package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.villages.VillageArea;
import org.bson.Document;

public class AreaMapper{

    public static Document packData(VillageArea area) {
        Document doc = new Document();
        doc.append("worldName", area.getWorldName());
        doc.append("x1", area.getX1());
        doc.append("x2", area.getX2());
        doc.append("z1", area.getZ1());
        doc.append("z2", area.getZ2());
        return doc;
    }

    public static VillageArea unpackData(Document doc) {
        return new VillageArea(
            doc.getString("worldName"),
            doc.getInteger("x1"),
            doc.getInteger("x2"),
            doc.getInteger("z1"),
            doc.getInteger("z2")
        );
    }
}
