package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class Village {

    private VillageArea area;
    private String name;

    public Village() { }

    public Village(String name, VillageArea area) {
        this.name = name;
        this.area = area;
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
