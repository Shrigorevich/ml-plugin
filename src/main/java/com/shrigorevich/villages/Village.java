package com.shrigorevich.villages;

import com.shrigorevich.Packable;
import com.shrigorevich.villages.square.MatrixCell;
import com.shrigorevich.villages.square.VillageArea;
import org.bson.Document;
import org.bukkit.Location;

public class Village implements Packable {

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

    public void setArea(Location l1, Location l2) {
        this.area = new VillageArea(l1, l2);
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("name", name);
        doc.append("area", area.packData());
        return doc;
    }
}
