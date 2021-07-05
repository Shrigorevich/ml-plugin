package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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

    public void createFromConsole(String name) {
        if(village == null) {
            if(!Plugin.getInstance().getVillageManager().contains(name)) {
                village = new Village();
                village.setName(name);
            } else {
                System.out.println("Manager already contains village");
            }
        } else {
            System.out.println("Village already exist");
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
                int cellSize = p.getConfig().getInt("CELL_SIZE");

                MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];

                Location areaStartCorner = p.getSessionManager().getLocations().getFirst();
                Location directionLocation = p.getSessionManager().getLocations().getLast();

                int areaDirectionX = areaStartCorner.getBlockX() > directionLocation.getBlockX() ? -1 : 1;
                int areaDirectionZ = areaStartCorner.getBlockZ() > directionLocation.getBlockZ() ? -1 : 1;

                Location areaOppositeCorner = areaStartCorner.clone()
                        .add((dimX*cellSize-1) * areaDirectionX, 0, (dimZ*cellSize-1) * areaDirectionZ);

                VillageArea villageArea = new VillageArea(areaStartCorner, areaOppositeCorner);

                int cellDirectionX = villageArea.getLowerNW().getBlockX() > villageArea.getUpperSE().getBlockX() ? -1 : 1;
                int cellDirectionZ = villageArea.getLowerNW().getBlockZ() > villageArea.getUpperSE().getBlockZ() ? -1 : 1;

                for(int i = 0; i < dimX; i++) {
                    for(int j = 0; j < dimZ; j++) {
                        int offsetX = i * cellSize;
                        int offsetZ = j * cellSize;

                        Location l1 = villageArea.getLowerNW().clone()
                                .add(offsetX * cellDirectionX, 0, offsetZ * cellDirectionZ);
                        Location l2 = l1.clone()
                                .add((cellSize-1) * cellDirectionX , 0, (cellSize-1) * cellDirectionZ);

                        MatrixCell mc = new MatrixCell(l1, l2);
                        matrix[i][j] = mc;
                    }
                }
                village.setMatrix(matrix);
                village.setArea(villageArea);
            }
        } else {
            player.sendMessage(ChatColor.RED + "Village with that name does not exist!");
        }
    }

    public void defineMatrixFromConsole() {
        Plugin p = Plugin.getInstance();

        if(village != null) {
            int dimX = p.getConfig().getInt("MATRIX_DIM_X");
            int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");
            int cellSize = p.getConfig().getInt("CELL_SIZE");

            MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];

            Location areaStartCorner = new Location(Bukkit.getWorld("world"), 0, 0, 0);
            Location directionLocation = new Location(Bukkit.getWorld("world"), 1, 0, 1);

            int areaDirectionX = areaStartCorner.getBlockX() > directionLocation.getBlockX() ? -1 : 1;
            int areaDirectionZ = areaStartCorner.getBlockZ() > directionLocation.getBlockZ() ? -1 : 1;

            Location areaOppositeCorner = areaStartCorner.clone()
                    .add((dimX*cellSize-1) * areaDirectionX, 0, (dimZ*cellSize-1) * areaDirectionZ);

            VillageArea villageArea = new VillageArea(areaStartCorner, areaOppositeCorner);

            int cellDirectionX = villageArea.getLowerNW().getBlockX() > villageArea.getUpperSE().getBlockX() ? -1 : 1;
            int cellDirectionZ = villageArea.getLowerNW().getBlockZ() > villageArea.getUpperSE().getBlockZ() ? -1 : 1;

            for(int i = 0; i < dimX; i++) {
                for(int j = 0; j < dimZ; j++) {
                    int offsetX = i * cellSize;
                    int offsetZ = j * cellSize;

                    Location l1 = villageArea.getLowerNW().clone()
                            .add(offsetX * cellDirectionX, 0, offsetZ * cellDirectionZ);
                    Location l2 = l1.clone()
                            .add((cellSize-1) * cellDirectionX , 0, (cellSize-1) * cellDirectionZ);

                    MatrixCell mc = new MatrixCell(l1, l2);
                    matrix[i][j] = mc;
                }
            }
            village.setMatrix(matrix);
            village.setArea(villageArea);
            System.out.println(matrix.length * matrix[0].length);
        } else {
            System.out.println("Village is null");
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
            p.getVillageManager().addVillage(village);
            p.getVillageContext().saveVillage(village);
            village = null;
        }
    }

    public void saveFromConsole() {
        Plugin p = Plugin.getInstance();

        if (village == null) {
            System.out.println("Village is null");
        } else if (village.getMatrix() == null) {
            System.out.println("Matrix is null");
        } else {
            MatrixCell[][] matrix = village.getMatrix();
            saveMatrixToDB(matrix, village.getName());
            p.getVillageManager().addVillage(village);
            p.getVillageContext().saveVillage(village);
            village = null;
        }
    }

    private void saveMatrixToDB(MatrixCell[][] matrix, String villageName) {
        Plugin p = Plugin.getInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                p.getMatrixCellContext().saveCell(villageName, new CellAddress(i, j), matrix[i][j]);
            }
        }
    }

    public void reset() {
        village = null;
    }
}
