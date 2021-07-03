package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.mongodb.client.model.Filters.eq;

public class UserContext {

    private MongoCollection<Document> users;

    public UserContext(MongoDatabase database) {
        this.users = database.getCollection("users");
    }

    public Document getRegisteredUser(String name){
        Document doc = users.find(eq("nickname", name)).first();
        return doc;
    }

    public Document authPlayer(String name, String password) {
        Document doc = users.find(eq("nickname", name)).first();
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
