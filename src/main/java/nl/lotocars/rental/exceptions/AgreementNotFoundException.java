package nl.lotocars.rental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgreementNotFoundException extends RuntimeException {
    private static final String message = "Agreement not Found";

    public AgreementNotFoundException() {
        super(message);
    }

    public AgreementNotFoundException(Throwable cause) {
        super(message, cause);
    }

    public AgreementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
