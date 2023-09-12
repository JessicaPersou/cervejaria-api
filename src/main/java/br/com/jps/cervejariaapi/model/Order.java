package br.com.jps.cervejariaapi.model;

import br.com.jps.cervejariaapi.enums.PaymentMethod;
import br.com.jps.cervejariaapi.enums.StatusOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
public class Order {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(name = "payment_method", nullable = false)
        @NotBlank(message = "Campo não pode ser vazio ou nulo")
        private PaymentMethod paymentMethod;

        @Column(name = "dt_order", nullable = false)
        @NotBlank(message = "Campo não pode ser vazio ou nulo")
        private Timestamp dtOrder;

        @Enumerated(EnumType.STRING)
        @Column(name = "status_order", nullable = false)
        @NotBlank(message = "Campo não pode ser vazio ou nulo")
        private StatusOrder statusOrder;

        @ManyToOne
        @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
        @NotBlank(message = "Campo não pode ser vazio ou nulo")
        private Client clientId;

        @ManyToOne
        @JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
        @NotBlank(message = "Campo não pode ser vazio ou nulo")
        private Address addressId;

}
