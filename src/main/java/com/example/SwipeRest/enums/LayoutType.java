package com.example.SwipeRest.enums;

public enum LayoutType {
    CLASSICAL("Классический"),STUDIO("Студия"),FREE("Свободная");
    private String value;

    LayoutType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
