package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.shrigorevich.utils.RegionUtils.*;

public class RegionExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Plugin p = Plugin.getInstance();
            if(args[0].equals("create")) {
                createOne();
            } else if(args[0].equals("matrix")) {
                try {
                    createMatrix(args[1], args[2]);
                } catch (Exception ex) {
                    player.sendMessage("Wrong dimension arguments");
                }
            } else if(args[0].equals("check")) {
                checkRegions();
            } else if(args[0].equals("clear")) {
                removeStone();
            }
        }

        return false;
    }
}
