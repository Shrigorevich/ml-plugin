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

public class MatrixService {

    private MatrixCellContext matrixContext;
    private MatrixManager matrixManager;

    public MatrixService(MongoDatabase database) {
        this.matrixContext = new MatrixCellContext(database);
        this.matrixManager = new MatrixManager();
    }

    public void applyMatrix(MatrixCell[][] matrix, String villageName) {
        Plugin p = Plugin.getInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrixContext.saveCell(villageName, matrix[i][j]);
            }
        }
    }

    public MatrixCell[][] getMatrix(String villageName) {
        return matrixManager.getMatrix(villageName);
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
