package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shrigorevich.landRegistry.villages.Village;
import org.bson.Document;

import java.util.ArrayList;

public class VillageContext {

    private MongoCollection<Document> villages;

    public VillageContext(MongoDatabase database) {
        this.villages = database.getCollection("villages");
    }

    public ArrayList<Document> getVillages() {

        MongoCursor<Document> cursor = villages.find().iterator();
        ArrayList<Document> villages = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                villages.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return villages;
    }

    public void saveVillage(Village village) {
        villages.insertOne(village.packData());
    }
}
