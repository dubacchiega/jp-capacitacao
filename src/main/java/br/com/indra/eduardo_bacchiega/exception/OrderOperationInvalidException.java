package br.com.indra.eduardo_bacchiega.exception;

public class OrderOperationInvalidException extends RuntimeException {
    public OrderOperationInvalidException(String message) {
        super(message);
    }
}
