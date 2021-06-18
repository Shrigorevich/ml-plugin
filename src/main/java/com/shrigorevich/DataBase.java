package com.shrigorevich;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.shrigorevich.regions.Region;
import org.bson.Document;
import org.bukkit.event.Listener;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashSet;


public class DataBase implements Listener {
    private MongoClient mc;
    private MongoDatabase database;
    private MongoCollection<Document> usersCol;
    private MongoCollection<Document> regionCol;


    public DataBase() {
        mc = MongoClients.create("mongodb://localhost:27017");
        database = mc.getDatabase("medievalife");
        usersCol = database.getCollection("users");
        regionCol = database.getCollection("regions");

    }

    public ArrayList<Region> getAllRegions() {
        MongoCursor<Document> cursor = regionCol.find().iterator();
        ArrayList<Region> regions = new ArrayList<Region>();
        try {
            while (cursor.hasNext()) {
               regions.add(new Region(cursor.next()));
            }
        } finally {
            cursor.close();
        }

        return regions;
    }

    public Document getRegisteredUser(String name){
        Document doc = usersCol.find(Filters.eq("nickname", name)).first();
        return doc;
    }

    public Document authPlayer(String name, String password) {
        Document doc = usersCol.find(Filters.eq("nickname", name)).first();
        System.out.println(doc.getString("password"));
        if(BCrypt.checkpw(password, doc.getString("password"))){
            System.out.println("db.authPlayer: successfully");
            return doc;
        } else{
            System.out.println("db.authPlayer: failed");
            return null;
        }
    }

    public void saveRegion(Region region) {
        regionCol.insertOne(region.packData());
    }
}

