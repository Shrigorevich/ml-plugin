package com.shrigorevich.villages.enums;

public enum CellType {
    DEFAULT("DEFAULT"),
    CIVIL("CIVIL"),
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
