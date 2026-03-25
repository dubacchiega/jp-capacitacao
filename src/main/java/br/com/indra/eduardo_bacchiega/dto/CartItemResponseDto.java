package br.com.indra.eduardo_bacchiega.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemResponseDto(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal priceSnapshot,
        BigDecimal subTotal
) {
}
