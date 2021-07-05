package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeft implements Listener {
    @EventHandler
    public void onLeft(PlayerQuitEvent event) {
        Plugin.getInstance().getUserService().removeUserFromState(event.getPlayer().getName());
    }
}
