package com.shrigorevich.regions.enums;

public enum VillageType {
    FIRST("First"),
    SECOND("Second");

    private final String type;

    VillageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
