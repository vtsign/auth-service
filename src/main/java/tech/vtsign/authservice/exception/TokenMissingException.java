package tech.vtsign.authservice.exception;

import org.springframework.http.HttpStatus;

public class TokenMissingException extends RuntimeException {
    public static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public TokenMissingException(String message) {
        super(message);
    }
}
