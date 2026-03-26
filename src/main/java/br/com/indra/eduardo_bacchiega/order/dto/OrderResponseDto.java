package br.com.indra.eduardo_bacchiega.order.dto;

import br.com.indra.eduardo_bacchiega.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderResponseDto(OrderStatus status, BigDecimal total, List<OrderItemResponseDto> items) {
}
