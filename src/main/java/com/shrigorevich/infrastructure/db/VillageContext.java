package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shrigorevich.enums.VillageStatus;
import com.shrigorevich.infrastructure.mappers.AreaMapper;
import com.shrigorevich.infrastructure.mappers.VillageMapper;
import com.shrigorevich.landRegistry.villages.Village;
import com.shrigorevich.landRegistry.villages.VillageArea;
import org.bson.Document;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class VillageContext {

    private MongoCollection<Document> villages;

    public VillageContext(MongoDatabase database) {
        this.villages = database.getCollection("villages");
    }

    public Village getActiveVillage() {
        Document filter = new Document("status", VillageStatus.ACTIVE.getStatus());
        Document doc = villages.find(filter).first();

        if(doc != null) {
            return VillageMapper.unpackData(doc);
        }
        return null;
    }

    public void saveVillage(Village village) {
        System.out.println("Save village");
        villages.insertOne(VillageMapper.packData(village));
    }

    public void villageSetLocation(String villageName, VillageArea area, int dimensionX, int dimensionZ) {
        Document filter = new Document("name", villageName);

        villages.updateOne(filter, combine(
                set("area", AreaMapper.packData(area)),
                set("dimensionX", dimensionX),
                set("dimensionZ", dimensionZ),
                set("status", VillageStatus.LOCATED.getStatus())
            ));
    }

    public boolean checkVillage(String villageName) {
        Document filter = new Document("name", villageName);
        Document doc = villages.find(filter).first();
        if (doc == null) {
            return false;
        } else {
            return true;
        }
    }
}
