package Product.Product.application.Repository;

import Product.Product.application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IuserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

}
