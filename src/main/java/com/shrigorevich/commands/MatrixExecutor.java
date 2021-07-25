package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.shrigorevich.utils.VillageUtil.*;

public class MatrixExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Plugin p = Plugin.getInstance();
            if(args[0].equals("create")) {
                if (args.length >= 3) {
                    try {
                        int dimensionX = Integer.parseInt(args[1]);
                        int dimensionZ = Integer.parseInt(args[2]);
                        p.getMatrixCreator().defineMatrix(player, dimensionX, dimensionZ);
                    } catch (Exception ex) {
                        player.sendMessage("Wrong dimension arguments");
                    }
                } else {
                    player.sendMessage("Enter all mandatory arguments");
                }
            } else if (args[0].equals("apply")) {
                p.getMatrixCreator().apply(player, args[1]);
            } else if(args[0].equals("check")) {
                checkCells();
            } else if (args[0].equals("checkcell")) {
                checkCell(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            } else if(args[0].equals("reset")) {
                p.getMatrixCreator().reset();
            } else if (args[0].equals("clear")) {
                clearCells();
            }
        }
        return false;
    }
}
