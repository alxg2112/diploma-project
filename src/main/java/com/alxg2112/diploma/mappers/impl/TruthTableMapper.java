package com.alxg2112.diploma.mappers.impl;

import com.alxg2112.diploma.logic.forms.TruthTable;
import com.alxg2112.diploma.mappers.Mapper;
import com.thoughtworks.xstream.XStream;

public class TruthTableMapper implements Mapper<TruthTable> {
    private XStream xStream;

    public TruthTableMapper() {
        xStream = new XStream();
        xStream.alias("truthTable", TruthTable.class);
    }

    @Override
    public String toXML(TruthTable object) {
        System.out.println(xStream.toXML(object));
        return xStream.toXML(object);
    }

    @Override
    public TruthTable fromXML(String xml) {
        return (TruthTable) xStream.fromXML(xml);
    }
}
