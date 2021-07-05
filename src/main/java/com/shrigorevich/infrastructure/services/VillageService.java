package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.infrastructure.db.VillageContext;
import com.shrigorevich.landRegistry.villages.Village;
import com.shrigorevich.state.VillageManager;

import java.util.ArrayList;
import java.util.Collection;

public class VillageService {

    private VillageManager villageManager;
    private VillageContext villageContext;

    public VillageService(MongoDatabase dataBase) {
        this.villageManager = new VillageManager();
        this.villageContext = new VillageContext(dataBase);
    }

    public boolean isVillageExist(String villageName) {
        return villageManager.contains(villageName);
    }

    public void saveNewVillage(Village village) {
        villageManager.addVillage(village);
        villageContext.saveVillage(village);
    }

    public Collection<Village> getVillages() {
        return villageManager.getVillages();
    }

    public Village getVillage(String villageName) {
        return villageManager.getVillage(villageName);
    }

    public ArrayList<Village> loadVillages() {
        return villageContext.getVillages();
    }
}
