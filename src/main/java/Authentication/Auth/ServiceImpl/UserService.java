package Authentication.Auth.ServiceImpl;

import Authentication.Auth.Exception.Useralreadyexists;
import Authentication.Auth.Model.User;
import Authentication.Auth.Repository.IuserRepository;
import Authentication.Auth.Service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IuserService {

    @Autowired
    IuserRepository iuserrepositry;

//    @Override
//    public User registeruser(User user)throws Useralreadyexists {
//        if(iuserrepositry.findById(user.getEmail()).isEmpty())
//        {
//            return iuserrepositry.save(user);
//        }
//        else
//        {
//            throw new Useralreadyexists();
//        }
//    }
@Override
public User registeruser(User user) throws Useralreadyexists {
    if (user.getEmail() == null || user.getEmail().isEmpty()) {
        throw new IllegalArgumentException("Email cannot be null or empty");
    }

    // If role is not set, default to 'user-access'
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("user-access");
    }

    // Check if the user already exists by email
    if (iuserrepositry.findById(user.getEmail()).isEmpty()) {
        return iuserrepositry.save(user);
    } else {
        throw new Useralreadyexists();
    }
}


    @Override
    public User checkuser(User user) {
        return iuserrepositry.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }
    public User updateUser(String email, User updatedUser) throws Exception {
        // Find the existing user by email
        User existingUser = iuserrepositry.findById(email).orElseThrow(() -> new Exception("User not found"));

        // Update the fields of the existing user
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());  // You may also hash the password before saving
        existingUser.setRole(updatedUser.getRole());

        // Save the updated user to the database
        return iuserrepositry.save(existingUser);
    }
}
