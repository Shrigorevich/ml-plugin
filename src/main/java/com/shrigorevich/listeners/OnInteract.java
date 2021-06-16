package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import org.bukkit.ChatColor;
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

        if(action.equals(Action.LEFT_CLICK_BLOCK)) {
            Plugin.getInstance().getSessionManager().addLocation(event.getClickedBlock().getLocation());
            player.sendMessage(ChatColor.AQUA + "First loc: " + Plugin.getInstance().getSessionManager().getLocations().getFirst());
            player.sendMessage(ChatColor.GREEN + "Second loc: " + Plugin.getInstance().getSessionManager().getLocations().getLast());
        }
    }
}
