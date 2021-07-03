package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.infrastructure.mappers.CellMapper;
import com.shrigorevich.infrastructure.mappers.IMapper;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class VillageLoader {

    public static void loadVillages() {

        Plugin p = Plugin.getInstance();
        IMapper<MatrixCell> mapper = new CellMapper();
        for(Document villageDoc : p.getVillageContext().getVillages()) {
            String villageName = villageDoc.getString("name");
            Village village = new Village(villageDoc);
            int dimX = p.getConfig().getInt("MATRIX_DIM_X");
            int dimZ = p.getConfig().getInt("MATRIX_DIM_Z");
            MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];
            for (Document cellDoc : p.getMatrixCellContext().getCellsByVillage(villageName)) {
                Document address = (Document) cellDoc.get("address");
                int i = address.getInteger("i");
                int j = address.getInteger("j");
                matrix[i][j] = mapper.unpackData(cellDoc);
            }
            village.setMatrix(matrix);
            p.getVillageManager().addVillage(village);
        }
    }
}
