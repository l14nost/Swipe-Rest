package com.example.SwipeRest.enums;

public enum LCDType {
    MANY("Многоквартирный"), FIVE("Пятиэтажка");
    private String value;

    LCDType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
