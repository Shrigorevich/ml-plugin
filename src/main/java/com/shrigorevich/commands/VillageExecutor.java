package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
                    p.getVillageCreator().create(player, args[1]);
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
                if(p.getVillageManager().contains(args[1])) {
                    PlayerData pData = p.getPlayerManager().getPlayer(player.getName());
                    if (pData != null) {
                        pData.setVillage(args[1]);
                        p.getUserContext().joinVillage(player.getName(), pData.getVillage());
                    } else {
                        player.sendMessage("Player not authorized");
                    }
                } else {
                    player.sendMessage("Village with that name does not exist");
                }
                player.sendMessage(ChatColor.RED + p.getPlayerManager().getPlayer(player.getName()).getVillage());
            } else if (args[0].equals("clear")) {
                clearCells(args[1]);
            }
        }

        return false;
    }
}
