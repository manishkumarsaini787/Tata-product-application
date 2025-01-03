package Product.Product.application.Controller;

import Product.Product.application.Repository.IproductRepository;
import Product.Product.application.Repository.IuserRepository;
import Product.Product.application.Service.IproductService;
import Product.Product.application.Model.Product;
import Product.Product.application.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/v1")
public class ProductController {

    @Autowired
    private IproductService iproductservice;
    @Autowired
    private IuserRepository iuserRepository;
    @Autowired
    IproductRepository iproductRepository;


    //http://localhost:9091/api/user/v1/addproduct
    // Endpoint to add a new product
    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            // Retrieve the user by email (from the request body)
            Optional<User> userOpt = iuserRepository.findByEmail(product.getUser().getEmail()); // user_email is expected in Product
            if (!userOpt.isPresent()) {
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);  // If user is not found, return BAD_REQUEST
            }

            // Set the user to the product
            product.setUser(userOpt.get()); // associate the retrieved User with the product

            // Call the service layer to add the product
            iproductservice.addProduct(product);  // Calls the addProduct service method

            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);  // Successful response
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Error response
        }
    }
    private static final String SECRET_KEY = "securitykeyfordemo";  // Replace with your actual secret key
    public String extractEmailFromToken(String token) {
        try {
            // Parsing the JWT token
            Claims claims = Jwts.parser()
                    .setSigningKey("securitykeyfordemo")  // Ensure this is the same key as used for signing in Auth application
                    .parseClaimsJws(token)
                    .getBody();

            // Extract the email from the token
            String email = claims.get("UserEmail", String.class);  // Extract the "UserEmail" claim

            return email;
        } catch (Exception e) {
            System.out.println("Error extracting email from token: " + e.getMessage());
            return null;
        }
    }
    //http://localhost:9091/api/user/v1/getuserproducts
    @GetMapping("/getuserproducts")
    public ResponseEntity<?> getUserProducts(@RequestHeader("Authorization") String token) {
        try {
            // Log the token received in the header
            System.out.println("Received Token: " + token);  // Log the token to make sure it's being received correctly

            // Remove 'Bearer ' prefix before passing the token to the method
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);  // Remove the "Bearer " part
            }

            // Extract the email from the token
            String email = extractEmailFromToken(token);

            // Check if the email is null
            if (email == null) {
                System.out.println("Error: Missing email from token.");  // Log this for debugging
                return new ResponseEntity<>("Invalid token or missing email", HttpStatus.UNAUTHORIZED);
            }

            // Log the extracted email to verify it
            System.out.println("Extracted email from token in controller: " + email);

            // Fetch products for this email
            List<Product> products = iproductservice.getProductsByUserEmail(email);

            // Return products if found, otherwise a message saying no products found
            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found for this user", HttpStatus.OK);
            }

            return ResponseEntity.ok(products);  // Return the products as response
        } catch (Exception e) {
            System.out.println("Error in fetching products: " + e.getMessage());
            return new ResponseEntity<>("Error fetching products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //http://localhost:9091/api/user/v1/update/product/{id}
    @PutMapping("/update/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        try {
            Product existingProduct = iproductRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));

            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());

            iproductRepository.save(existingProduct);
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:9091/api/user/v1/deleteproduct/{id}
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        boolean isDeleted = iproductservice.deleteProduct(id);

        if (isDeleted) {
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint to get a product by its ID
    //http://localhost:9091/api/user/v1/product/{id}
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            // Call service layer to get the product by ID
            Product product = iproductservice.getProductById(id);

            // Check if the product exists
            if (product == null) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }

            // Return the found product
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}






//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        boolean isDeleted = iproductservice.deleteProduct(id);
//        if (isDeleted) {
//            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
//        }
//    }
//@DeleteMapping("/delete/{id}")
//public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
//    try {
//        Product product = iproductRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
//        iproductRepository.delete(product);
//        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
//    } catch (Exception e) {
//        return new ResponseEntity<>("Error deleting product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}



//    @GetMapping("/getuserproducts")
//    public ResponseEntity<?> getUserProducts(@RequestHeader("Authorization") String token) {
//        try {
//            // Log the token received in the header
//            System.out.println("Received Token: " + token);  // This is to check if token is being received
//
//            String email = extractEmailFromToken(token);  // Ensure this method is extracting the correct email from token
//            if (email == null) {
//                System.out.println("Error: Missing email from token.");  // Log to make sure the email is being extracted
//                return new ResponseEntity<>("Invalid token or missing email", HttpStatus.UNAUTHORIZED);
//            }
//
//            // Log the extracted email
//            System.out.println("Extracted email from token in controller: " + email);  // Make sure email is correct
//
//            // Fetch the products for the user based on email
//            List<Product> products = iproductservice.getProductsByUserEmail(email);
//
//            // Return response
//            if (products.isEmpty()) {
//                return new ResponseEntity<>("No products found for this user", HttpStatus.OK);
//            }
//
//            return ResponseEntity.ok(products);  // Return products if found
//
//        } catch (Exception e) {
//            System.out.println("Error in fetching products: " + e.getMessage());  // Log any error
//            return new ResponseEntity<>("Error fetching products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }






















