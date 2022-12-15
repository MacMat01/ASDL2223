/**
 *
 */
package it.unicam.cs.asdl2223.mp3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luca Tesei
 */
class ElMamunCaravanSolverTest {

    private final ObjectiveFunction max = new MaximumFunction();
    private final ObjectiveFunction min = new MinimumFunction();

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#ElMamunCaravanSolver(it.unicam.cs.asdl2021.mp2sol.Expression)}.
     */
    @Test
    final void testElMamunCaravanSolver() {
        Expression f = new Expression("1+2*3*4+5");
        @SuppressWarnings("unused") ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        assertThrows(NullPointerException.class, () -> new ElMamunCaravanSolver(null));
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve1maxv() {
        Expression f = new Expression("1+2*3*4+5");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals(81, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve1maxp() {
        Expression f = new Expression("1+2*3*4+5");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals("((1+2)*(3*(4+5)))", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve1minv() {
        Expression f = new Expression("1+2*3*4+5");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals(30, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve1minp() {
        Expression f = new Expression("1+2*3*4+5");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals("(1+((2*(3*4))+5))", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve2maxv() {
        Expression f = new Expression("1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals(1, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve2maxp() {
        Expression f = new Expression("1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals("1", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve2minv() {
        Expression f = new Expression("1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals(1, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve2minp() {
        Expression f = new Expression("1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals("1", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve3maxv() {
        Expression f = new Expression("1*3");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals(3, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve3maxp() {
        Expression f = new Expression("1*3");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals("(1*3)", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve3minv() {
        Expression f = new Expression("1*3");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals(3, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve3minp() {
        Expression f = new Expression("1*3");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals("(1*3)", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve4maxv() {
        Expression f = new Expression("9*3+2");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals(45, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve4maxp() {
        Expression f = new Expression("9*3+2");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals("(9*(3+2))", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve4minv() {
        Expression f = new Expression("9*3+2");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals(29, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve4minp() {
        Expression f = new Expression("9*3+2");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals("((9*3)+2)", solver.getOptimalParenthesization());
    }


    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve5maxv() {
        Expression f = new Expression("1+2+3*3*2*1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals(36, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve5maxp() {
        Expression f = new Expression("1+2+3*3*2*1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(max);
        assertEquals("((1+(2+3))*(3*(2*1)))", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve5minv() {
        Expression f = new Expression("1+2+3*3*2*1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals(21, solver.getOptimalSolution());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#solve(it.unicam.cs.asdl2021.mp2sol.ObjectiveFunction)}.
     */
    @Test
    final void testSolve5minp() {
        Expression f = new Expression("1+2+3*3*2*1");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        solver.solve(min);
        assertEquals("(1+(2+(3*(3*(2*1)))))", solver.getOptimalParenthesization());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.mp2sol.ElMamunCaravanSolver#isSolved()}.
     */
    @Test
    final void testIsSolved() {
        Expression f = new Expression("1+2*3*4+5");
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);
        assertFalse(solver.isSolved());
        solver.solve(max);
        assertTrue(solver.isSolved());
    }

}
