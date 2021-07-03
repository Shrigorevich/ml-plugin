package com.shrigorevich.landRegistry.villages;

import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class Village {

    private MatrixCell[][] matrix;
    private VillageArea area;
    private String name;

    public Village() { }

    public Village(Document doc) {
        this.name = doc.getString("name");
        this.area = new VillageArea((Document) doc.get("area"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MatrixCell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixCell[][] matrix) {
        this.matrix = matrix;
    }

    public VillageArea getArea() {
        return area;
    }

    public void setArea(VillageArea area) {
        this.area = area;
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("name", name);
        doc.append("area", area.packData());
        return doc;
    }
}
