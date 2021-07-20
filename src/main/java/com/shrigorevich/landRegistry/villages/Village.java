package com.shrigorevich.landRegistry.villages;

public class Village {

    private VillageArea area;
    private String name;
    private int dimensionX;
    private int dimensionZ;

    public Village(String name, VillageArea area, int dimensionX, int dimensionZ) {
        this.name = name;
        this.area = area;
        this.dimensionX = dimensionX;
        this.dimensionZ = dimensionZ;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionZ() {
        return dimensionZ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VillageArea getArea() {
        return area;
    }

    public void setArea(VillageArea area) {
        this.area = area;
    }
}
