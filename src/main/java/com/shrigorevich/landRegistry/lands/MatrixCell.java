package com.shrigorevich.landRegistry.lands;

import com.shrigorevich.landRegistry.enums.CellType;
import org.bukkit.Location;

public class MatrixCell extends Square {

    private String owner;
    private CellType type;
    private boolean unbreakable;

    public MatrixCell(Location l1, Location l2) {
        super(l1, l2);
        type = CellType.CIVIL;
        owner = "Default";
        unbreakable = false;
    }

    public MatrixCell(
        String owner,
        CellType cellType,
        String worldName,
        boolean unbreakable,
        int x1, int x2, int z1, int z2
    ) {
        super(worldName, x1, x2, z1, z2);
        this.unbreakable = unbreakable;
        this.owner = owner;
        this.type = cellType;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public String getOwner() {
        return owner;
    }

    public CellType getType() {
        return type;
    }
}
