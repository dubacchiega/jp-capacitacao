package br.com.indra.eduardo_bacchiega.repository;

import br.com.indra.eduardo_bacchiega.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
