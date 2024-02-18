package br.com.jps.cervejariaapi.enums;

import lombok.Getter;

@Getter
public enum StatusPurchaseOrder {
    PENDENT("Pendent"),
    APPROVED("Approved"),
    DELIVERED("Delivered");

    private final String label;

    StatusPurchaseOrder(String label) {
        this.label = label;
    }

}
