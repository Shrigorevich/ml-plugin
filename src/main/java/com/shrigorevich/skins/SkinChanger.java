package com.shrigorevich.skins;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.shrigorevich.Plugin;
import org.bson.Document;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SkinChanger {

    private Map<SkinType, SkinData> skins = new ConcurrentHashMap();

    public SkinChanger() {
        ArrayList<Document> skinDocs = Plugin.getInstance().getSkinContext().getSkins();

        for(Document doc : skinDocs) {
            skins.put(SkinType.valueOf(doc.getString("skinType")), new SkinData(doc));
        }
    }

    public void applySkin(Player player) {

        Plugin p = Plugin.getInstance();
        SkinType playerSkin = p.getPlayerManager().getPlayer(player.getName()).getSkin();

        if (playerSkin.equals(SkinType.DEFAULT)) return;

        GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
        PropertyMap pm = profile.getProperties();
        Property property = pm.get("textures").iterator().next();

        if (property != null) pm.remove("textures", property);

        switch (playerSkin) {
            case FIRST:
                applyFirst(pm);
                break;
            case SECOND:
                applySecond(pm);
                break;
            default:
                break;
        }
    }

    private void applyFirst(PropertyMap pm) {
        pm.put("textures", new Property("textures",
            skins.get(SkinType.FIRST).getValue(),
            skins.get(SkinType.FIRST).getSignature())
        );
    }

    private void applySecond(PropertyMap pm) {

    }

}
