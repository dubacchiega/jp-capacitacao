package br.com.indra.eduardo_bacchiega.cart.repository;

import br.com.indra.eduardo_bacchiega.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
