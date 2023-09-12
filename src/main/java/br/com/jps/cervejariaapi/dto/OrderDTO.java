package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.enums.PaymentMethod;
import br.com.jps.cervejariaapi.enums.StatusOrder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {

    private Long id;
    private PaymentMethod paymentMethod;
    private Timestamp dtOrder;
    private StatusOrder statusOrder;
    private Long clientId;
    private Long addressId;
}
