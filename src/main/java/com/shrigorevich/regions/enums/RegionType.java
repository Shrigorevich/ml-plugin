package com.shrigorevich.regions.enums;

public enum RegionType {
    CIVIL("Civil"),
    ADMIN("Admin"),
    GUILD("Guild");

    public final String label;

    private RegionType(String label){
        this.label = label;
    }
}
