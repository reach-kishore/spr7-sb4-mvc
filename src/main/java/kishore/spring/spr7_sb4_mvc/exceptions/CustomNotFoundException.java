package kishore.spring.spr7_sb4_mvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Value not found")
//if we use this response status annotation - this status will be automatically sent out in case of exception.
//we can avoid the usage of controller advice - if the ask is just to return status in case of this particular exc
public class CustomNotFoundException extends RuntimeException{
}
