package tech.vtsign.authservice.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token is expired!");
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
