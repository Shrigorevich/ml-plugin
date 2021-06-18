package com.shrigorevich.regions.enums;

public enum RegionType {
    CIVIL("CIVIL"),
    ADMIN("ADMIN"),
    GUILD("GUILD");

    private final String type;

    RegionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
