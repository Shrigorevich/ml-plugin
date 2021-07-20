package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


import java.util.Map;

public class MobExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(args[0].equals("create")) {
            Map mobs = Plugin.getInstance().mobs;
            World w = Bukkit.getWorld("world");
            int n = Integer.parseInt(args[1]);

            for(int i = 0; i < n; i++) {
                Entity entity = w != null ? w.spawnEntity(player.getLocation(), EntityType.ZOMBIE, null, (e) -> {
                    mobs.put(e.getUniqueId(), e);
                }) : null;
                
                player.sendMessage(entity.getClass().getCanonicalName());
            }
        } else if (args[0].equals("remove")) {
            for (Entity e : Plugin.getInstance().mobs.values()) {
                e.remove();
                sender.sendMessage(e.getClass().getCanonicalName());
            }
        }
        return false;
    }
}
