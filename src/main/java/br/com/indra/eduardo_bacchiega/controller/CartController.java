package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.dto.CartItemRequestDto;
import br.com.indra.eduardo_bacchiega.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.model.Cart;
import br.com.indra.eduardo_bacchiega.service.CartService;
import br.com.indra.eduardo_bacchiega.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public CartResponseDto getCart(){
        return cartService.get(UserService.getUser().id());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/items")
    public CartResponseDto createCart(){
        return cartService.create(UserService.getUser().id());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("items")
    public CartResponseDto addItems(@RequestBody CartItemRequestDto request){
        return cartService.addItems(UserService.getUser().id(), request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("items/{productId}")
    public CartResponseDto deleteItems(@RequestBody Long productId){
        return cartService.deleteItems(UserService.getUser().id(), productId);
    }


}
