package br.com.indra.eduardo_bacchiega.inventory.repository;

import br.com.indra.eduardo_bacchiega.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
