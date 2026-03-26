package br.com.indra.eduardo_bacchiega.order.service;

import br.com.indra.eduardo_bacchiega.config.JWTUserData;
import br.com.indra.eduardo_bacchiega.inventory.dto.InventoryRemoveRequestDto;
import br.com.indra.eduardo_bacchiega.order.dto.OrderResponseDto;
import br.com.indra.eduardo_bacchiega.enums.OrderStatus;
import br.com.indra.eduardo_bacchiega.exception.CartEmptyException;
import br.com.indra.eduardo_bacchiega.exception.CartNotFoundException;
import br.com.indra.eduardo_bacchiega.exception.OrderNotFoundException;
import br.com.indra.eduardo_bacchiega.exception.OrderOperationInvalidException;
import br.com.indra.eduardo_bacchiega.order.mapper.OrderMapper;
import br.com.indra.eduardo_bacchiega.cart.model.Cart;
import br.com.indra.eduardo_bacchiega.order.model.Order;
import br.com.indra.eduardo_bacchiega.order.model.OrderItem;
import br.com.indra.eduardo_bacchiega.cart.repository.CartItemRepository;
import br.com.indra.eduardo_bacchiega.cart.repository.CartRepository;
import br.com.indra.eduardo_bacchiega.order.repository.OrderItemRepository;
import br.com.indra.eduardo_bacchiega.order.repository.OrderRepository;
import br.com.indra.eduardo_bacchiega.inventory.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final InventoryService inventoryService;

    @Transactional
    public OrderResponseDto newOrder(JWTUserData user) {
        Cart cart = cartRepository.findByUserId(user.id()).orElseThrow(
                () -> new CartNotFoundException("Cannot find a cart for this user")
        );

        if (cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty.");
        }

        Order order = Order.builder()
                .user(cart.getUser())
                .orderStatus(OrderStatus.CREATED)
                .total(cart.getTotal())
                .build();

        List<OrderItem> items = cart.getItems().stream().map(
                (ci) -> {
                    inventoryService.remove(ci.getProduct().getInventory().getId(), InventoryRemoveRequestDto.builder().quantity(ci.getQuantity()).build());
                    return OrderItem.builder()
                            .order(order)
                            .product(ci.getProduct())
                            .quantity(ci.getQuantity())
                            .unityPrice(ci.getPriceSnapshot())
                            .build();
                }
        ).toList();

        order.setItems(items);

        Order saved = orderRepository.save(order);

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotal(BigDecimal.ZERO);
        cartRepository.save(cart);

        return OrderMapper.toDto(saved);

    }

    public List<OrderResponseDto> getAllOrder(JWTUserData user) {

        List<Order> list = orderRepository.findByUserId(user.id());
        if (list.isEmpty()){
            throw new OrderNotFoundException("This user haven't a Order.");
        }

        return list.stream().map(
                OrderMapper::toDto
        ).toList();

    }

    public OrderResponseDto getOrder(JWTUserData user, Long id){

        Order order = orderRepository.findByIdAndUserId(id, user.id()).orElseThrow(
                () -> new OrderNotFoundException("Order not found.")
        );

        return OrderMapper.toDto(order);
    }

    public OrderResponseDto cancelOrder(JWTUserData user, Long orderId) {
        Order order = orderRepository.findByIdAndUserId(orderId, user.id()).orElseThrow(
                () -> new OrderNotFoundException("Order not found for this client.")
        );

        if (order.getOrderStatus() == OrderStatus.CREATED || order.getOrderStatus() == OrderStatus.PAID){
            order.setOrderStatus(OrderStatus.CANCELLED);
        }else {
            throw new OrderOperationInvalidException("An order can only be cancelled if its status is CREATED or PAID.");
        }

        return OrderMapper.toDto(orderRepository.save(order));
    }

}
