package com.shrigorevich.infrastructure.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.shrigorevich.infrastructure.mappers.AddressMapper;
import com.shrigorevich.infrastructure.mappers.CellMapper;
import com.shrigorevich.landRegistry.lands.CellAddress;
import com.shrigorevich.landRegistry.lands.MatrixCell;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Updates.set;

public class MatrixCellContext {
    private MongoCollection<Document> cells;

    public MatrixCellContext(MongoDatabase database) {
        this.cells = database.getCollection("cells");
    }

    public void updateCellOwner(MatrixCell cell, CellAddress address, String villageName) {
        Bson addressFilter = Filters.eq("address", AddressMapper.packData(address));
        Bson villageFilter = Filters.eq("villageName", villageName);
        cells.updateOne(Filters.and(addressFilter, villageFilter), set("owner", cell.getOwner()));
    }

    public void saveCell(String villageName, MatrixCell cell) {
        Document cellDoc = CellMapper.packData(cell);
        cellDoc.append("villageName", villageName);
        cells.insertOne(cellDoc);
    }

    public ArrayList<MatrixCell> getCellsByVillage(String villageName) {

        Document filter = new Document().append("villageName", villageName);
        MongoCursor<Document> cursor = cells.find().filter(filter).iterator();

        ArrayList<MatrixCell> cells = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                cells.add(CellMapper.unpackData(cursor.next()));
            }
        } finally {
            cursor.close();
        }

        return cells;
    }
}
