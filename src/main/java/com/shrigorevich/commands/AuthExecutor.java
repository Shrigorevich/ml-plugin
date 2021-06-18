package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.PlayerData;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuthExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        System.out.println("Command works   " + args.length);

        if(args.length > 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                Document doc = Plugin.getInstance().getDb().authPlayer(player.getName(), args[0]);
                if(doc != null) {
                    Plugin.getInstance().getPlayerManager().addPlayer(doc);
                    player.sendMessage("Successfully logged");
                } else {
                    player.sendMessage("Try again");
                }
            } else {
                System.out.println("You can`t use this command through console");
            }
        }
        return false;
    }
}
