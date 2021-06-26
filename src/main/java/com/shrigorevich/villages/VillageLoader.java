package com.shrigorevich.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.villages.square.MatrixCell;
import org.bson.Document;

public class VillageLoader {

    public static void loadVillages() {

        Plugin p = Plugin.getInstance();

        for(Document villageDoc : p.getDb().getVillages()) {
            String villageName = villageDoc.getString("name");
            Village village = new Village(villageDoc);
            int dimX = p.getConfig().getInt("MATRIX_DIM_X");
            int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");
            MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];
            for (Document cellDoc : p.getDb().getCellsForVillage(villageName)) {
                Document address = (Document) cellDoc.get("address");
                int i = address.getInteger("i");
                int j = address.getInteger("j");
                matrix[i][j] = new MatrixCell(cellDoc);
            }
            village.setMatrix(matrix);
            p.getVillageManager().addVillage(village);
        }
    }
}