package br.com.indra.eduardo_bacchiega.cart.service;

import br.com.indra.eduardo_bacchiega.cart.dto.CartItemRequestDto;
import br.com.indra.eduardo_bacchiega.cart.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.coupon.dto.ValidCoupon;
import br.com.indra.eduardo_bacchiega.coupon.model.Coupon;
import br.com.indra.eduardo_bacchiega.coupon.repository.CouponRepository;
import br.com.indra.eduardo_bacchiega.coupon.service.CouponService;
import br.com.indra.eduardo_bacchiega.enums.DiscountType;
import br.com.indra.eduardo_bacchiega.exception.*;
import br.com.indra.eduardo_bacchiega.cart.mapper.CartMapper;
import br.com.indra.eduardo_bacchiega.cart.model.Cart;
import br.com.indra.eduardo_bacchiega.cart.model.CartItem;
import br.com.indra.eduardo_bacchiega.product.model.Product;
import br.com.indra.eduardo_bacchiega.user.model.User;
import br.com.indra.eduardo_bacchiega.cart.repository.CartItemRepository;
import br.com.indra.eduardo_bacchiega.cart.repository.CartRepository;
import br.com.indra.eduardo_bacchiega.product.repository.ProductsRepository;
import br.com.indra.eduardo_bacchiega.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final CouponRepository couponRepository;
    private final CouponService couponService;

    public CartResponseDto get(Long userId) {
        Cart cart = findCart(userId);
        return CartMapper.toDto(cart);
    }

    public CartResponseDto create(Long userId) {
        cartRepository.findByUserId(userId).ifPresent(
                (e) -> {
                    throw new CartAlreadyExistsException("Cart already exists for this user");
                }
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        Cart save = cartRepository.save(
                Cart.builder()
                        .user(user)
                        .items(new ArrayList<>())
                        .total(BigDecimal.ZERO)
                        .build()
        );

        return CartMapper.toDto(save);
    }

    @Transactional
    public CartResponseDto addItems(Long userId, CartItemRequestDto request, String couponName) {
        Cart cart = findCart(userId);

        Product product = productsRepository.findById(request.productId()).orElseThrow(
                () -> new ProductsNotFound("Product not found")
        );

        Coupon coupon = couponRepository.findByCode(couponName).orElseThrow(
                () -> new CouponNotFoundException("Coupon not found")
        );


        Optional<CartItem> existsItem = cart.getItems().stream().filter(
                (i) -> i.getProduct().getId().equals(product.getId())
        ).findFirst();
        if (existsItem.isPresent()){
            CartItem cartItem = existsItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
            cartItem.setPriceSnapshot(calculateWithCoupon(coupon, product.getPrice()));

        } else {
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.quantity())
                    .priceSnapshot(calculateWithCoupon(coupon, product.getPrice()))
                    .build();
            cart.getItems().add(newCartItem);
        }
        recalculateTotal(cart);

        return CartMapper.toDto(cart);

    }

    public CartResponseDto deleteItems(Long userId, Long productId) {
        Cart cart = findCart(userId);

        Optional<CartItem> item = cart.getItems().stream().filter(
                (i) -> i.getProduct().getId().equals(productId)
        ).findFirst();

        if (item.isPresent()){
            cart.getItems().remove(item.get());
            recalculateTotal(cart);
            cartItemRepository.delete(item.get());
            cartRepository.save(cart);
        }else {
            throw new ProductsNotFound("Product not found");
        }
        return CartMapper.toDto(cart);

    }


    private void recalculateTotal(Cart cart){
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()){
            Integer quantity = item.getQuantity();
            BigDecimal itemTotal = item.getPriceSnapshot().multiply(BigDecimal.valueOf(quantity));
            total = total.add(itemTotal);
        }

        cart.setTotal(total);
    }

    private Cart findCart(Long userId){
        return cartRepository.findByUserId(userId).orElseThrow(
                () -> new CartNotFoundException("Cart not found for this user")
        );
    }

    private BigDecimal calculateWithCoupon(Coupon coupon, BigDecimal price){

        ValidCoupon valid = couponService.valid(coupon);

        if (valid.discountType() == DiscountType.FIXED){
            return price.subtract(valid.discountValue());
        } else if (valid.discountType()  == DiscountType.PERCENTAGE) {
            BigDecimal percentage = valid.discountValue().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            return price.multiply(percentage);
        } else {
            throw new CouponInvalidException("This coupon is not valid");
        }

    }
}
