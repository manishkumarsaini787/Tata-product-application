package Product.Product.application.ServiceImpl;

import Product.Product.application.Exception.UserNotfoundExecption;
import Product.Product.application.Model.Product;
import Product.Product.application.Model.User;
import Product.Product.application.Repository.IproductRepository;
import Product.Product.application.Repository.IuserRepository;
import Product.Product.application.Service.IproductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class IproductServiceImpl implements IproductService {
    @Autowired
    private IuserRepository iuserRepository; // Repository for user

    @Autowired
    private IproductRepository iproductrepository;
//    private UserProxy userProxy;
//    @Autowired
//    public IproductServiceImpl(IproductRepository iproductrepository, UserProxy userProxy) {
//        this.iproductrepository = iproductrepository;
//        this.userProxy = userProxy;
//    }

    // Add a new user
    public User add(User user) throws UserNotfoundExecption {
        // Check if user already exists
        if (iuserRepository.findById(user.getEmail()).isEmpty()) {
            // If the user doesn't exist, save it
//            UserDTO u=new UserDTO();
//            u.setEmail(user.getEmail());
//            u.setPassword(user.getPassword());
//            userProxy.registerUser(user);
            return iuserRepository.save(user);
        } else {
            throw new UserNotfoundExecption();  // This will trigger 409 Conflict with "User not found"
        }
    }

    // Add a new product
    @Override
    public void addProduct(Product product) {
        iproductrepository.save(product);  // Save the product to the database
    }

    // Get user by email
//    @Override
//    public User getemail(String email) throws UserNotfoundExecption {
//        return iuserRepository.findById(email)
//                .orElseThrow(() -> new UserNotfoundExecption());  // If not found, throw exception
//    }

    // Get all users
    @Override
    public List<User> getall() {
        return iuserRepository.findAll();
    }

    // Get all products
//    @Override
//    public List<Product> getAllProducts() {
//        return iproductrepository.findAll();  // Fetch all products from the database
//    }
    public List<Product> getProductsByUserEmail(String email) {
        Optional<User> userOpt = iuserRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return iproductrepository.findByUser(user);  // Assuming you have a method to find products by user
        }
        return new ArrayList<>();  // Make sure the repository query is implemented to filter by email
    }

    // Delete user by email
//    @Override
//    public boolean deletebyemail(String email) throws UserNotfoundExecption {
//        if (iuserRepository.findById(email).isEmpty()) {
//            throw new UserNotfoundExecption();  // User not found, so throw exception
//        } else {
//            iuserRepository.deleteById(email);  // Delete user from the repository
//            return true;
//        }
//    }
    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = iproductrepository.findById(id);
        if (existingProductOptional.isEmpty()) {
            return null;
        }

        Product existingProduct = existingProductOptional.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        iproductrepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public Product getProductById(Long id) {
        // Retrieve the product by ID from the repository
        Optional<Product> productOptional = iproductrepository.findById(id);

        // Return the product if found, otherwise return null
        return productOptional.orElse(null);
    }
    @Transactional
  public boolean deleteProduct(Long id) {
        // Check if the product exists in the database
        Optional<Product> existingProduct = iproductrepository.findById(id);

       // If the product doesn't exist, return false
       if (existingProduct.isEmpty()) {
            return false;
        }

      // If the product exists, delete it
       iproductrepository.deleteById(id);

       // After deletion, return true
       return true;
   }

}



//    @Transactional
//    public boolean deleteProduct(Long id) {
//        try {
//            // Check if the product exists
//            if (iproductrepository.existsById(String.valueOf(id))) {
//                System.out.println("Product found, deleting: " + id);
//                iproductrepository.deleteById(String.valueOf(id));  // Delete product by ID
//                return true;  // Successfully deleted
//            } else {
//                System.out.println("Product with ID " + id + " not found.");
//                return false;  // Product not found
//            }
//        } catch (Exception e) {
//            System.out.println("Error deleting product with ID " + id + ": " + e.getMessage());
//            return false;  // Error occurred during deletion
//        }


//    @Transactional
//    public boolean deleteProduct(Long id) {
//        // Check if the product exists in the database
//        Optional<Product> existingProduct = iproductrepository.findById(id);
//
//        // If the product doesn't exist, return false
//        if (existingProduct.isEmpty()) {
//            return false;
//        }
//
//        // If the product exists, delete it
//        iproductrepository.delete(existingProduct.get());
//
//        // After deletion, return true
//        return true;
//    }


    //    @Transactional
//    public boolean deleteProduct(Long id) {
//        Optional<Product> existingProduct = iproductrepository.findById(id);
//        if (existingProduct.isEmpty()) {
//            System.out.println("Product with ID " + id + " not found.");
//            return false;
//        }
//
//        // Debug log: Product found
//        System.out.println("Product found: " + existingProduct.get());
//
//        iproductrepository.delete(existingProduct.get());
//
//        // Debug log: Product deleted
//        System.out.println("Product with ID " + id + " deleted.");
//        return true;
//    }

//@Transactional
//public boolean deleteProduct(Long id) {
//    // Log the incoming product ID
//    System.out.println("Attempting to delete product with ID: " + id);
//
//    // Try to find the product by its ID
//    Optional<Product> existingProduct = iproductrepository.findById(id);
//
//    // Check if the product was found
//    if (existingProduct.isEmpty()) {
//        System.out.println("Product with ID " + id + " not found.");
//        return false; // Product not found, return false
//    }
//
//    // Log that product is found
//    System.out.println("Product found: " + existingProduct.get());
//
//    // Delete the product
//    iproductrepository.delete(existingProduct.get());
//
//    // Log the deletion action
//    System.out.println("Product with ID " + id + " deleted successfully.");
//
//    return true; // Return true if deletion was successful
//}