//http://localhost:9091/api/user/v1/adduser"
// Uncomment and use this endpoint to register a new user if needed
//    @PostMapping("/adduser")
//    public ResponseEntity<?> addUser(@RequestBody User user) {
//        try {
//            // Try to add the user
//            User savedUser = iproductservice.add(user);  // Calls the add method from IproductService
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);  // Return created status with the saved user
//        } catch (Useralreadyexists e) {
//            // Handle case where the user already exists
//            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
//        } catch (UserNotfoundExecption e) {
//            // Handle case where the user is not found (if relevant to your use case)
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//    }


//http://localhost:9091/api/user/v1/addproduct
//    @PostMapping("/addproduct")
//    public ResponseEntity<?> addProduct(@RequestBody Product product) {
//        try {
//            // Ensure the user email is present in the request body
//            String userEmail = product.getUser().getEmail();
//            if (userEmail == null || userEmail.isEmpty()) {
//                return new ResponseEntity<>("User email is required", HttpStatus.BAD_REQUEST);
//            }
//
//            // Retrieve the user from the database using the provided email
//            Optional<User> userOpt = iuserRepository.findByEmail(userEmail);
//            if (!userOpt.isPresent()) {
//                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
//            }
//
//            // Set the user to the product
//            product.setUser(userOpt.get());
//
//            // Call the service layer to add the product
//            iproductservice.addProduct(product);
//
//            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error adding product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }




//http://localhost:9091/api/user/v1/getuser/email"
// Endpoint to get user details by email
//    @GetMapping("/getuser/email")
//    public ResponseEntity<?> getByEmail(HttpServletRequest request) throws UserNotfoundExecption {
//        String email = (String) request.getAttribute("attr1");  // Get email from the request attribute
//        try {
//            User user = iuserRepository.getEmail(email);  // Fetch the user by email
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (UserNotfoundExecption e) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);  // Return not found status
//        }
//    }

// Endpoint to get all users (admin-only access)
//http://localhost:9091/api/user/v1/getall
//    @GetMapping("/getall")
//    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
//        if ("admin".equals(request.getAttribute("attr2"))) {  // Check if user is admin
//            List<User> users = iproductservice.getall();  // Fetch all users
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("You are not authorized", HttpStatus.FORBIDDEN);  // Return Forbidden if not admin
//        }
//    }

// Endpoint to get all products
//http://localhost:9091/api/user/v1/getallproducts
//    @GetMapping("/getallproducts")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = iproductservice.getAllProducts();  // Fetch all products from the service
//        if (products.isEmpty()) {
//            return ResponseEntity.noContent().build();  // Return 204 No Content if no products found
//        }
//        return ResponseEntity.ok(products);  // Return products with HTTP 200 OK
//    }

// Method to extract email from JWT token
//    private String extractEmailFromToken(String token) {
//        try {
//            // Parse the JWT token using the secret key and get claims
//            Claims claims = Jwts.parser()
//                    .setSigningKey(SECRET_KEY)  // Use the secret key to verify the token signature
//                    .parseClaimsJws(token)      // Parse the JWT and extract claims
//                    .getBody();
//
//            // Extract the "UserEmail" claim from the token
//            return claims.get("UserEmail", String.class);
//        } catch (Exception e) {
//            // If there's an error (invalid token, expired, etc.), return null
//            return null;
//        }
//    }


//    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
//        try {
//            Product product = iproductservice.updateProduct(id, updatedProduct);  // Call service method to update product
//            return new ResponseEntity<>(product, HttpStatus.OK);  // Return updated product
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error updating product", HttpStatus.INTERNAL_SERVER_ERROR);  // Handle error
//        }
//    }
//@PutMapping("/update/product/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
//        try {
//            // Fetch the existing product
//            Product existingProduct = iproductRepository.findById(id)
//                    .orElseThrow(() -> new Exception("Product not found"));
//
//            // Update the product fields
//            existingProduct.setName(product.getName());
//            existingProduct.setPrice(product.getPrice());
//            existingProduct.setDescription(product.getDescription());
//
//            // Save the updated product
//            Product updatedProduct = iproductRepository.save(existingProduct);
//
//            // Log updated product for debugging
//            System.out.println("Updated product: " + updatedProduct);
//
//            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // Return the updated product
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error updating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



// Endpoint to delete user by email
//http://localhost:9091/api/user/v1/delete/by-email
//    @DeleteMapping("/delete/by-email")
//    public ResponseEntity<?> deleteByEmail(HttpServletRequest request) throws UserNotfoundExecption {
//        String email = (String) request.getAttribute("attr1");  // Get email from the request attribute
//        try {
//            iproductservice.deletebyemail(email);  // Delete user by email
//            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
//        } catch (UserNotfoundExecption e) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);  // Return not found if user does not exist
//        }
//    }

