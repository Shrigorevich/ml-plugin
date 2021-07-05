package com.shrigorevich.infrastructure.mappers;

import com.shrigorevich.authorization.UserData;
import com.shrigorevich.skins.SkinType;
import org.bson.Document;

public class UserMapper {
    public static Document packData(UserData uData) {
        Document doc = new Document();
        doc.append("nickname", uData.getName());
        doc.append("village", uData.getVillage());
        doc.append("skin", uData.getSkin().getSkinName());
        return doc;
    }

    public static UserData unpackData(Document doc) {
        return new UserData(
            doc.getString("nickname"),
            SkinType.valueOf(doc.getString("skin")),
            doc.getString("village")
        );
    }
}
