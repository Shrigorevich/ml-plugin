package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class VillageCreator {

    private Village village;
    private MatrixCell[][] matrix;
    private LinkedList<Location> locations = new LinkedList<Location>();

    public VillageCreator() { }

    public LinkedList<Location> getLocations() {
        return this.locations;
    }
    public void addLocation(Location l) {
        if(locations.size() == 2) locations.remove();
        locations.add(l);
    }

    public void defineVillage(CommandSender sender, String villageName) {
        Plugin p = Plugin.getInstance();
        if(village == null) {
            if(!p.getVillageService().isVillageExist(villageName)) {
                village = new Village();
                village.setName(villageName);
            } else {
                sender.sendMessage("A village with this name already existed");
            }
        } else {
            sender.sendMessage("Complete or clear the current creation process");
        }
    }

    public void defineMatrix(CommandSender sender) {
        Plugin p = Plugin.getInstance();

        if(village != null) {
            if(locations.size() != 2) {
                sender.sendMessage(ChatColor.RED + "Define both coordinates!");
            } else {
                int dimX = p.getConfig().getInt("MATRIX_DIM_X");
                int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");
                int cellSize = p.getConfig().getInt("CELL_SIZE");

                MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];

                Location areaStartCorner = locations.getFirst();
                Location directionLocation = locations.getLast();

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
                this.matrix = matrix;
                village.setArea(villageArea);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Create village first!");
        }
    }

    public void save(CommandSender sender) {
        Plugin p = Plugin.getInstance();

        if (village == null) {
            sender.sendMessage("Please create a village first");
        } else if (matrix == null) {
            sender.sendMessage("Village matrix does not defined");
        } else {
            p.getVillageService().saveNewVillage(village);
            p.getCellService().saveMatrix(matrix, village.getName());
            village = null;
            matrix = null;
        }
    }

    public void reset() {
        village = null;
        matrix = null;
    }
}
