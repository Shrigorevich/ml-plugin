package com.shrigorevich.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.PlayerData;
import com.shrigorevich.villages.square.MatrixCell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CellPurchaseProcessor {

    public CellPurchaseProcessor() {

    }

    public void giveUpOwnership(Player player, int i, int j) {
        Plugin p = Plugin.getInstance();
        PlayerData pData = p.getPlayerManager().getPlayer(player.getName());
        MatrixCell[][] matrix = p.getVillageManager().getVillage(pData.getVillage()).getMatrix();
        if(matrix[i][j].getOwner().equals(pData.getName())) {
            matrix[i][j].setOwner("Admin");
        }
    }

    public void purchaseAttempted(Player player, int i, int j) {
        Plugin p = Plugin.getInstance();
        PlayerData pData = p.getPlayerManager().getPlayer(player.getName());

        if(isUserCanBuyCell(pData, i, j)) {
            p.getVillageManager().getVillage(pData.getVillage()).getMatrix()[i][j].setOwner(player.getName());
            player.sendMessage(ChatColor.GREEN + "Successful deal!");
        } else {
            player.sendMessage(ChatColor.RED + "The user cannot purchase this site");
        }
    }

    public boolean isUserCanBuyCell(PlayerData pData, int i, int j) {
        Plugin p = Plugin.getInstance();
        int dimX = p.getConfig().getInt("MATRIX_DIM_X");
        int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");

        MatrixCell[][] matrix = p.getVillageManager().getVillage(pData.getVillage()).getMatrix();

        if(!matrix[i][j].getOwner().equals("Admin")) return false;

        for(int dx = -1; dx < 2; dx++) {
            for(int dz = -1; dz < 2; dz++) {
                if(dx == 0 && dz == 0) continue;
                int addressI = i+dx;
                int addressJ = j+dz;

                if(0 <= addressI && addressI < dimX && 0 <= addressJ && addressJ < dimZ) {
                    System.out.println(ChatColor.AQUA + " " + addressI + " " + addressJ);
                    String cellOwner = matrix[addressI][addressJ].getOwner();
                    if(!cellOwner.equals("Admin") && !cellOwner.equals(pData.getName())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
