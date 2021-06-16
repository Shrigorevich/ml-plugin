package com.shrigorevich.regions;

import org.bukkit.Location;

import java.util.*;

public class RegionManager {

    private final ArrayList<Region> regions;
    private HashSet<Region> region;

    public RegionManager() {
        regions = new ArrayList<Region>();
    }

    public void addRegion(Region r) {
        regions.add(r);
    }

    public ArrayList<Region> getAll() {
        return regions;
    }

    public Region getRegionByLocation(Location l) {
        return regions.stream()
                .filter(r -> r.getSquare().contains(l))
                .findAny()
                .orElse(null);
    }

    public Region getRegionByOwner(String owner) {
        return regions.stream()
                .filter(r -> r.getOwner().equals(owner))
                .findAny()
                .orElse(null);
    }
}
