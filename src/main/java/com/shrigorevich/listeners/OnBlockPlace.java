package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.enums.CellType;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.landRegistry.villages.Village;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPlace(BlockPlaceEvent event) {
        Plugin p = Plugin.getInstance();
        Player player = event.getPlayer();
        Location blockLocation = event.getBlockPlaced().getLocation();
        boolean isVillageBlock = false;
        Village village = null;
        for(Village v : p.getVillageManager().getVillages()) {
            if(v.getArea().contains(blockLocation)) {
                isVillageBlock = true;
                village = v;
            }
        }

        if(isVillageBlock) {
            int cellSize = p.getConfig().getInt("CELL_SIZE");
            int targetCellI = (blockLocation.getBlockX() - village.getArea().getX1()) / cellSize;
            int targetCellJ = (blockLocation.getBlockZ() - village.getArea().getZ1()) / cellSize;
            MatrixCell targetCell = village.getMatrix()[targetCellI][targetCellJ];

            if(locationToCheck(blockLocation, targetCell) != null) {
                int neighbourCellI = (blockLocation.getBlockX() - village.getArea().getX1()) / cellSize;
                int neighbourCellJ = (blockLocation.getBlockZ() - village.getArea().getZ1()) / cellSize;

                MatrixCell neighbourCell = village.getMatrix()[neighbourCellI][neighbourCellJ];
                if (neighbourCell.getType().equals(CellType.CIVIL) && !neighbourCell.getOwner().equals(player.getName())) {
                    player.sendMessage("Road should be here");
                }
            }
        }
    }

    private Location locationToCheck(Location blockLocation, MatrixCell targetCell) {
        if (blockLocation.getBlockX() == targetCell.getX1()) {
            return blockLocation.clone().add(-1, 0, 0);
        } else if (blockLocation.getBlockX() == targetCell.getX2()) {
            return blockLocation.clone().add(1, 0, 0);

        } else if (blockLocation.getBlockZ() == targetCell.getZ1()) {
            return blockLocation.clone().add(0, 0, -1);

        } else if (blockLocation.getBlockZ() == targetCell.getZ2()) {
            return blockLocation.clone().add(0, 0, 1);
        }
        return null;
    }
}
