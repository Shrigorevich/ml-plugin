package com.shrigorevich.authorization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerCache {
    private final Map<String, PlayerData> cache = new ConcurrentHashMap<>();

    public int getLogged() {
        return cache.size();
    }

    public void addPlayer(PlayerData p) {
        cache.put(p.getName(), p);
    }

    public void updatePlayer(PlayerData p) {
        cache.put(p.getName().toLowerCase(), p);
    }

    public void removePlayer(String user) {
        cache.remove(user.toLowerCase());
    }

    public boolean isAuthenticated(String user) {
        return cache.containsKey(user);
    }

    public Map<String, PlayerData> getInstance() {
        return this.cache;
    }
}
