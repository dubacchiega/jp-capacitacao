package br.com.indra.eduardo_bacchiega.order.controller;

import br.com.indra.eduardo_bacchiega.docs.OrderControllerDoc;
import br.com.indra.eduardo_bacchiega.order.dto.OrderResponseDto;
import br.com.indra.eduardo_bacchiega.order.service.OrderService;
import br.com.indra.eduardo_bacchiega.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController implements OrderControllerDoc {

    private final OrderService orderService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponseDto newOrder(){
        return orderService.newOrder(UserService.getUser());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId){
        return orderService.getOrder(UserService.getUser(),orderId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrderResponseDto> getAllOrder(){
        return orderService.getAllOrder(UserService.getUser());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{orderId}/cancel")
    public OrderResponseDto cancelOrder(@PathVariable Long orderId){
        return orderService.cancelOrder(UserService.getUser(), orderId);
    }

}
