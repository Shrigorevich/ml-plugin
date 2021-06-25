package com.shrigorevich.villages.square;

import com.shrigorevich.villages.enums.CellType;
import org.bson.Document;
import org.bukkit.Location;

public class AdminCell extends MatrixCell{

    public AdminCell(Location l1, Location l2) {
        super(l1, l2);
        this.setType(CellType.DEFAULT);
        this.setOwner("Default");
    }

    public AdminCell(Document doc) {
        super(doc);
    }
}
