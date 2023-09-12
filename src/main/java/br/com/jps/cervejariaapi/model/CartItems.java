package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private int quantity;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private BigDecimal unitPrice;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Product productId;

}
