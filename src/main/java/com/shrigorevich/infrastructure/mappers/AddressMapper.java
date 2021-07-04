package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.lands.CellAddress;
import org.bson.Document;

public class AddressMapper{

    public static Document packData(CellAddress cellAddress) {
        Document doc = new Document();
        doc.append("i", cellAddress.getI());
        doc.append("j", cellAddress.getJ());
        return doc;
    }

    public static CellAddress unpackData(Document doc) {
        return new CellAddress(doc.getInteger("i"), doc.getInteger("j"));
    }
}
