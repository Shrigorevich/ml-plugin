package com.shrigorevich.regions;

import com.shrigorevich.Packable;
import com.shrigorevich.regions.enums.CellType;
import org.bson.Document;
import org.bukkit.Location;

public class MatrixCell extends Square implements Packable {

    String owner;
    CellType type;

    public MatrixCell(Location l1, Location l2) {
        super(l1, l2);
    }

    public MatrixCell(Document doc) {
        super(
            doc.getString("worldName"),
            doc.getInteger("x1"),
            doc.getInteger("x2"),
            doc.getInteger("z1"),
            doc.getInteger("z2")
        );

        this.owner = doc.getString("owner");
        this.type = CellType.valueOf(doc.getString("type"));
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("worldName", worldName);
        doc.append("owner", owner);
        doc.append("type", type.getType());
        doc.append("x1", x1);
        doc.append("x2", x2);
        doc.append("z1", z1);
        doc.append("z2", z2);

        return doc;
    }
}
