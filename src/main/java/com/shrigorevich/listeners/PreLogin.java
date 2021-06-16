package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreLogin implements Listener {
    String nameMsg = ChatColor.RED + "Игрок с таким именем уже на сервере",
            regMsg = ChatColor.RED + "Вы не зарегистрированы на сайте";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        if (!Plugin.getInstance().getDb().isUserRegistered(name)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(regMsg));
        }

        if (Plugin.getInstance().getPlayerCache().isAuthenticated(name)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(nameMsg));
        }
    }


}
