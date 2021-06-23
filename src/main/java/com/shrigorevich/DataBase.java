package com.shrigorevich;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.shrigorevich.regions.CellAddress;
import com.shrigorevich.regions.MatrixCell;
import com.shrigorevich.regions.Village;
import org.bson.Document;
import org.bukkit.event.Listener;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class DataBase implements Listener {
    private MongoClient mc;
    private MongoDatabase database;
    private MongoCollection<Document> users;
    private MongoCollection<Document> cells;
    private MongoCollection<Document> villages;


    public DataBase() {
        mc = MongoClients.create("mongodb://localhost:27017");
        database = mc.getDatabase("medievalife");
        users = database.getCollection("users");
        cells = database.getCollection("cells");
        villages = database.getCollection("villages");

    }

    public ArrayList<Document> getCellsForVillage(String villageName) {

        Document filter = new Document().append("villageName", villageName);
        MongoCursor<Document> cursor = cells.find().filter(filter).iterator();

        ArrayList<Document> cells = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
               cells.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return cells;
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

    public Document getRegisteredUser(String name){
        Document doc = users.find(Filters.eq("nickname", name)).first();
        return doc;
    }

    public Document authPlayer(String name, String password) {
        Document doc = users.find(Filters.eq("nickname", name)).first();
        System.out.println(doc.getString("password"));
        if(BCrypt.checkpw(password, doc.getString("password"))){
            System.out.println("db.authPlayer: successfully");
            return doc;
        } else{
            System.out.println("db.authPlayer: failed");
            return null;
        }
    }

    public void saveVillage(Village village) {
        villages.insertOne(village.packData());
    }

    public void saveCell(String villageName, CellAddress address, MatrixCell cell) {
        Document cellDoc = cell.packData();
        cellDoc.append("villageName", villageName);
        cellDoc.append("address", address.packData());
        cells.insertOne(cellDoc);
    }
}

