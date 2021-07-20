package com.shrigorevich.landRegistry.lands;

import com.shrigorevich.enums.CellType;
import org.bukkit.Location;

public class MatrixCell extends Square {

    private String owner;
    private CellType type;
    private String purpose;
    private boolean owned;
    private boolean unbreakable;
    private CellAddress address;

    public MatrixCell(Location l1, Location l2, CellAddress address) {
        super(l1, l2);
        type = CellType.CIVIL;
        unbreakable = false;
        owned = false;
        purpose = "Living cell";
        this.address = address;
    }

    public MatrixCell(
        String owner,
        CellType cellType,
        String worldName,
        boolean unbreakable,
        boolean isOwned,
        String purpose,
        CellAddress address,
        int x1, int x2, int z1, int z2
    ) {
        super(worldName, x1, x2, z1, z2);
        this.owned = isOwned;
        this.purpose = purpose;
        this.unbreakable = unbreakable;
        this.owner = owner;
        this.type = cellType;
        this.address = address;
    }

    public CellAddress getAddress() {
        return address;
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

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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
