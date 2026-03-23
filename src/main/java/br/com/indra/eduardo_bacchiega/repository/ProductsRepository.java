package br.com.indra.eduardo_bacchiega.repository;

import br.com.indra.eduardo_bacchiega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
}
