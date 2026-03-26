package br.com.indra.eduardo_bacchiega.exception;

public class CouponExistsException extends RuntimeException {
    public CouponExistsException(String message) {
        super(message);
    }
}
