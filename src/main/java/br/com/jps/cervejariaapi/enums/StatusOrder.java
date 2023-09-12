package br.com.jps.cervejariaapi.enums;

public enum StatusOrder {
    PENDENT("Pendent"),
    APPROVED("Approved"),
    DELIVERED("Delivered");

    private final String label;

    StatusOrder(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
