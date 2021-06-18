package com.shrigorevich.authorization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    private final Map<String, PlayerData> cache = new ConcurrentHashMap<>();

    public int getCurrentOnline() {
        return cache.size();
    }

    public void addPlayer(PlayerData p) {
        cache.put(p.getName(), p);
    }

    public void updatePlayer(PlayerData p) {
        cache.put(p.getName(), p);
    }

    public void removePlayer(String user) {
        cache.remove(user);
    }

    public boolean isAuthenticated(String user) {
        return cache.containsKey(user);
    }

    public Map<String, PlayerData> getCache() {
        return this.cache;
    }
}
