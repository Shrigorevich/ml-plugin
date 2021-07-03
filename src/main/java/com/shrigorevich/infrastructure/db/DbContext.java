package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DbContext {
    private MongoClient mc;
    private MongoDatabase database;

    public DbContext() {
        //TODO: USE CONFIG PARAMS
        mc = MongoClients.create("mongodb://localhost:27017");
        database = mc.getDatabase("medievalife");
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
