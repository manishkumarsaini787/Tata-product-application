package Authentication.Auth.Service;

import Authentication.Auth.Model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generate(User user);
}
