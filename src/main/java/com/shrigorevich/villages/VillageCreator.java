package com.shrigorevich.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.villages.enums.VillageType;
import com.shrigorevich.villages.square.MatrixCell;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;

public class VillageCreator {

    private Village village;

    public VillageCreator() { }

    public void create(Player player, String name) {
        if(village == null) {
            if(!Plugin.getInstance().getVillageManager().contains(name)) {
                village = new Village();
                village.setName(name);
            } else {
                player.sendMessage("A village with this name already existed");
            }
        } else {
            player.sendMessage("Complete or clear the current creation process");
        }
    }

    public void defineMatrix(Player player) {
        Plugin p = Plugin.getInstance();

        if(village != null) {
            if(p.getSessionManager().getLocations().size() != 2) {
                player.sendMessage(ChatColor.RED + "Define both coordinates!");
            } else {
                int dimX = p.getConfig().getInt("MATRIX_DIM_X");
                int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");

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
                village.setMatrix(matrix);
            }
        } else {
            player.sendMessage(ChatColor.RED + "Village with that name does not exist!");
        }
    }

    public void save(Player player) {
        Plugin p = Plugin.getInstance();

        if (village == null) {
            player.sendMessage("Please create a village first");
        } else if (village.getMatrix() == null) {
            player.sendMessage("Village matrix does not defined");
        } else {
            MatrixCell[][] matrix = village.getMatrix();
            saveMatrixToDB(matrix, village.getName());
            int i = matrix.length - 1;
            int j = matrix[0].length - 1;

            Location l1 = matrix[0][0].getLowerNE();
            Location l2 = matrix[i][j].getUpperSW();
            village.setArea(l1, l2);

            p.getVillageManager().addVillage(village);
            p.getDb().saveVillage(village);
            village = null;
        }
    }

    private void saveMatrixToDB(MatrixCell[][] matrix, String villageName) {
        Plugin p = Plugin.getInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                p.getDb().saveCell(villageName, new CellAddress(i, j), matrix[i][j]);
            }
        }
    }

    public void reset() {
        village = null;
    }
}
