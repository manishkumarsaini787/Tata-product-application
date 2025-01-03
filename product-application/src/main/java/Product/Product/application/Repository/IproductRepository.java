package Product.Product.application.Repository;

import Product.Product.application.Model.Product;
import Product.Product.application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IproductRepository extends JpaRepository<Product,Long> {
    //List<Product> findByUserEmail(String email);
    List<Product> findByUser(User user);
    Optional<Product> findById(Long id);
    //void delete(Product product);
//    void deleteById(Long s);
}
//productdetails_db