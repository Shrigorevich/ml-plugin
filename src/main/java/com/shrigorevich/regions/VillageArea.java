package com.shrigorevich.regions;

import com.shrigorevich.Packable;
import org.bson.Document;
import org.bukkit.Location;

public class VillageArea extends Square implements Packable {

    public VillageArea(Location l1, Location l2) {
        super(l1, l2);
    }

    public VillageArea(Document doc) {
        super(
            doc.getString("worldName"),
            doc.getInteger("x1"),
            doc.getInteger("x2"),
            doc.getInteger("z1"),
            doc.getInteger("z2")
        );
    }

    public boolean contains(int x, int z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Location l) {
        return this.contains(l.getBlockX(), l.getBlockZ());
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("worldName", worldName);
        doc.append("x1", x1);
        doc.append("x2", x2);
        doc.append("z1", z1);
        doc.append("z2", z2);

        return doc;
    }
}
