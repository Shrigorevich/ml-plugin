package com.shrigorevich.utils;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.landRegistry.villages.Village;
import org.bukkit.Location;
import org.bukkit.Material;

public class VillageUtil {

    public static void checkCell(String villageName, int x, int y) {
        MatrixCell cell = Plugin.getInstance().getCellService().getMatrix(villageName)[x][y];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.STONE);
        }
    }

    public static void checkCells(String villageName) {
        Plugin p = Plugin.getInstance();
        Village village = p.getVillageService().getVillage(villageName);

        if(village != null) {
            MatrixCell[][] matrix = p.getCellService().getMatrix(villageName);
            for(MatrixCell[] row : matrix) {
                for(MatrixCell cell : row) {
                    Location[] corners = cell.getSquareCorners();
                    for(Location l : corners) {
                        l.getBlock().setType(Material.STONE);
                    }
                }
            }
        }
    }

    public static void clearCell(String villageName, int x, int y) {
        MatrixCell cell = Plugin.getInstance().getCellService().getMatrix(villageName)[x][y];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.AIR);
        }
    }

    public static void clearCells(String villageName) {
        MatrixCell[][] matrix = Plugin.getInstance().getCellService().getMatrix(villageName);
        for(MatrixCell[] row : matrix) {
            for(MatrixCell cell : row) {
                Location[] corners = cell.getSquareCorners();
                for(Location l : corners) {
                    l.add(0, -1, 0).getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
