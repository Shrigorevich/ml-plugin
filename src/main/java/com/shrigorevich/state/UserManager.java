package com.shrigorevich.state;

import com.shrigorevich.authorization.UserData;
import org.bson.Document;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private final Map<String, UserData> cache;

    public UserManager() {
        cache = new ConcurrentHashMap<>();
    }

    public void addUser(UserData uData) {
        UserData p = uData;
        cache.put(p.getName(), p);
    }

    public void removeUser(String user) {
        cache.remove(user);
    }

    public boolean isAuthenticated(String user) {
        return cache.containsKey(user);
    }

    public int getCurrentOnline() {
        return cache.size();
    }

    public UserData getUser(String name) {
        return cache.get(name);
    }

    public Map<String, UserData> getCache() {
        return this.cache;
    }
}
