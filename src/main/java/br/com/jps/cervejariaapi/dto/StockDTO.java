package br.com.jps.cervejariaapi.dto;

import lombok.Data;

@Data
public class StockDTO {

    private Long id;
    private int quantityTotal;
    private String name;
    private Long productId;
}
