package br.com.jps.cervejariaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 15, unique = true)
    private String name;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_category", referencedColumnName = "id")
    private Category parent;
}
