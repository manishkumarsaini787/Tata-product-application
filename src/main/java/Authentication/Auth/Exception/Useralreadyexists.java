package Authentication.Auth.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "user already exists")
public class Useralreadyexists extends Exception{
}

