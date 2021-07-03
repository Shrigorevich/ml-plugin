package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.enums.CellType;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class AddressMapper implements IMapper<CellAddress>{
    @Override
    public Document packData(CellAddress cellAddress) {
        Document doc = new Document();
        doc.append("i", cellAddress.getI());
        doc.append("j", cellAddress.getJ());
        return doc;
    }

    @Override
    public CellAddress unpackData(Document doc) {
        return new CellAddress(doc.getInteger("i"), doc.getInteger("j"));
    }
}
