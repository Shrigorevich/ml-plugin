package com.shrigorevich.infrastructure.mappers;

import org.bson.Document;

public interface IMapper<T> {

    public Document packData(T object);

    public T unpackData(Document doc);
}
