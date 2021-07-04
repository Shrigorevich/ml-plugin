package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.landRegistry.lands.Square;
import org.bson.Document;
import org.bukkit.Location;

public class VillageArea extends Square {

    public VillageArea(Location l1, Location l2) {
        super(l1, l2);
    }

    public VillageArea(String worldName, int x1, int x2, int z1, int z2) {
        super(worldName, x1, x2, z1, z2);
    }

    public boolean contains(int x, int z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Location l) {
        return this.contains(l.getBlockX(), l.getBlockZ());
    }
}
