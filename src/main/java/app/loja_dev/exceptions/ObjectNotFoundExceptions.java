package app.loja_dev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectNotFoundExceptions extends RuntimeException{

    public ObjectNotFoundExceptions(String message){
        super(message);
    }
}
