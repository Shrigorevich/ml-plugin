package com.shrigorevich.utils;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.landRegistry.villages.Village;
import org.bukkit.Location;
import org.bukkit.Material;

public class VillageUtil {

    public static void checkCell( int x, int y) {
        MatrixCell cell = Plugin.getInstance().getMatrixService().getMatrix()[x][y];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.STONE);
        }
    }

    public static void checkCells() {
        Plugin p = Plugin.getInstance();

        MatrixCell[][] matrix = p.getMatrixService().getMatrix();
        for(MatrixCell[] row : matrix) {
            for(MatrixCell cell : row) {
                Location[] corners = cell.getSquareCorners();
                for(Location l : corners) {
                    l.getBlock().setType(Material.STONE);
                }
            }
        }
    }

    public static void clearCell(String villageName, int x, int z) {
        MatrixCell cell = Plugin.getInstance().getMatrixService().getMatrix()[x][z];
        Location[] corners = cell.getSquareCorners();
        for(Location l : corners) {
            //player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0,0);
            l.getBlock().setType(Material.AIR);
        }
    }

    public static void clearCells() {
        MatrixCell[][] matrix = Plugin.getInstance().getMatrixService().getMatrix();
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
