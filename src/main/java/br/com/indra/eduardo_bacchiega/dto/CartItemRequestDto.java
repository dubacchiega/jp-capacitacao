package br.com.indra.eduardo_bacchiega.dto;

public record CartItemRequestDto(
        Long productId,
        Integer quantity,
        Long priceSnapshot
) {
}
