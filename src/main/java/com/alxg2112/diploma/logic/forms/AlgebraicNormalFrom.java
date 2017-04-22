package com.alxg2112.diploma.logic.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alxg2112.diploma.logic.forms.Values.*;

public class AlgebraicNormalFrom {
    private static final Map<String, String> VALUES_TO_SUMMANDS = createCoefficientsToArgumentsMap();

    private TruthTable truthTable;
    private Map<String, Boolean> coefficients;
    private List<String> summands;

    private static Map<String, String> createCoefficientsToArgumentsMap() {
        Map<String, String> valuesToSummandsMap = new HashMap<>();
        valuesToSummandsMap.put(FALSE_FALSE_FALSE, "1");
        valuesToSummandsMap.put(FALSE_FALSE_TRUE, "z");
        valuesToSummandsMap.put(FALSE_TRUE_FALSE, "y");
        valuesToSummandsMap.put(FALSE_TRUE_TRUE, "yz");
        valuesToSummandsMap.put(TRUE_FALSE_FALSE, "x");
        valuesToSummandsMap.put(TRUE_FALSE_TRUE, "xz");
        valuesToSummandsMap.put(TRUE_TRUE_FALSE, "xy");
        valuesToSummandsMap.put(TRUE_TRUE_TRUE, "xyz");
        return valuesToSummandsMap;
    }

    private AlgebraicNormalFrom(TruthTable truthTable) {
        this.truthTable = truthTable;
        coefficients = new HashMap<>();
        calculateCoefficients();
        initSummands();
    }

    private void calculateCoefficients() {
        coefficients.put(FALSE_FALSE_FALSE, truthTable.getTable().get(FALSE_FALSE_FALSE));
        coefficients.put(TRUE_FALSE_FALSE,
                coefficients.get(FALSE_FALSE_FALSE) ^
                        truthTable.getTable().get(TRUE_FALSE_FALSE));
        coefficients.put(FALSE_TRUE_FALSE,
                coefficients.get(FALSE_FALSE_FALSE) ^
                        truthTable.getTable().get(FALSE_TRUE_FALSE));
        coefficients.put(FALSE_FALSE_TRUE,
                coefficients.get(FALSE_FALSE_FALSE) ^
                        truthTable.getTable().get(FALSE_FALSE_TRUE));
        coefficients.put(TRUE_TRUE_FALSE,
                coefficients.get(FALSE_FALSE_FALSE) ^ coefficients.get(TRUE_FALSE_FALSE) ^ coefficients.get(FALSE_TRUE_FALSE) ^
                        truthTable.getTable().get(FALSE_FALSE_TRUE));
        coefficients.put(TRUE_FALSE_TRUE,
                coefficients.get(FALSE_FALSE_FALSE) ^ coefficients.get(TRUE_FALSE_FALSE) ^ coefficients.get(FALSE_FALSE_TRUE) ^
                        truthTable.getTable().get(TRUE_FALSE_TRUE));
        coefficients.put(FALSE_TRUE_TRUE,
                coefficients.get(FALSE_FALSE_FALSE) ^ coefficients.get(FALSE_TRUE_FALSE) ^ coefficients.get(FALSE_FALSE_TRUE) ^
                        truthTable.getTable().get(FALSE_TRUE_TRUE));
        coefficients.put(TRUE_TRUE_TRUE,
                coefficients.get(FALSE_FALSE_FALSE) ^
                        coefficients.get(TRUE_FALSE_FALSE) ^ coefficients.get(FALSE_TRUE_FALSE) ^ coefficients.get(FALSE_FALSE_TRUE) ^
                        coefficients.get(TRUE_TRUE_FALSE) ^ coefficients.get(TRUE_FALSE_TRUE) ^ coefficients.get(FALSE_TRUE_TRUE) ^
                        truthTable.getTable().get(TRUE_TRUE_TRUE));
    }

    public static AlgebraicNormalFrom getFromTruthTable(TruthTable truthTable) {
        return new AlgebraicNormalFrom(truthTable);
    }

    private void initSummands() {
        summands = new ArrayList<>();
        for (Map.Entry<String, String> valuesToItem : VALUES_TO_SUMMANDS.entrySet()) {
            String values = valuesToItem.getKey();
            boolean includeItemToAnf = coefficients.get(values);
            // If 'a' coefficient near given summand is true (which means it equals 1),
            // we add this summand with given values to ANF.
            if (includeItemToAnf) {
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
            prefix = " ⊕ ";
        }
        return builder.toString();
    }
}
