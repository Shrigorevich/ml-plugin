package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PreventActionListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
//        if(!(Plugin.getInstance().getPlayerCache().isAuthenticated(e.getPlayer().getName()))){
//            e.setCancelled(true);
//        }
    }
}
