package com.shrigorevich.utils;

import com.shrigorevich.Plugin;
import com.shrigorevich.regions.MatrixCell;
import com.shrigorevich.regions.Village;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

public class VillageUtil {

    public static void checkCell(String villageName, int x, int y) {
        MatrixCell cell = Plugin.getInstance().getVillageManager().getVillage(villageName).getMatrix()[x][y];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.STONE);
        }
    }

    public static void checkCells(String villageName) {
        Village village = Plugin.getInstance().getVillageManager().getVillage(villageName);
        MatrixCell[][] matrix = village.getMatrix();
        for(MatrixCell[] row : matrix) {
            for(MatrixCell cell : row) {
                Location[] corners = cell.getSquareCorners();
                for(Location l : corners) {
                    l.getBlock().setType(Material.STONE);
                }
            }
        }
    }

    public static void clearCell(String villageName, int x, int y) {
        MatrixCell cell = Plugin.getInstance().getVillageManager().getVillage(villageName).getMatrix()[x][y];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.AIR);
        }
    }

    public static void clearCells(String villageName) {
        MatrixCell[][] matrix = Plugin.getInstance().getVillageManager().getVillage(villageName).getMatrix();
        for(MatrixCell[] row : matrix) {
            for(MatrixCell cell : row) {
                Location[] corners = cell.getSquareCorners();
                for(Location l : corners) {
                    l.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
