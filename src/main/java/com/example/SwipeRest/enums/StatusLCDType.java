package com.example.SwipeRest.enums;

public enum StatusLCDType {
    APARTMENT("Квартиры"), CONSTRUCTION("Строится");
    private String value;

    StatusLCDType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
