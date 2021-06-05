package com.shrigorevich.authorization;

import org.bson.Document;

public class PlayerData {
    private String nickname;

    public PlayerData(Document doc) {
        this.nickname = doc.getString("nickname");
    }

    public String getName() {
        return nickname;
    }
}