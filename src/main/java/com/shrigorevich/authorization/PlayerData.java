package com.shrigorevich.authorization;

import com.shrigorevich.skins.SkinType;
import org.bson.Document;

public class PlayerData {
    private String nickname;
    private SkinType skin;

    public PlayerData(Document doc) {
        this.nickname = doc.getString("nickname");
        this.skin = SkinType.valueOf(doc.getString("skin"));
    }

    public String getName() {
        return nickname;
    }
}