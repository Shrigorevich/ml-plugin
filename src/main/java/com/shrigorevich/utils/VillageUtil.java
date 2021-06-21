package com.shrigorevich.utils;

import com.shrigorevich.Plugin;
import com.shrigorevich.regions.CellAddress;
import com.shrigorevich.regions.MatrixCell;
import com.shrigorevich.regions.Village;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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
        MatrixCell[][] matrix = Plugin.getInstance().getVillageManager().getVillage(villageName).getMatrix();
        for(MatrixCell[] row : matrix) {
            for(MatrixCell cell : row) {
                Location[] corners = cell.getSquareCorners();
                for(Location l : corners) {
                    l.getBlock().setType(Material.STONE);
                }
            }
        }
    }

    public static void createVillage(String name) {
        Plugin p = Plugin.getInstance();

        p.getVillageManager().addVillage(name);
        p.getDb().saveVillage(
            p.getVillageManager().getVillage(name)
        );
    }

    public static void defineMatrix(Player player, String villageName, int dimX, int dimZ) {

        Plugin p = Plugin.getInstance();

        Village specificVillage = p.getVillageManager().getVillage(villageName);

        if(specificVillage != null) {
            if(p.getSessionManager().getLocations().size() != 2) {
                player.sendMessage(ChatColor.RED + "Define both coordinates!");
            } else {
                MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];

                Location startLocation = p.getSessionManager().getLocations().getFirst();
                Location directionLocation = p.getSessionManager().getLocations().getLast();

                int directionX = startLocation.getBlockX() > directionLocation.getBlockX() ? -1 : 1;
                int directionZ = startLocation.getBlockZ() > directionLocation.getBlockZ() ? -1 : 1;
                int cellSize = p.getConfig().getInt("CELL_SIZE");

                for(int i = 0; i < dimX; i++) {
                    for(int j = 0; j < dimZ; j++) {
                        int offsetX = i * cellSize;
                        int offsetZ = j * cellSize;

                        Location l1 = startLocation.clone().add(offsetX * directionX, 0, offsetZ * directionZ);
                        Location l2 = startLocation.clone().add((offsetX + cellSize-1) * directionX , 0, (offsetZ + cellSize-1) * directionZ);

                        MatrixCell mc = new MatrixCell(l1, l2);
                        matrix[i][j] = mc;
                    }
                }
                specificVillage.setMatrix(matrix);
                saveMatrixToDB(matrix, villageName);
            }
        } else {
            player.sendMessage(ChatColor.RED + "Village with that name does not exist!");
        }

    }

    public static void saveMatrixToDB(MatrixCell[][] matrix, String villageName) {
        Plugin p = Plugin.getInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                p.getDb().saveCell(villageName, new CellAddress(i, j), matrix[i][j]);
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
