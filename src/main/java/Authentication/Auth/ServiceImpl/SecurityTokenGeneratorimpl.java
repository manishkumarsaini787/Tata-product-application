package Authentication.Auth.ServiceImpl;

import Authentication.Auth.Model.User;
import Authentication.Auth.Service.SecurityTokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorimpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generate(User user) {
        Map<String,String> result=new HashMap<>();
        Map<String, Object> userdata=new HashMap<>();
        userdata.put("UserEmail",user.getEmail());
        userdata.put("UserRole",user.getRole());
        String mytoken= Jwts.builder().setClaims(userdata).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512,"securitykeyfordemo").compact();
        result.put("token",mytoken);
        result.put("message", "User Successfully logged in");
        return result;
    }


}