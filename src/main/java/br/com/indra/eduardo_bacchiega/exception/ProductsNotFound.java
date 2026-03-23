package br.com.indra.eduardo_bacchiega.exception;

public class ProductsNotFound extends RuntimeException {
    public ProductsNotFound(String message) {
        super(message);
    }
}
