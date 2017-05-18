import com.alxg2112.diploma.logic.forms.AlgebraicNormalFrom;
import com.alxg2112.diploma.logic.forms.ConjuctiveNormalForm;
import com.alxg2112.diploma.logic.forms.DisjunctiveNormalForm;
import com.alxg2112.diploma.logic.forms.TruthTable;

public class Test {

    public static void main(String[] args) {
        int[] values = { 1, 0, 1, 1, 0, 1, 0, 1 };
        TruthTable truthTable = TruthTable.fromArray(values);
        AlgebraicNormalFrom anf = AlgebraicNormalFrom.getFromTruthTable(truthTable);
        DisjunctiveNormalForm dnf = DisjunctiveNormalForm.fromTruthTable(truthTable);
        ConjuctiveNormalForm cnf = ConjuctiveNormalForm.fromTruthTable(truthTable);
        System.out.printf("Truth table: %n%s", truthTable);
        System.out.printf("ANF: %s%n", anf);
        System.out.printf("DNF: %s%n", dnf);
        System.out.printf("CNF: %s%n", cnf);
    }
}
