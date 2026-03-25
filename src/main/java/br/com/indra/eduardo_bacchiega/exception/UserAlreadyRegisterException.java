package br.com.indra.eduardo_bacchiega.exception;

public class UserAlreadyRegisterException extends RuntimeException {
    public UserAlreadyRegisterException(String message) {
        super(message);
    }
}
