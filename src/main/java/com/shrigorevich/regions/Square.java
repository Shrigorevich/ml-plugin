package com.shrigorevich.regions;

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

    public Square expand(Cuboid.CuboidDirection dir, int amount) {
        switch (dir) {
            case North:
                return new Square(this.worldName, this.x1 - amount, this.z1, this.x2, this.z2);
            case South:
                return new Square(this.worldName, this.x1, this.z1, this.x2 + amount, this.z2);
            case East:
                return new Square(this.worldName, this.x1, this.z1 - amount, this.x2, this.z2);
            case West:
                return new Square(this.worldName, this.x1, this.z1, this.x2, this.z2 + amount);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public boolean contains(int x, int z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Block b) {
        return this.contains(b.getLocation());
    }

    public boolean contains(Location l) {
        if (!this.worldName.equals(l.getWorld().getName())) return false;
        return this.contains(l.getBlockX(), l.getBlockZ());
    }
}
