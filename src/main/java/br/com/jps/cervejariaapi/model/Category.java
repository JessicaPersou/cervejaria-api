package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 15, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category", referencedColumnName = "id", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Category parent;

}
