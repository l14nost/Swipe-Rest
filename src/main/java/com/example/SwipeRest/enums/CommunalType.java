package com.example.SwipeRest.enums;

public enum CommunalType {
    PAYMENTS("Платежи"), HALF("Частичное");
    private String value;

    CommunalType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
