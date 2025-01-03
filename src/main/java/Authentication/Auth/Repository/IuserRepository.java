package Authentication.Auth.Repository;


import Authentication.Auth.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IuserRepository extends JpaRepository<User,String> {

    //register--> controller-->service-->save() of repository
    User findByEmailAndPassword(String email,String password);
    //User findBydeleteuser(User user);
    Optional<User> findById(String email);


}