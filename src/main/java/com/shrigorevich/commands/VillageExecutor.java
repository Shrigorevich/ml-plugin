package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VillageExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Plugin p = Plugin.getInstance();

        }
        return false;
    }
}
