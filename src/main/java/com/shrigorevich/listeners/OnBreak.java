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
//        Plugin p = Plugin.getInstance();
//        boolean isBlockInVillage = false;
//        String activeVillage = null;
//        for(Village v : p.getVillageManager().getVillages()) {
//            if(v.getArea().contains(event.getBlock().getLocation())) {
//                isBlockInVillage = true;
//                activeVillage = v.getName();
//            }
//        }
//        if (isBlockInVillage) {
//            UserData pData = p.getPlayerManager().getPlayer(event.getPlayer().getName());
//            Village playerVillage = p.getVillageManager().getVillage(pData.getVillage());
//            if(playerVillage != null && playerVillage.getName().equals(activeVillage)) {
//                event.getPlayer().sendMessage(ChatColor.GREEN + "Allowed");
//            } else {
//                event.setCancelled(true);
//                event.getPlayer().sendMessage("This is not your village, clown");
//            }
//        }
    }
}
