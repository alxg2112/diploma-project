package com.alxg2112.diploma.logic.forms;

import java.util.HashMap;
import java.util.Map;
import static com.alxg2112.diploma.logic.forms.Values.*;

public class TruthTable {
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final int X_KEY_INDEX = 0;
    private static final int Y_KEY_INDEX = 1;
    private static final int Z_KEY_INDEX = 2;
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
        StringBuilder builder = new StringBuilder();
        builder.append("+---+---+---+---+\n");
        builder.append("¦ x ¦ y ¦ z ¦ F ¦\n");
        builder.append("+---+---+---+---+\n");
        for (String key : TRUTH_TABLE_KEYS) {
            Character x = key.toCharArray()[X_KEY_INDEX];
            Character y = key.toCharArray()[Y_KEY_INDEX];
            Character z = key.toCharArray()[Z_KEY_INDEX];
            int functionValue = table.get(key) ? TRUE : FALSE;
            builder.append(String.format("¦ %s   %s   %s   %s ¦%n", x, y, z, functionValue));
        }
        builder.append("+---+---+---+---+\n");
        return builder.toString();
    }
}
