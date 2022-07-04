package app.loja_dev.exceptions;

public class AuthorizationPaymentException extends RuntimeException {
    public AuthorizationPaymentException(String message) {
        super(message);
    }
}
