package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long id;
    private int quantityTotal;
    private String name;
    private Long productId;

    public StockDTO(Stock stock) {
        this.id = stock.getId();
        this.quantityTotal = stock.getQuantityTotal();
        this.name = stock.getName();
        this.productId = stock.getProductId().getId();
    }
}
