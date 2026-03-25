package br.com.indra.eduardo_bacchiega.repository;

import br.com.indra.eduardo_bacchiega.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order, Long> {
}
