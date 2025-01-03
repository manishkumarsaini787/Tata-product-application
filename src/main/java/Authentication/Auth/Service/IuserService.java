package Authentication.Auth.Service;

import Authentication.Auth.Exception.Useralreadyexists;
import Authentication.Auth.Model.User;

public interface IuserService {
    User registeruser(User user)throws Useralreadyexists;

    User checkuser(User user);
    User updateUser(String email, User updatedUser) throws Exception;
}

