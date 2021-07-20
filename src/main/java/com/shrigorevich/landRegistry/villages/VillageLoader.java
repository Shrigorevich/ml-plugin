package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.Plugin;
import com.shrigorevich.infrastructure.mappers.CellMapper;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class VillageLoader {

    public static void loadVillages() {

        Plugin p = Plugin.getInstance();
        Village village = p.getVillageService().loadVillage();
        int dimX = village.getDimensionX();
        int dimZ = village.getDimensionZ();
        MatrixCell[][] matrix = new MatrixCell[dimX][dimZ];
        for (MatrixCell cell : p.getMatrixService().loadCellsByVillage(village.getName())) {
            //TODO: Resolve conflict between db cell model and business cell model
            int i = cell.getAddress().getI();
            int j = cell.getAddress().getJ();
            matrix[i][j] = cell;
        }
        p.getVillageService().setVillage(village);
        p.getMatrixService().setMatrix(matrix);
    }
}
