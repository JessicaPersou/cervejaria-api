package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.enums.PaymentMethod;
import br.com.jps.cervejariaapi.enums.StatusPurchaseOrder;
import br.com.jps.cervejariaapi.model.Product;
import br.com.jps.cervejariaapi.model.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseOrderDTO {

    private Long id;
    private PaymentMethod paymentMethod;
    private LocalDate dtOrder;
    private StatusPurchaseOrder status;
    private int quantity;
    private double totalPrice;
    private List<ProductDTO> productList = new ArrayList<>();
    private Long clientId;
    private Long addressId;

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.paymentMethod = purchaseOrder.getPaymentMethod();
        this.dtOrder = purchaseOrder.getDtOrder();
        this.status = purchaseOrder.getStatus();
        this.quantity = purchaseOrder.getQuantity();
        this.totalPrice = purchaseOrder.getTotalPrice();
        this.clientId = purchaseOrder.getClientId().getId();
        this.addressId = purchaseOrder.getAddressId().getId();

        productList.forEach(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(item.getName());
            productDTO.setDescription(item.getDescription());
            productDTO.setPrice(item.getPrice());
            productDTO.setCategoryId(item.getCategoryId());
            productDTO.setId(item.getId());
            this.productList.add(productDTO);
        });
    }

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder, List<Product> productsList) {
        this.id = purchaseOrder.getId();
        this.paymentMethod = purchaseOrder.getPaymentMethod();
        this.dtOrder = purchaseOrder.getDtOrder();
        this.status = purchaseOrder.getStatus();
        this.quantity = purchaseOrder.getQuantity();
        this.totalPrice = purchaseOrder.getTotalPrice();
        this.clientId = purchaseOrder.getClientId().getId();
        this.addressId = purchaseOrder.getAddressId().getId();
        this.productList = new ArrayList<>();

        productsList.forEach(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(item.getName());
            productDTO.setDescription(item.getDescription());
            productDTO.setPrice(item.getPrice());
            productDTO.setCategoryId(item.getCategoryId().getId());
            productDTO.setId(item.getId());
            this.productList.add(productDTO);
        });
    }
}
