package br.com.indra.eduardo_bacchiega.order.repository;

import br.com.indra.eduardo_bacchiega.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order, Long> {
}
