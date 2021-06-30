package com.shrigorevich.skins;

public enum SkinType {
    DEFAULT("DEFAULT"),
    FIRST("FIRST"),
    SECOND("SECOND");

    private final String skinName;

    SkinType(String skinName) {
        this.skinName = skinName;
    }
}
