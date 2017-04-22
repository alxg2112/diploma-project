import com.alxg2112.diploma.logic.forms.AlgebraicNormalFrom;
import com.alxg2112.diploma.logic.forms.DisjunctiveNormalForm;
import com.alxg2112.diploma.logic.forms.TruthTable;

public class TransformBooleanEquations {

    public static void main(String[] args) {
        int[] values = { 1, 0, 1, 1, 0, 1, 0, 1 };
        TruthTable truthTable = TruthTable.getFromValuesArray(values);
        AlgebraicNormalFrom anf = AlgebraicNormalFrom.getFromTruthTable(truthTable);
        DisjunctiveNormalForm dnf = DisjunctiveNormalForm.fromTruthTable(truthTable);
        System.out.println(anf);
        System.out.println(dnf);
    }
}
