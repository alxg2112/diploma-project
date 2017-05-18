package com.alxg2112.diploma.mappers.impl;

import com.alxg2112.diploma.logic.forms.ConjuctiveNormalForm;
import com.alxg2112.diploma.mappers.Mapper;
import com.thoughtworks.xstream.XStream;

public class CNFMapper implements Mapper<ConjuctiveNormalForm> {
    private XStream xStream;

    public CNFMapper() {
        xStream = new XStream();
        xStream.alias("cnf", ConjuctiveNormalForm.class);
    }

    @Override
    public String toXML(ConjuctiveNormalForm object) {
        return xStream.toXML(object);
    }

    @Override
    public ConjuctiveNormalForm fromXML(String xml) {
        return (ConjuctiveNormalForm) xStream.fromXML(xml);
    }
}
