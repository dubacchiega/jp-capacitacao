package br.com.indra.eduardo_bacchiega.mapper;

import br.com.indra.eduardo_bacchiega.dto.CartItemResponseDto;
import br.com.indra.eduardo_bacchiega.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.model.Cart;

import java.math.BigDecimal;

public class CartMapper {

    public static CartResponseDto toDto(Cart cart){
        return CartResponseDto.builder()
                .userId(cart.getUser().getId())
                .totalAmount(cart.getTotal())
                .items(cart.getItems().stream().map(
                        (i) -> CartItemResponseDto.builder()
                                .productId(i.getProduct().getId())
                                .productName(i.getProduct().getName())
                                .quantity(i.getQuantity())
                                .priceSnapshot(i.getPriceSnapshot())
                                .subTotal(i.getPriceSnapshot().multiply(BigDecimal.valueOf(i.getQuantity())))
                                .build()
                ).toList())
                .build();
    }
}
