package com.shrigorevich.state;

import com.shrigorevich.landRegistry.lands.MatrixCell;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatrixManager {

    private Map<String, MatrixCell[][]> cache;

    public MatrixManager() {
        this.cache = new ConcurrentHashMap<>();
    }

    public MatrixCell[][] getMatrix(String village) {
        return cache.get(village);
    }

    public void addMatrix(MatrixCell[][] matrix, String village) {
        cache.put(village, matrix);
    }
}
