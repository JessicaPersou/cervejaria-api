package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "quantity_total", nullable = false)
    @NotNull(message = "Campo não pode ser vazio ou nulo")
    private int quantityTotal;

    @Column(name = "name", length = 50, nullable = false)
    @NotNull(message = "Campo não pode ser vazio ou nulo")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @NotNull(message = "Campo não pode ser vazio ou nulo")
    private Product productId;

}
