package br.com.indra.eduardo_bacchiega.cart.repository;

import br.com.indra.eduardo_bacchiega.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
