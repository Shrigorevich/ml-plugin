package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import com.shrigorevich.villages.CellAddress;
import com.shrigorevich.villages.square.MatrixCell;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CellExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(args.length > 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                Plugin p = Plugin.getInstance();
                if(args[0].equals("buy")) {
                    int i;
                    int j;
                    try {
                        i = Integer.parseInt(args[1]);
                        j = Integer.parseInt(args[2]);
                        p.getCellPurchaseProcessor().purchaseAttempted(player, i, j);
                    } catch (Exception e) {
                        System.out.println(e);
                        player.sendMessage("Enter correct cell address");
                    }
                } else if(args[0].equals("giveup")) {
                    int i;
                    int j;
                    try {
                        i = Integer.parseInt(args[1]);
                        j = Integer.parseInt(args[2]);
                        p.getCellPurchaseProcessor().giveUpOwnership(player, i, j);
                    } catch (Exception e) {
                        player.sendMessage("Enter correct cell address");
                    }
                }
            } else {
                System.out.println("You can`t use this command through console");
            }
        }
        return false;
    }
}
