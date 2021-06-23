package com.shrigorevich.authorization;

import com.shrigorevich.skins.SkinType;
import org.bson.Document;
import org.bukkit.ChatColor;

public class PlayerData {
    private String nickname;
    private SkinType skin;
    private String village;

    public PlayerData(Document doc) {
        this.nickname = doc.getString("nickname");
        this.skin = SkinType.valueOf(doc.getString("skin"));
        this.village = doc.getString("village");
    }

    public String getName() {
        return nickname;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public SkinType getSkin() {
        return skin;
    }
}