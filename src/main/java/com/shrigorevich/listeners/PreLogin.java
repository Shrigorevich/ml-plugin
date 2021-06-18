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
    String nameMsg = ChatColor.RED + "Игрок с таким именем уже на сервере!",
            regMsg = ChatColor.RED + "Вы не зарегистрированы!",
            ipMsg = ChatColor.RED + "Вы заходите под новым IP адресом. Что бы активировать его, авторизуйтесь еще раз на нашем сайте :)",
            paidMsg = ChatColor.RED + "Вы не оплатили доступ к серверу :)";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        if (Plugin.getInstance().getPlayerManager().isAuthenticated(name)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(nameMsg));
            return;
        }

        Document doc = Plugin.getInstance().getDb().getRegisteredUser(name);

        System.out.println(ChatColor.AQUA + "" + doc.getList("ips", String.class));
        System.out.println(ChatColor.GREEN + "" + event.getAddress());

        if (doc == null) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(regMsg));
        }
//        else if(!doc.getList("ips", String.class).contains(event.getAddress().toString())) {
//            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(ipMsg));
//        }
        else if(!doc.getBoolean("paid")) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(paidMsg));
        }
    }


}
