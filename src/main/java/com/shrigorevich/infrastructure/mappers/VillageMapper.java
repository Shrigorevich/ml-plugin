package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.villages.Village;
import com.shrigorevich.landRegistry.villages.VillageArea;
import org.bson.Document;

public class VillageMapper {

    public static Document packData(Village village) {
        Document doc = new Document();
        doc.append("name", village.getName());
        doc.append("area", AreaMapper.packData(village.getArea()));
        return doc;
    }

    public static Village unpackData(Document doc) {
        return new Village(
            doc.getString("name"),
            AreaMapper.unpackData((Document) doc.get("area"))
        );
    }
}
