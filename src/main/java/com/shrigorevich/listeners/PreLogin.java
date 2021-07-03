package com.shrigorevich.listeners;

import com.shrigorevich.Plugin;
import net.kyori.adventure.text.Component;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreLogin implements Listener {
    String nameMsg = ChatColor.RED + "Player with the same name is already on the server!",
            regMsg = ChatColor.RED + "You are not registered!",
            ipMsg = ChatColor.RED + "You are logged in with a new IP address. To activate it, log in again on our website :)",
            paidMsg = ChatColor.RED + "You have not paid for access to the server :)";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        if (Plugin.getInstance().getPlayerManager().isAuthenticated(name)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(nameMsg));
            return;
        }

        Document doc = Plugin.getInstance().getUserContext().getRegisteredUser(name);

        System.out.println(ChatColor.AQUA + "" + doc.getList("ips", String.class));

        if (doc == null) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(regMsg));
        }
//        else if(!doc.getList("ips", String.class).contains(event.getAddress().toString())) {
//            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(ipMsg));
//        }
        else if(!doc.getBoolean("paid")) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(paidMsg));
        } else {
            Plugin.getInstance().getPlayerManager().addPlayer(doc);
        }
    }


}
