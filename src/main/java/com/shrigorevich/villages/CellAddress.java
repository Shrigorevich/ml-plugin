package com.shrigorevich.villages;

import com.shrigorevich.Packable;
import org.bson.Document;

public class CellAddress implements Packable {

    private final int i;
    private final int j;

    public CellAddress(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Document packData() {
        Document doc = new Document();
        doc.append("i", i);
        doc.append("j", j);
        return doc;
    }
}
