package com.shrigorevich.authorization;

import com.shrigorevich.skins.SkinType;
import org.bson.Document;
import org.bukkit.ChatColor;

public class UserData {
    private String nickname;
    private SkinType skin;
    private String village;

    public UserData(String name, SkinType skinType, String villageName) {
        this.nickname = name;
        this.skin = skinType;
        this.village = villageName;
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