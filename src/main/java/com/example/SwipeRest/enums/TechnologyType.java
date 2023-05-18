package com.example.SwipeRest.enums;

public enum TechnologyType {
    MONOLITH("Монолит"), PANEL("Панельное");
    private String value;

    TechnologyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
