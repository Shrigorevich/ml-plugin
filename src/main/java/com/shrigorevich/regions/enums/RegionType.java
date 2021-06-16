package com.shrigorevich.regions.enums;

public enum RegionType {
    CIVIL("Civil"),
    ADMIN("Admin"),
    GUILD("Guild");

    private final String type;

    RegionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
