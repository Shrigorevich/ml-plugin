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

public class MatrixCreator {

    private VillageArea villageArea;
    private MatrixCell[][] matrix;
    private LinkedList<Location> locations = new LinkedList<Location>();

    public MatrixCreator() { }

    public LinkedList<Location> getLocations() {
        return this.locations;
    }
    public void addLocation(Location l) {
        if(locations.size() == 2) locations.remove();
        locations.add(l);
    }

    public void defineMatrix(Player player, int dimX, int dimZ) {
        Plugin p = Plugin.getInstance();

        if(locations.size() != 2) {
            player.sendMessage(ChatColor.RED + "Define both coordinates!");
        } else {
            int cellSize = p.getConfig().getInt("CELL_SIZE");

            this.matrix = new MatrixCell[dimX][dimZ];

            Location areaStartCorner = locations.getFirst();
            Location directionLocation = locations.getLast();

            int areaDirectionX = areaStartCorner.getBlockX() > directionLocation.getBlockX() ? -1 : 1;
            int areaDirectionZ = areaStartCorner.getBlockZ() > directionLocation.getBlockZ() ? -1 : 1;

            Location areaOppositeCorner = areaStartCorner.clone()
                    .add((dimX*cellSize-1) * areaDirectionX, 0, (dimZ*cellSize-1) * areaDirectionZ);

            villageArea = new VillageArea(areaStartCorner, areaOppositeCorner);

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

                    MatrixCell mc = new MatrixCell(l1, l2, new CellAddress(i, j));
                    this.matrix[i][j] = mc;
                }
            }
        }
    }

    public void apply(Player player, String villageName) {
        Plugin p = Plugin.getInstance();
        boolean isVillageExist = p.getVillageService().isVillageExistDB(villageName);
        if (!isVillageExist) {
            player.sendMessage("Please create a village first");
        } else if (this.matrix == null) {
            player.sendMessage("Village matrix does not defined");
        } else {
            p.getMatrixService().applyMatrix(matrix, villageArea, villageName);
            this.matrix = null;
            this.villageArea = null;
        }
    }

    public void reset() {
        this.villageArea = null;
        this.matrix = null;
    }
}
