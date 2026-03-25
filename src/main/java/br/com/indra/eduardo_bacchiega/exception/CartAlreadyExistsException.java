package br.com.indra.eduardo_bacchiega.exception;

public class CartAlreadyExistsException extends RuntimeException {
    public CartAlreadyExistsException(String message){
        super(message);
    }
}
