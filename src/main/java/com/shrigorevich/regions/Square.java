package com.shrigorevich.regions;

import com.shrigorevich.Plugin;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Square {
    protected final String worldName;
    protected final int x1, z1;
    protected final int x2, z2;

    public Square(Location l1, Location l2) {
        if (!l1.getWorld().equals(l2.getWorld())) throw new IllegalArgumentException("Locations must be on the same world");
        this.worldName = l1.getWorld().getName();
        this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    private Square(String worldName, int x1, int z1, int x2, int z2) {
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    public Square(Location l1) {
        this(l1, l1);
    }

    public Square(Square other) {
        this(other.getWorld().getName(), other.x1, other.z1, other.x2, other.z2);
    }

    public Square(Document doc) {
        this.worldName = doc.getString("worldName");
        this.x1 = doc.getInteger("x1");
        this.x2 = doc.getInteger("x2");
        this.z1 = doc.getInteger("z1");
        this.z2 = doc.getInteger("z2");
    }

    public World getWorld() {
        World world = Bukkit.getWorld(this.worldName);
        if (world == null) throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        return world;
    }

    public int getSizeX() {
        return (this.x2 - this.x1) + 1;
    }

    public int getSizeY() {
        return (this.z2 - this.z1) + 1;
    }

    public boolean contains(int x, int z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Location l) {
        if (!this.worldName.equals(l.getWorld().getName())) return false;
        return this.contains(l.getBlockX(), l.getBlockZ());
    }

    public Location getLowerNE() {
        return this.getWorld().getHighestBlockAt(this.x1, this.z1).getLocation();
    }

    public Location[] getRegionCorners() {
        Location[] res = new Location[4];
        World w = this.getWorld();
        res[0] = w.getHighestBlockAt(this.x1, this.z1).getLocation().add(0,1,0);
        res[1] = w.getHighestBlockAt(this.x1, this.z2).getLocation().add(0,1,0);
        res[2] = w.getHighestBlockAt(this.x2, this.z2).getLocation().add(0,1,0);
        res[3] = w.getHighestBlockAt(this.x2, this.z1).getLocation().add(0,1,0);
        return res;
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("worldName", worldName);
        doc.append("x1", x1);
        doc.append("x2", x2);
        doc.append("z1", z1);
        doc.append("z2", z2);

        return doc;
    }
}
