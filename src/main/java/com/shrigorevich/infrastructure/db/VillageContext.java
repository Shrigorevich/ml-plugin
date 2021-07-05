package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shrigorevich.infrastructure.mappers.VillageMapper;
import com.shrigorevich.landRegistry.villages.Village;
import org.bson.Document;

import java.util.ArrayList;

public class VillageContext {

    private MongoCollection<Document> villages;

    public VillageContext(MongoDatabase database) {
        this.villages = database.getCollection("villages");
    }

    public ArrayList<Village> getVillages() {

        MongoCursor<Document> cursor = villages.find().iterator();
        ArrayList<Village> villages = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                villages.add(VillageMapper.unpackData(cursor.next()));
            }
        } finally {
            cursor.close();
        }

        return villages;
    }

    public void saveVillage(Village village) {
        System.out.println("Save village");
        villages.insertOne(VillageMapper.packData(village));
    }
}
