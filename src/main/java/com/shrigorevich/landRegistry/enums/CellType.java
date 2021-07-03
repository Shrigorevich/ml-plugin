package com.shrigorevich.landRegistry.enums;

public enum CellType {
    DEFAULT("DEFAULT"),
    CIVIL("CIVIL"),
    ADMIN("ADMIN"),
    GUILD("GUILD");

    private final String typeName;

    CellType(String type) {
        this.typeName = type;
    }

    public String getTypeName() {
        return typeName;
    }
}
