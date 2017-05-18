package com.alxg2112.diploma.mappers.impl;

import com.alxg2112.diploma.logic.forms.DisjunctiveNormalForm;
import com.alxg2112.diploma.mappers.Mapper;
import com.thoughtworks.xstream.XStream;

public class DNFMapper implements Mapper<DisjunctiveNormalForm> {
    private XStream xStream;

    public DNFMapper() {
        xStream = new XStream();
        xStream.alias("dnf", DisjunctiveNormalForm.class);
    }

    @Override
    public String toXML(DisjunctiveNormalForm object) {
        return xStream.toXML(object);
    }

    @Override
    public DisjunctiveNormalForm fromXML(String xml) {
        return (DisjunctiveNormalForm) xStream.fromXML(xml);
    }
}
