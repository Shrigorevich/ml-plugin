package com.shrigorevich.regions;

import com.shrigorevich.Plugin;
import org.bson.Document;
import org.bukkit.ChatColor;

import java.util.concurrent.ConcurrentHashMap;

public class VillageManager {

    private final ConcurrentHashMap<String, Village> villages;

    public VillageManager() {
        villages = new ConcurrentHashMap<>();

        Plugin p = Plugin.getInstance();

        for(Document villageDoc : p.getDb().getVillages()) {
            String villageName = villageDoc.getString("name");
            System.out.println(ChatColor.AQUA + villageName);
            villages.put(villageName, new Village(villageDoc));
            MatrixCell[][] matrix = new MatrixCell[0][0];
            for (Document cellDoc : p.getDb().getCellsForVillage(villageName)) {
                Document address = (Document) cellDoc.get("address");
                int i = address.getInteger("i");
                int j = address.getInteger("j");
                System.out.println(ChatColor.AQUA+ " " + i + " " + j);
                matrix[i][j] = new MatrixCell(cellDoc);
            }
        }
    }

    public void addVillage(String name) {
        villages.put(name, new Village(name));
    }

    public Village getVillage(String name) {
        return villages.get(name);
    }
}
