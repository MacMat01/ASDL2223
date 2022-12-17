package it.unicam.cs.asdl2223.mp3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that solves an instance of the El Mamun's Caravan problem using
 * dynamic programming.
 * <p>
 * Template: Daniele Marchei and Luca Tesei, Implementation: Matteo Machella
 * matteo.machella@studenti.unicam.it
 */
public class ElMamunCaravanSolver {

    // the expression to analyse
    private final Expression expression;

    // table to collect the optimal solution for each sub-problem,
    // protected just for Junit Testing purposes
    protected Integer[][] table;

    // table to record the chosen optimal solution among the optimal solution of
    // the sub-problems, protected just for JUnit Testing purposes
    protected Integer[][] tracebackTable;

    // flag indicating that the problem has been solved at least once
    private boolean solved;

    /**
     * Create a solver for a specific expression.
     *
     * @param expression The expression to work on
     * @throws NullPointerException if the expression is null
     */
    public ElMamunCaravanSolver(Expression expression) {
        if (expression == null) {
            throw new NullPointerException("Creazione di solver con expression null");
        }
        this.expression = expression;
        // TODO implementare

        // imposto il flag a false
        this.solved = false;

        // creo la tabella
        this.table = new Integer[expression.size()][expression.size()];
        this.tracebackTable = new Integer[expression.size()][expression.size()];

    }

    /**
     * Returns the expression that this solver analyse.
     *
     * @return the expression of this solver
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Solve the problem on the expression of this solver by using a given
     * objective function.
     *
     * @param function The objective function to be used when deciding which
     *                 candidate to choose
     * @throws NullPointerException if the objective function is null
     */
    public void solve(ObjectiveFunction function) {
        // TODO implementare

        // controllo che la funzione non sia nulla
        if (function == null) {
            throw new NullPointerException("La funzione non può essere nulla");
        }

        if (this.solved) {
            return;
        }

        // itero su tutte le celle della tabella
        for (int i = 0; i < expression.size(); i++) {
            for (int j = 0; j < expression.size(); j++) {

                // riempio la diagonale con le cifre
                if (i == j && expression.get(i).getType() == ItemType.DIGIT) {
                    this.table[i][j] = (Integer) expression.get(i).getValue();
                }

                if (i < j && expression.get(i).getType() == ItemType.DIGIT && expression.get(j).getType() == ItemType.DIGIT) {

                    // inizializzo la lista dei candidati
                    List<Integer> candidates = new ArrayList<>();

                    // itero per tutti i possibili valori di k
                    for (int k = 0; i + k + 2 <= j; k += 2) {

                        //aggiungo ai candidati questo valore di k
                        candidates.add(this.table[i][i + k]);
                        candidates.add((Integer) this.expression.get(i + k + 1).getValue());
                        candidates.add(this.table[i + k + 2][j]);

                        // aggiungo il valore ottimo alla tabella
                        this.table[i][j] = function.getBest(candidates);

                        // libero i candidati
                        candidates.clear();
                    }
                }
            }
        }

        // imposto il flag a true
        this.solved = true;
    }

    /**
     * Returns the current optimal value for the expression of this solver. The
     * value corresponds to the one obtained after the last solving (which used
     * a particular objective function).
     *
     * @return the current optimal value
     * @throws IllegalStateException if the problem has never been solved
     */
    public int getOptimalSolution() {
        // TODO implementare

        // controllo se il problema è stato risolto almeno una volta
        if (!this.solved) {
            throw new IllegalStateException("Il problema non è mai stato risolto");
        }

        // ritorno la soluzione ottima
        return this.table[0][this.expression.size() - 1];
    }

    /**
     * Returns an optimal parenthesization corresponding to an optimal solution
     * of the expression of this solver. The parenthesization corresponds to the
     * optimal value obtained after the last solving (which used a particular
     * objective function).
     * <p>
     * If the expression is just a digit then the parenthesization is the
     * expression itself. If the expression is not just a digit then the
     * parethesization is of the form "(<parenthesization>)". Examples: "1",
     * "(1+2)", "(1*(2+(3*4)))"
     *
     * @return the current optimal parenthesization for the expression of this
     * solver
     * @throws IllegalStateException if the problem has never been solved
     */
    public String getOptimalParenthesization() {
        // TODO implementare

        // controllo se il problema è stato risolto almeno una volta
        if (!this.solved) {
            throw new IllegalStateException("Il problema non è mai stato risolto");
        }

        // ritorno la parentesizzazione ottima
        return this.tracebackTable[0][this.expression.size() - 1].toString();
    }

    /**
     * Metodo ricorsivo per il traceback
     *
     * @param i indice iniziale
     * @param j indice finale
     * @return la parentesizzazione ottimale
     */
    private String traceback(int i, int j) {
        if (i == j) return this.expression.get(i).toString();
        else {
            int k = this.tracebackTable[i][j];
            return "(" + this.traceback(i, k) + this.expression.get(k + 1).toString() + this.traceback(k + 1, j) + ")";
        }
    }

    /**
     * Determines if the problem has been solved at least once.
     *
     * @return true if the problem has been solved at least once, false
     * otherwise.
     */
    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public String toString() {
        return "ElMamunCaravanSolver for " + expression;
    }
}