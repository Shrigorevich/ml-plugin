package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.infrastructure.db.VillageContext;
import com.shrigorevich.landRegistry.villages.Village;

public class VillageService {

    private Village village;
    private VillageContext villageContext;

    public VillageService(MongoDatabase dataBase) {
        this.villageContext = new VillageContext(dataBase);
        village = null;
    }

    public boolean isVillageExist() {
        return village != null;
    }

    public Village getVillage(String villageName) {
        return village;
    }

    public Village loadVillage() {
        return villageContext.getVillage();
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
