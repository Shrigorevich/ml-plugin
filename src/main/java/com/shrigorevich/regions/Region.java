package com.shrigorevich.regions;

import com.shrigorevich.regions.enums.RegionType;
import com.shrigorevich.regions.enums.VillageType;
import org.bson.Document;
import org.bukkit.Location;
import java.util.LinkedList;

public class Region {
    private RegionType type;
    private Square square;
    private String owner;
    private VillageType village;

    public Region(Location firstLoc, Location secondLoc) {
        this(
            new Square(firstLoc, secondLoc),
            RegionType.ADMIN,
            "ADMIN",
            VillageType.FIRST
        );
    }

    public Region(Document doc) {
        this(
            new Square((Document) doc.get("square")),
            RegionType.valueOf(doc.getString("type")),
            doc.getString("owner"),
            VillageType.valueOf(doc.getString("village"))
        );
    }

    public Region(Square square, RegionType type, String owner, VillageType village) {
        this.type = type;
        this.square = square;
        this.owner = owner;
        this.village = village;
    }

    public String getOwner() {
        return owner;
    }

    public RegionType getType(){
        return this.type;
    }

    public Square getSquare() {
        return this.square;
    }

    public VillageType getVillage() {
        return village;
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("owner", owner);
        doc.append("type", type.getType());
        doc.append("village", village.getType());
        doc.append("square", square.packData());
        return doc;
    }
}
