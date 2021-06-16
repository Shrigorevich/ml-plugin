package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import com.shrigorevich.regions.Region;
import com.shrigorevich.regions.RegionManager;
import com.shrigorevich.regions.Square;
import com.shrigorevich.regions.enums.RegionType;
import com.shrigorevich.regions.enums.VillageType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Plugin p = Plugin.getInstance();
            if(args[0].equals("create")) {

                Region r = new Region(p.getSessionManager().getLocations());
                p.getRegionManager().addRegion(r);
                p.getDb().saveRegion(r);

            } else if(args[0].equals("update")) {
                player.sendMessage(ChatColor.AQUA + "Update");
            } else if(args[0].equals("check")) {
                RegionManager m = Plugin.getInstance().getRegionManager();
                for(Region reg : m.getAll()) {
                    player.sendMessage("First: " + reg.getOwner());
                    Location[] corners = reg.getSquare().getRegionCorners();
                    for(Location l : corners) {
                        player.spawnParticle(Particle.SMOKE_NORMAL, l, 5,0.5,0,0.5,0);
                    }
                }
            }
        }

        return false;
    }
}
