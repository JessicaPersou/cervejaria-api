package br.com.jps.cervejariaapi.enums;

public enum UserRole {
    USER("User"),
    ADMIN("Admin"),
    GUEST("Guest");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
