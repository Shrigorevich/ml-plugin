package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.villages.Village;
import org.bson.Document;

public class VillageMapper {

    public static Document packData(Village village) {
        Document doc = new Document();
        doc.append("name", village.getName());
        doc.append("area", AreaMapper.packData(village.getArea()));
        doc.append("dimensionX", village.getDimensionX());
        doc.append("dimensionZ", village.getDimensionZ());
        return doc;
    }

    public static Village unpackData(Document doc) {
        return new Village(
            doc.getString("name"),
            AreaMapper.unpackData((Document) doc.get("area")),
            doc.getInteger("dimensionX"),
            doc.getInteger("dimensionZ")
        );
    }
}
