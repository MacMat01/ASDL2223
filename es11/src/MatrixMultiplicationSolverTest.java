import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixMultiplicationSolverTest {

    @Test
    final void testMatrixMultiplicationSolver() {
        assertThrows(NullPointerException.class, () -> new MatrixMultiplicationSolver(null));
        ArrayList<Integer> p = new ArrayList<Integer>();
        assertThrows(IllegalArgumentException.class, () -> new MatrixMultiplicationSolver(p));
        p.add(30);
        assertThrows(IllegalArgumentException.class, () -> new MatrixMultiplicationSolver(p));
        p.add(20);
        MatrixMultiplicationSolver s = new MatrixMultiplicationSolver(p);
        assertTrue(s.getOptimalCost() == 0);
        assertTrue(s.getOptimalParenthesization().equals("A_{0}"));
        p.add(50);
        MatrixMultiplicationSolver s1 = new MatrixMultiplicationSolver(p);
        assertTrue(s1.getOptimalCost() == 30 * 20 * 50);
        assertTrue(s1.getOptimalParenthesization().equals("(A_{0} x A_{1})"));
    }

    @Test
    final void testGetOptimalSolution1() {
        ArrayList<Integer> p = new ArrayList<Integer>();
        p.add(30);
        p.add(35);
        p.add(15);
        p.add(5);
        p.add(10);
        p.add(20);
        p.add(25);
        MatrixMultiplicationSolver s = new MatrixMultiplicationSolver(p);
        assertTrue(s.getOptimalCost() == 15125);
        assertTrue(s.getOptimalParenthesization().equals("((A_{0} x (A_{1} x A_{2})) x ((A_{3} x A_{4}) x A_{5}))"));
    }

    @Test
    final void testGetOptimalSolution2() {
        ArrayList<Integer> p = new ArrayList<Integer>();
        p.add(15);
        p.add(200);
        p.add(10);
        p.add(1050);
        MatrixMultiplicationSolver s = new MatrixMultiplicationSolver(p);
        assertTrue(s.getOptimalCost() == 187500);
        assertTrue(s.getOptimalParenthesization().equals("((A_{0} x A_{1}) x A_{2})"));
    }

}
