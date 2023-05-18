package com.example.SwipeRest.enums;

public enum ClassType {
    ELITE("Элитный"), BUSINESS("Бизнес"),MASS("Массовый");
    private String value;

    ClassType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
