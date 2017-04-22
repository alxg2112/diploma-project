package com.alxg2112.diploma.logic.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.alxg2112.diploma.logic.forms.Values.*;

public class DisjunctiveNormalForm {
    private static final Map<String, String> VALUES_TO_SUMMANDS = createCoefficientsToArgumentsMap();

    private TruthTable truthTable;
    private List<String> summands;

    private static Map<String, String> createCoefficientsToArgumentsMap() {
        Map<String, String> valuesToSummandsMap = new HashMap<>();
        valuesToSummandsMap.put(FALSE_FALSE_FALSE, "^x^y^z");
        valuesToSummandsMap.put(FALSE_FALSE_TRUE, "^x^yz");
        valuesToSummandsMap.put(FALSE_TRUE_FALSE, "^xy^z");
        valuesToSummandsMap.put(FALSE_TRUE_TRUE, "^xyz");
        valuesToSummandsMap.put(TRUE_FALSE_FALSE, "x^y^z");
        valuesToSummandsMap.put(TRUE_FALSE_TRUE, "x^yz");
        valuesToSummandsMap.put(TRUE_TRUE_FALSE, "xy^z");
        valuesToSummandsMap.put(TRUE_TRUE_TRUE, "xyz");
        return valuesToSummandsMap;
    }

    private DisjunctiveNormalForm(TruthTable truthTable) {
        this.truthTable = truthTable;
        initItems();
    }

    public static DisjunctiveNormalForm fromTruthTable(TruthTable truthTable) {
        return new DisjunctiveNormalForm(truthTable);
    }

    private void initItems() {
        summands = new ArrayList<>();
        for (Map.Entry<String, Boolean> truthTableEntry : truthTable.getTable().entrySet()) {
            String values = truthTableEntry.getKey();
            boolean functionIsTrue = truthTableEntry.getValue();
            // If function evaluates to true with given argument values,
            // we include summand with given values to DNF.
            if (functionIsTrue) {
                String summand = VALUES_TO_SUMMANDS.get(values);
                summands.add(summand);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for (String summand : summands) {
            builder.append(prefix);
            builder.append(summand);
            prefix = " + ";
        }
        return builder.toString();
    }
}
