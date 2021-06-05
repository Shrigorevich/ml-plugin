package com.shrigorevich.authorization;

import com.shrigorevich.Plugin;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AuthCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        System.out.println("Command works   " + args.length);

        if(args.length > 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage("Authorization started");
                Document doc = Plugin.getInstance().getDb().authPlayer(player.getName(), args[0]);
                if(doc != null) {
                    PlayerData pData = new PlayerData(doc);
                    Plugin.getInstance().getPlayerCache().addPlayer(pData);
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
