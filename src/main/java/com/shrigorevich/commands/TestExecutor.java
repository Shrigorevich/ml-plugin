package com.shrigorevich.commands;

import com.shrigorevich.Plugin;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Plugin p = Plugin.getInstance();
        if(args[0].equals("create")) {
            if(args.length >= 2){
                p.getVillageCreator().createFromConsole(args[1]);
            } else {
            }
        } else if(args[0].equals("matrix")) {
            if (args.length >= 1) {
                try {
                    p.getVillageCreator().defineMatrixFromConsole();
                } catch (Exception ex) {
                }
            } else {
            }
        } else if (args[0].equals("save")) {
            p.getVillageCreator().saveFromConsole();
        } else if (args[0].equals("checkcell")) {
            MatrixCell[][] matrix = p.getVillageManager().getVillage(args[1]).getMatrix();
            MatrixCell targetCell = matrix[Integer.parseInt(args[2])][Integer.parseInt(args[3])];
            System.out.println(targetCell.getType());
            System.out.println((targetCell.getOwner()));
        }

        return false;
    }
}
