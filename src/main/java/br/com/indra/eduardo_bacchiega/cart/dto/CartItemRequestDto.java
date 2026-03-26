package br.com.indra.eduardo_bacchiega.cart.dto;

public record CartItemRequestDto(
        Long productId,
        Integer quantity
) {
}
