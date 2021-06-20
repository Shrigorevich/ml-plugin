package com.shrigorevich.utils;

import com.shrigorevich.Plugin;
import com.shrigorevich.regions.Region;
import com.shrigorevich.regions.RegionManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

public class RegionUtils {

    public static void createOne() {
        Plugin p = Plugin.getInstance();
        Region r = new Region(p.getSessionManager().getLocations().getFirst(), p.getSessionManager().getLocations().getLast());
        p.getRegionManager().addRegion(r);
        p.getDb().saveRegion(r);
    }

    public static void createMatrix(String dimensionX, String dimensionZ) {
        Plugin p = Plugin.getInstance();
        Location startLocation = p.getSessionManager().getLocations().getFirst();
        Location directionLocation = p.getSessionManager().getLocations().getLast();
        int dimX = Integer.parseInt(dimensionX);
        int dimZ = Integer.parseInt(dimensionZ);
        int directionX = startLocation.getBlockX() > directionLocation.getBlockX() ? -1 : 1;
        int directionZ = startLocation.getBlockZ() > directionLocation.getBlockZ() ? -1 : 1;
        int squareSize = p.getConfig().getInt("SQUARE_SIZE");
        int matrixGap = p.getConfig().getInt("MATRIX_GAP");
        for(int i = 0; i < dimX; i++) {
            for(int j = 0; j < dimZ; j++) {
                int offsetX = i * squareSize + i * matrixGap;
                int offsetZ = j * squareSize + j * matrixGap;
                Location l1 = startLocation.clone().add(offsetX * directionX, 0, offsetZ * directionZ);
                Location l2 = startLocation.clone().add((offsetX + squareSize-1) * directionX , 0, (offsetZ + squareSize-1) * directionZ);
                Region r = new Region(l1, l2);
                p.getRegionManager().addRegion(r);
                p.getDb().saveRegion(r);
            }
        }
    }

    public static void checkRegions() {
        RegionManager m = Plugin.getInstance().getRegionManager();
        for(Region reg : m.getAll()) {
            Location[] corners = reg.getSquare().getRegionCorners();
            for(Location l : corners) {
                //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
                l.getBlock().setType(Material.STONE);
            }
        }
    }

    public static void removeStone() {
        RegionManager m = Plugin.getInstance().getRegionManager();
        for(Region reg : m.getAll()) {
            Location[] corners = reg.getSquare().getRegionCorners();
            for(Location l : corners) {
                //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
                l.add(0, -1, 0).getBlock().setType(Material.AIR);
            }
        }
    }
}
