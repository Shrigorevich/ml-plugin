package com.shrigorevich.regions;

import java.util.concurrent.ConcurrentHashMap;

public class VillageManager {

    private final ConcurrentHashMap<String, Village> villages;

    public VillageManager() {
        villages = new ConcurrentHashMap<>();
    }

    public void addVillage(Village village) {
        villages.put(village.getName(), village);
    }

    public ConcurrentHashMap<String, Village> getVillages() {
        return villages;
    }

    public Village getVillage(String name) {
        return villages.get(name);
    }
}
