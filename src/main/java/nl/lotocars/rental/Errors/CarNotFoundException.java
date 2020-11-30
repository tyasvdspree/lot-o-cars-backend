package nl.lotocars.rental.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {
    private static final String message = "Car not Found";

    public CarNotFoundException() {
        super(message);
    }

    public CarNotFoundException(Throwable cause) {
        super(message, cause);
    }

    public CarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
