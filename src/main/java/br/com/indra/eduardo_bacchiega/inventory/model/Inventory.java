package br.com.indra.eduardo_bacchiega.inventory.model;

import br.com.indra.eduardo_bacchiega.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Column(name = "min_quantity")
    private Long minQuantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
