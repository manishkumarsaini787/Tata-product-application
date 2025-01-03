package Product.Product.application.Service;

import Product.Product.application.Exception.UserNotfoundExecption;
import Product.Product.application.Exception.Useralreadyexists;
//import Product.Product.application.Model.User;
import Product.Product.application.Model.Product;
import Product.Product.application.Model.User;


import java.util.List;

public interface IproductService {
    List<Product> getProductsByUserEmail(String email);
    //User add(User user)throws Useralreadyexists,UserNotfoundExecption;
    void addProduct(Product product);
    //List<Product> getAllProducts();
    Product updateProduct(Long id, Product updatedProduct) throws Exception;
    List<User> getall();
    boolean deleteProduct(Long id);
    //boolean deletebyemail(String email)throws UserNotfoundExecption;
    Product getProductById(Long id);

}
