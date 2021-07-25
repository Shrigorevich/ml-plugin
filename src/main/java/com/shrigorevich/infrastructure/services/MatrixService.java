package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.UserData;
import com.shrigorevich.enums.VillageStatus;
import com.shrigorevich.infrastructure.db.MatrixCellContext;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.landRegistry.villages.VillageArea;
import com.shrigorevich.state.MatrixManager;
import org.bson.Document;

import java.util.ArrayList;

public class MatrixService {

    private MatrixCellContext matrixContext;
    private MatrixManager matrixManager;

    public MatrixService(MongoDatabase database) {
        this.matrixContext = new MatrixCellContext(database);
        this.matrixManager = new MatrixManager();
    }

    public void applyMatrix(MatrixCell[][] matrix, VillageArea area,  String villageName) {
        Plugin p = Plugin.getInstance();
        if(p.getVillageService().isVillageExistDB(villageName)) {
            boolean isAppliedSuccessfully = matrixContext.saveMatrix(villageName, matrix);
            if(isAppliedSuccessfully) {
                p.getVillageService().villageSetLocation(villageName, area, matrix);
            }
        }
    }

    public MatrixCell[][] getMatrix() {
        return matrixManager.getMatrix();
    }

    public void setCellOwner(UserData uData, MatrixCell cell, CellAddress cellAddress) {
        cell.setOwner(uData.getName());
        matrixContext.updateCellOwner(cell, cellAddress, uData.getVillage());
    }

    public ArrayList<MatrixCell> loadCellsByVillage(String villageName) {
        return matrixContext.getCellsByVillage(villageName);
    }

    public void setMatrix(MatrixCell[][] matrix) {
        matrixManager.setMatrix(matrix);
    }

}
