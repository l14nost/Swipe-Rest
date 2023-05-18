package com.example.SwipeRest.enums;

public enum CommunicationType {
    CALLSMS("Звонок + сообщение"),CALL("Звонок"), SMS("Сообщение");
    private String value;

    CommunicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
