package com.alxg2112.diploma.mappers;

import com.alxg2112.diploma.logic.LogicForm;
import com.alxg2112.diploma.logic.forms.*;
import com.alxg2112.diploma.mappers.impl.*;

public class MapperFactory {
    public static Mapper getMapper(Class<? extends LogicForm> clazz) {
        if (clazz == AlgebraicNormalFrom.class) return new ANFMapper();
        else if (clazz == ConjuctiveNormalForm.class) return new CNFMapper();
        else if (clazz == DisjunctiveNormalForm.class) return new DNFMapper();
        else if (clazz == TruthTable.class) return new TruthTableMapper();
        else return null;
    }
}
