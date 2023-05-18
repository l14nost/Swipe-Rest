package com.example.SwipeRest.enums;

public enum TypeNotification {
    ME("me"),
    MEANDAGENT("meandagent"),
    AGENT("agent"),
    OFF("off");
    private String title;

    TypeNotification(String title) {
        this.title = title;
    }
}
