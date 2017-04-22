package com.alxg2112.diploma.logic.forms;

import java.util.HashMap;
import java.util.Map;
import static com.alxg2112.diploma.logic.forms.Values.*;

public class TruthTable {
    private static final String[] TRUTH_TABLE_KEYS = {
            FALSE_FALSE_FALSE,
            FALSE_FALSE_TRUE,
            FALSE_TRUE_FALSE,
            FALSE_TRUE_TRUE,
            TRUE_FALSE_FALSE,
            TRUE_FALSE_TRUE,
            TRUE_TRUE_FALSE,
            TRUE_TRUE_TRUE
    };

    private Map<String, Boolean> table;

    private TruthTable(int[] values) {
        table = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Boolean functionValue = values[i] == 1;
            table.put(TRUTH_TABLE_KEYS[i], functionValue);
        }
    }

    public static TruthTable getFromValuesArray(int[] values) {
        return new TruthTable(values);
    }

    public Map<String, Boolean> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
