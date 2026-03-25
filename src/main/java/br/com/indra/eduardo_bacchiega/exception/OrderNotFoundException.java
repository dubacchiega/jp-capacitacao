package br.com.indra.eduardo_bacchiega.exception;

import br.com.indra.eduardo_bacchiega.model.Order;

import java.util.List;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
