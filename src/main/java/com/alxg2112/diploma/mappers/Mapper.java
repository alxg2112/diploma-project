package com.alxg2112.diploma.mappers;

public interface Mapper<T> {
    String toXML(T object);

    T fromXML(String xml);
}
