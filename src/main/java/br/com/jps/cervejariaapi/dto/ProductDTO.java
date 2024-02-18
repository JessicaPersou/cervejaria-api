package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Long categoryId;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.categoryId = product.getCategoryId().getId();
    }
}
