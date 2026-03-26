package br.com.indra.eduardo_bacchiega.cart.controller;

import br.com.indra.eduardo_bacchiega.docs.CartControllerDoc;
import br.com.indra.eduardo_bacchiega.cart.dto.CartItemRequestDto;
import br.com.indra.eduardo_bacchiega.cart.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.cart.service.CartService;
import br.com.indra.eduardo_bacchiega.user.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController implements CartControllerDoc {

    private final CartService cartService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public CartResponseDto getCart(){
        return cartService.get(UserService.getUser().id());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/items")
    public CartResponseDto createCart(){
        return cartService.create(UserService.getUser().id());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/items")
    public CartResponseDto addItems(@RequestBody CartItemRequestDto request, @RequestParam(required = false) String coupon){
        return cartService.addItems(UserService.getUser().id(), request, coupon);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/items/{productId}")
    public CartResponseDto deleteItems(@RequestBody Long productId){
        return cartService.deleteItems(UserService.getUser().id(), productId);
    }

}
