package br.com.indra.eduardo_bacchiega.order.mapper;

import br.com.indra.eduardo_bacchiega.order.dto.OrderItemResponseDto;
import br.com.indra.eduardo_bacchiega.order.dto.OrderResponseDto;
import br.com.indra.eduardo_bacchiega.order.model.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order){
        List<OrderItemResponseDto> itemResponse = order.getItems().stream().map(
                (i) -> {
                    return OrderItemResponseDto.builder()
                            .productId(i.getProduct().getId())
                            .productName(i.getProduct().getName())
                            .quantity(i.getQuantity())
                            .unitPrice(i.getUnityPrice())
                            .subTotal(i.getUnityPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                            .build();
                }
        ).toList();

        return OrderResponseDto.builder()
                .status(order.getOrderStatus())
                .total(order.getTotal())
                .items(itemResponse)
                .build();
    }
}
