package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.landRegistry.enums.CellType;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;

public class CellMapper implements IMapper<MatrixCell>{

    @Override
    public Document packData(MatrixCell mc) {
        Document doc = new Document();
        doc.append("worldName", mc.getWorldName());
        doc.append("owner", mc.getOwner());
        doc.append("type", mc.getType().getTypeName());
        doc.append("x1", mc.getX1());
        doc.append("x2", mc.getX2());
        doc.append("z1", mc.getZ1());
        doc.append("z2", mc.getZ2());

        return doc;
    }

    @Override
    public MatrixCell unpackData(Document doc) {
        return new MatrixCell(
                doc.getString("owner"),
                CellType.valueOf(doc.getString("type")),
                doc.getString("worldName"),
                doc.getInteger("x1"),
                doc.getInteger("x2"),
                doc.getInteger("z1"),
                doc.getInteger("z2")
        );
    }
}
