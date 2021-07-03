package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        //Plugin.getInstance().getSkinChanger().applySkin(event.getPlayer());
    }
}
