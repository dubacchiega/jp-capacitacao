package br.com.indra.eduardo_bacchiega.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartResponseDto(
        Long userId,
        BigDecimal totalAmount,
        List<CartItemResponseDto> items
) {
}
