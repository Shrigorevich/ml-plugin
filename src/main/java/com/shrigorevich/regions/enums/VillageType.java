package com.shrigorevich.regions.enums;

public enum VillageType {
    FIRST("FIRST"),
    SECOND("SECOND");

    private final String type;

    VillageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
