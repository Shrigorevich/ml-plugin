package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class SkinContext {

    private MongoCollection<Document> skins;

    public SkinContext(MongoDatabase database) {
        this.skins = database.getCollection("skins");
    }

    public ArrayList<Document> getSkins() {
        MongoCursor<Document> cursor = skins.find().iterator();
        ArrayList<Document> skins = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                skins.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return skins;
    }
}
