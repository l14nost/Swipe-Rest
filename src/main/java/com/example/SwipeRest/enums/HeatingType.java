package com.example.SwipeRest.enums;

public enum HeatingType {
    AUTONOMOUS("Автономное"), INDIVIDUAL("Индивидуальное"),CENTRAL("Центральное");
    private String value;

    HeatingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
