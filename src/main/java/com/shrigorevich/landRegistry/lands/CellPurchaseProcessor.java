package com.shrigorevich.landRegistry.lands;

import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.UserData;
import com.shrigorevich.enums.CellType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CellPurchaseProcessor {

    public static void giveUpOwnership(Player player, int i, int j) {
        Plugin p = Plugin.getInstance();
        UserData uData = p.getUserService().getUserData(player.getName());
        //TODO: Check if the user has joined the village
        MatrixCell[][] matrix = p.getMatrixService().getMatrix();
        if(matrix[i][j].getOwner().equals(player.getName())) {
            matrix[i][j].setOwner("Default");
        }
    }

    public static void purchaseAttempted(Player player, int i, int j) {
        Plugin p = Plugin.getInstance();
        UserData uData = p.getUserService().getUserData(player.getName());
        MatrixCell[][] matrix = p.getMatrixService().getMatrix();
        MatrixCell targetCell = matrix[i][j];

        if(isAdminCell(targetCell)) {
            player.sendMessage(ChatColor.RED + "This is an administrative cell");
        } else if(isPlayerOwnsCell(uData.getName(), targetCell)) {
            player.sendMessage(ChatColor.RED + "The user already owns this cell");
        } else if(isBlockingCellNearby(uData.getName(), matrix, i, j)) {
            player.sendMessage(ChatColor.RED + "The blocking cell is adjacent to the target cell");
        } else {
            p.getMatrixService().setCellOwner(uData, targetCell, new CellAddress(i, j));
            player.sendMessage(ChatColor.GREEN + "Successful deal!");
        }
    }

    public static boolean isAdminCell(MatrixCell cell) {
        return cell.getType().equals(CellType.ADMIN);
    }

    public static boolean isPlayerOwnsCell(String playerName, MatrixCell cell) {
        return cell.getOwner().equals(playerName);
    }

    public static boolean isBlockingCellNearby(String playerName, MatrixCell[][] matrix, int i, int j) {
        int dimX = Plugin.getInstance().getConfig().getInt("MATRIX_DIM_X");
        int dimZ = Plugin.getInstance().getConfig().getInt("MATRIX_DIM_Z");

        for(int dx = -1; dx < 2; dx++) {
            for(int dz = -1; dz < 2; dz++) {
                if(dx == 0 && dz == 0) continue;
                int addressI = i+dx;
                int addressJ = j+dz;

                if(0 <= addressI && addressI < dimX && 0 <= addressJ && addressJ < dimZ) {
                    System.out.println(ChatColor.AQUA + " " + addressI + " " + addressJ);
                    MatrixCell cell = matrix[addressI][addressJ];
                    if(isAdminCell(cell) || (!isPlayerOwnsCell(playerName, cell) && !cell.getOwner().equals("Default"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
