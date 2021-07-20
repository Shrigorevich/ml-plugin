package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shrigorevich.infrastructure.mappers.VillageMapper;
import com.shrigorevich.landRegistry.villages.Village;
import org.bson.Document;

public class VillageContext {

    private MongoCollection<Document> villages;

    public VillageContext(MongoDatabase database) {
        this.villages = database.getCollection("villages");
    }

    public Village getVillage() {
        Document doc = villages.find().first();
        return VillageMapper.unpackData(doc);
    }

    public void saveVillage(Village village) {
        System.out.println("Save village");
        villages.insertOne(VillageMapper.packData(village));
    }
}
