package com.shrigorevich.regions.enums;

public enum CellType {
    CIVIL("CIVIL"),
    ROAD("ROAD"),
    ADMIN("ADMIN"),
    GUILD("GUILD");

    private final String type;

    CellType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
