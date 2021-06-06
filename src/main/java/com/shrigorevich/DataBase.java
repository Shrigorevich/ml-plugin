package com.shrigorevich;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.event.Listener;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class DataBase implements Listener {
    private MongoClient mc;
    private MongoDatabase database;
    private MongoCollection<Document> usersCol;

    public DataBase() {
        mc = MongoClients.create("mongodb://localhost:27017");
        database = mc.getDatabase("medievalife");
        usersCol = database.getCollection("users");
    }

    public boolean isUserRegistered(String name){
        Document doc = usersCol.find(Filters.eq("nickname", name)).first();
        return doc != null;
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
}
