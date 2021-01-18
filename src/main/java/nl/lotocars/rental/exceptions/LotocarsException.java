package nl.lotocars.rental.exceptions;

public class LotocarsException extends RuntimeException {
    public LotocarsException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public LotocarsException(String exMessage) {
        super(exMessage);
    }
}
