package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.infrastructure.mappers.CellMapper;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class VillageLoader {

    public static void loadVillages() {

        Plugin p = Plugin.getInstance();
        for(Village village : p.getVillageService().loadVillages()) {
            int dimX = p.getConfig().getInt("MATRIX_DIM_X");
            int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");
            MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];
            for (MatrixCell cell : p.getCellService().loadCellsByVillage(village.getName())) {
                //TODO: Resolve conflict between db cell model and business cell model
                Document address = (Document) cellDoc.get("address");
                int i = address.getInteger("i");
                int j = address.getInteger("j");
                matrix[i][j] = CellMapper.unpackData(cellDoc);
            }
            village.setMatrix(matrix);
            p.getVillageManager().addVillage(village);
        }
    }
}
