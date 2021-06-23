package com.shrigorevich.villages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VillageManager {

    private final Map<String, Village> villages;

    public VillageManager() {
        villages = new ConcurrentHashMap<>();
    }

    public void addVillage(Village village) {
        villages.put(village.getName(), village);
    }

    public Collection<Village> getVillages() {
        return villages.values();
    }

    public boolean contains(String villageName) {
        return villages.containsKey(villageName);
    }


    public Village getVillage(String name) {
        return villages.get(name);
    }
}
