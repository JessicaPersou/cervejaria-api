package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "quantity_total", nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private int quantityTotal;

    @Column(name = "name", length = 20, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Product productId;

}
