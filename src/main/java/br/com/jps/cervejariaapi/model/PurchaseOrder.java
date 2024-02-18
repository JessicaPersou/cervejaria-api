package br.com.jps.cervejariaapi.model;

import br.com.jps.cervejariaapi.enums.PaymentMethod;
import br.com.jps.cervejariaapi.enums.StatusPurchaseOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_order")
public class PurchaseOrder {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(name = "payment_method")
        @NotNull(message = "Campo não pode ser vazio ou nulo")
        private PaymentMethod paymentMethod;

        @Column(name = "dt_order")
        private LocalDate dtOrder;

        @Enumerated(EnumType.STRING)
        @Column(name = "status_order")
        private StatusPurchaseOrder status;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        @Column(name = "total_price")
        private double totalPrice;

        @ManyToMany
        @JoinTable(
                name = "order_product",
                joinColumns = { @JoinColumn(name = "order_id") },
                inverseJoinColumns = { @JoinColumn(name = "product_id") }
        )
        private List<Product> productList;

        @ManyToOne
        @JoinColumn(name = "client_id", referencedColumnName = "id")
        private Client clientId;

        @ManyToOne
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address addressId;

}
