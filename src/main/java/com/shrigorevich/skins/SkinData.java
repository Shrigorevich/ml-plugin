package com.shrigorevich.skins;

import org.bson.Document;

public class SkinData {

    private final String value;
    private final String signature;

    public SkinData(Document doc) {
        this.value = doc.getString("value");
        this.signature = doc.getString("signature");
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }
}
