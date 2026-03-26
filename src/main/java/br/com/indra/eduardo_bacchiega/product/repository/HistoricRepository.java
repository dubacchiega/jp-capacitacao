package br.com.indra.eduardo_bacchiega.product.repository;

import br.com.indra.eduardo_bacchiega.product.model.PriceHistoric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoricRepository extends JpaRepository<PriceHistoric, UUID> {
}
