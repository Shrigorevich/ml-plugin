package com.shrigorevich.authorization;

import org.bson.Document;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    private final Map<String, PlayerData> cache;

    public PlayerManager() {
        cache = new ConcurrentHashMap<>();
    }

    public void addPlayer(Document doc) {
        PlayerData p = new PlayerData(doc);
        cache.put(p.getName(), p);
    }

    public void removePlayer(String user) {
        cache.remove(user);
    }

    public boolean isAuthenticated(String user) {
        return cache.containsKey(user);
    }

    public int getCurrentOnline() {
        return cache.size();
    }

    public PlayerData getPlayer(String name) {
        return cache.get(name);
    }

    public Map<String, PlayerData> getCache() {
        return this.cache;
    }
}
