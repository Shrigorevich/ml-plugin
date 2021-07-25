package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.UserData;
import com.shrigorevich.landRegistry.villages.Village;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreak implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnBreak(BlockBreakEvent event) {
        Plugin p = Plugin.getInstance();
        boolean isBlockInVillage = false;
        String activeVillage = null;
        Village village = p.getVillageService().getVillage();
        if(village.getArea().contains(event.getBlock().getLocation())) {
            isBlockInVillage = true;
            activeVillage = village.getName();
        }

        if (isBlockInVillage) {

        }
    }
}
