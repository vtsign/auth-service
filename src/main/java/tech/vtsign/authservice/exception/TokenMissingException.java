package tech.vtsign.authservice.exception;

public class TokenMissingException extends RuntimeException {
    public TokenMissingException() {
        super("Token is missing!");
    }

    public TokenMissingException(String message) {
        super(message);
    }

    public TokenMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
