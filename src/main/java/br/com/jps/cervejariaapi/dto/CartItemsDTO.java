package br.com.jps.cervejariaapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemsDTO {

    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
