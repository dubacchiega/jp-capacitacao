package br.com.indra.eduardo_bacchiega.model;

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

    private Long quantity;

    @Column(name = "min_quantity")
    private Long minQuantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
