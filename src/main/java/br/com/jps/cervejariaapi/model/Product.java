package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Category categoryId;

}
