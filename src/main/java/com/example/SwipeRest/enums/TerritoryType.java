package com.example.SwipeRest.enums;

public enum TerritoryType {
    CLOSE("Закрытая территория"), OPEN("Открытая территория");
    private String value;

    TerritoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
