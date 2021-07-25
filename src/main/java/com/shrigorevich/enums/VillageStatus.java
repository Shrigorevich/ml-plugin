package com.shrigorevich.enums;

public enum VillageStatus {
    DRAFT("DRAFT"),
    LOCATED("LOCATED"),
    ACTIVE("ACTIVE");

    private final String status;

    VillageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
