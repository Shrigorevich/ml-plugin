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

    public void updateCellOwner(String villageName, CellAddress address, MatrixCell cell) {
        Bson addressFilter = Filters.eq("address", AddressMapper.packData(address));
        Bson villageFilter = Filters.eq("villageName", villageName);
        cells.updateOne(Filters.and(addressFilter, villageFilter), set("owner", cell.getOwner()));
    }

    public void saveCell(String villageName, CellAddress address, MatrixCell cell) {
        Document cellDoc = CellMapper.packData(cell);
        cellDoc.append("villageName", villageName);
        cellDoc.append("address", AddressMapper.packData(address));
        cells.insertOne(cellDoc);
    }

    public ArrayList<Document> getCellsByVillage(String villageName) {

        Document filter = new Document().append("villageName", villageName);
        MongoCursor<Document> cursor = cells.find().filter(filter).iterator();

        ArrayList<Document> cells = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                cells.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return cells;
    }
}
