package br.com.indra.eduardo_bacchiega.order.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemResponseDto(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {
}
