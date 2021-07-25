package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteract implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if(player.getInventory().getItemInMainHand().getType().equals(Material.FEATHER) && action.equals(Action.LEFT_CLICK_BLOCK)) {
            Plugin.getInstance().getMatrixCreator().addLocation(event.getClickedBlock().getLocation());

            player.sendMessage(ChatColor.AQUA + "First loc: " +
                    Plugin.getInstance().getMatrixCreator().getLocations().getFirst().getBlockX() +
                    " " +
                    Plugin.getInstance().getMatrixCreator().getLocations().getFirst().getBlockZ()
            );
            player.sendMessage(ChatColor.GREEN + "Second loc: " +
                    Plugin.getInstance().getMatrixCreator().getLocations().getLast().getBlockX() +
                    " " +
                    Plugin.getInstance().getMatrixCreator().getLocations().getLast().getBlockZ()
            );
        }
    }
}
