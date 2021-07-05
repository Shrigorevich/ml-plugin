package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.shrigorevich.utils.VillageUtil.*;

public class VillageExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Plugin p = Plugin.getInstance();
            if(args[0].equals("create")) {
                if(args.length >= 2){
                    p.getVillageCreator().defineVillage(player, args[1]);
                } else {
                  player.sendMessage("Enter village name");
                }
            } else if(args[0].equals("matrix")) {
                if (args.length >= 1) {
                    try {
                        p.getVillageCreator().defineMatrix(player);
                    } catch (Exception ex) {
                        player.sendMessage("Wrong dimension arguments");
                    }
                } else {
                    player.sendMessage("Enter all mandatory arguments");
                }
            } else if (args[0].equals("save")) {
                p.getVillageCreator().save(player);
            } else if(args[0].equals("check")) {
                checkCells(args[1]);
            } else if (args[0].equals("checkcell")) {
                checkCell(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            } else if(args[0].equals("reset")) {
                p.getVillageCreator().reset();
            } else if (args[0].equals("join")) {
                p.getUserService().joinVillage(player, args[1]);
            } else if (args[0].equals("clear")) {
                clearCells(args[1]);
            }
        }

        return false;
    }
}
