package com.shrigorevich.state;

import com.shrigorevich.landRegistry.lands.MatrixCell;

public class MatrixManager {

    private MatrixCell[][] matrix;

    public MatrixManager() {

    }

    public MatrixCell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixCell[][] matrix) {
        this.matrix = matrix;
    }
}
