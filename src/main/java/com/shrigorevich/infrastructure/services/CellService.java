package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.UserData;
import com.shrigorevich.infrastructure.db.MatrixCellContext;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import com.shrigorevich.state.MatrixManager;
import org.bson.Document;

import java.util.ArrayList;

public class CellService {

    private MatrixCellContext cellContext;
    private MatrixManager matrixManager;

    public CellService(MongoDatabase database) {
        this.cellContext = new MatrixCellContext(database);
        this.matrixManager = new MatrixManager();
    }

    public void saveMatrix(MatrixCell[][] matrix, String villageName) {
        Plugin p = Plugin.getInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                cellContext.saveCell(villageName, new CellAddress(i, j), matrix[i][j]);
            }
        }
    }

    public MatrixCell[][] getMatrix(String villageName) {
        return matrixManager.getMatrix(villageName);
    }

    public void setCellOwner(UserData uData, MatrixCell cell, CellAddress cellAddress) {
        cell.setOwner(uData.getName());
        cellContext.updateCellOwner(cell, cellAddress, uData.getVillage());
    }

    public ArrayList<MatrixCell> loadCellsByVillage(String villageName) {
        return cellContext.getCellsByVillage(villageName);
    }

}
