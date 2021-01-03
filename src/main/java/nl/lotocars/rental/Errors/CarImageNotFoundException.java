package nl.lotocars.rental.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarImageNotFoundException extends RuntimeException {
    private static final String message = "Car image not Found";

    public CarImageNotFoundException() {
        super(message);
    }

    public CarImageNotFoundException(Throwable cause) {
        super(message, cause);
    }

    public CarImageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
