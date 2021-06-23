package com.shrigorevich.villages.session;

import org.bukkit.Location;

import java.util.LinkedList;

public class SessionManager {
    private LinkedList<Location> locations = new LinkedList<Location>();

    public SessionManager() {}

    public void addLocation(Location l) {
        if(locations.size() == 2) locations.remove();
        locations.add(l);
    }

    public LinkedList<Location> getLocations() {
        return this.locations;
    }
}
