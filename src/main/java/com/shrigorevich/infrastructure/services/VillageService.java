package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.enums.VillageStatus;
import com.shrigorevich.infrastructure.db.VillageContext;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.landRegistry.villages.Village;
import com.shrigorevich.landRegistry.villages.VillageArea;

public class VillageService {

    private Village village;
    private VillageContext villageContext;

    public VillageService(MongoDatabase dataBase) {
        this.villageContext = new VillageContext(dataBase);
        village = null;
    }

    public boolean isVillageExistDB(String villageName) {
        return villageContext.checkVillage(villageName);
    }

    public Village getVillage() {
        return village;
    }

    public Village loadActiveVillage() {
        return villageContext.getActiveVillage();
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public void villageSetLocation(String villageName, VillageArea area, MatrixCell[][] matrix) {
        int dimensionX = matrix.length;
        int dimensionZ = matrix[0].length;

        villageContext.villageSetLocation(
            villageName,
            area,
            dimensionX,
            dimensionZ
        );
    }
}
