package br.com.indra.eduardo_bacchiega.inventory.repository;

import br.com.indra.eduardo_bacchiega.inventory.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
}
