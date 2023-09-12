package br.com.jps.cervejariaapi.enums;
public enum PaymentMethod {
    CREDIT("Credit"),
    DEBIT("Debit"),
    PIX("Pix");

    private final String label;

    PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
