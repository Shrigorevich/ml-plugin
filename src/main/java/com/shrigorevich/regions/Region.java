package com.shrigorevich.regions;

import com.shrigorevich.regions.enums.RegionType;
import com.shrigorevich.regions.enums.VillageType;

public class Region {
    private RegionType type;
    private Square square;
    private String ownerName;
    private VillageType village;

    public Region(RegionType type, Square square, String ownerName, VillageType village) {
        this.type = type;
        this.square = square;
        this.ownerName = ownerName;
        this.village = village;
    }

    public RegionType getType(){
        return this.type;
    }

    public Square getSquare() {
        return this.square;
    }

    public VillageType getVillage() {
        return village;
    }
}
