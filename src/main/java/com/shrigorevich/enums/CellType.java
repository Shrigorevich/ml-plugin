package com.shrigorevich.enums;

public enum CellType {
    CIVIL("CIVIL"),
    ADMIN("ADMIN"),
    WALL("WALL"),
    UNION("UNION");

    private final String typeName;

    CellType(String type) {
        this.typeName = type;
    }

    public String getTypeName() {
        return typeName;
    }
}
