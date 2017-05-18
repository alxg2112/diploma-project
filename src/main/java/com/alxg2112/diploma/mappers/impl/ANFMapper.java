package com.alxg2112.diploma.mappers.impl;

import com.alxg2112.diploma.logic.forms.AlgebraicNormalFrom;
import com.alxg2112.diploma.mappers.Mapper;
import com.thoughtworks.xstream.XStream;

public class ANFMapper implements Mapper<AlgebraicNormalFrom> {
    private XStream xStream;

    public ANFMapper() {
        xStream = new XStream();
        xStream.alias("anf", AlgebraicNormalFrom.class);
    }

    @Override
    public String toXML(AlgebraicNormalFrom object) {
        return xStream.toXML(object);
    }

    @Override
    public AlgebraicNormalFrom fromXML(String xml) {
        return (AlgebraicNormalFrom) xStream.fromXML(xml);
    }
}
