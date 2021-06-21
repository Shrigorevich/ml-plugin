package com.shrigorevich.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {
    String msg = ChatColor.GREEN + "Medievalife";
    @EventHandler
    public void onPing(ServerListPingEvent e){
        System.out.println("PINGED");
        e.setMaxPlayers(120);
        e.motd(Component.text(msg));
    }
}
