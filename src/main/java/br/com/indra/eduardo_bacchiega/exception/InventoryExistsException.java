package br.com.indra.eduardo_bacchiega.exception;

public class InventoryExistsException extends RuntimeException {
    public InventoryExistsException(String message) {
        super(message);
    }
}
