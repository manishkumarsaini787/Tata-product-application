package Authentication.Auth.Controller;

import Authentication.Auth.Exception.Useralreadyexists;
import Authentication.Auth.Model.User;
import Authentication.Auth.Service.IuserService;
import Authentication.Auth.Service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IuserService iuserservice;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;
    //http://localhost:65501/api/v1/user/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Useralreadyexists {
        // Validate that email is present
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Email cannot be null or empty");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);  // Return JSON
        }

        // If role is not set, default to 'user-access'
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user-access");
        }

        // Call the service to register the user
        return new ResponseEntity<>(iuserservice.registeruser(user), HttpStatus.OK);
    }

    //http://localhost:65501/api/v1/user/login
//
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Ensure user.getEmail() and user.getPassword() are not null or empty
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Email and Password cannot be empty", HttpStatus.BAD_REQUEST);
        }

        User retrivedUser = iuserservice.checkuser(user);
        if (retrivedUser != null) {
            Map<String, String> tokenResponse = securityTokenGenerator.generate(retrivedUser);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failure", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        try {
            User user = iuserservice.updateUser(email, updatedUser);  // Call service method to update user
            return new ResponseEntity<>(user, HttpStatus.OK);  // Return updated user
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user", HttpStatus.INTERNAL_SERVER_ERROR);  // Handle error
        }
    }

}

















//http://localhost:65501/api/v1/user/register
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user)throws Useralreadyexists
//    {
//        user.setRole("user-access");
//        return new ResponseEntity<>(iuserservice.registeruser(user), HttpStatus.OK);
//    }
// In UserController.java