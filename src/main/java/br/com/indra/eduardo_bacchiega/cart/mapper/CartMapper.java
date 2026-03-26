package br.com.indra.eduardo_bacchiega.cart.mapper;

import br.com.indra.eduardo_bacchiega.cart.dto.CartItemResponseDto;
import br.com.indra.eduardo_bacchiega.cart.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.cart.model.Cart;

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
