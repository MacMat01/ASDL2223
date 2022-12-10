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

    // variabile usata per il metodo getBest
    private int k = 0;

    /**
     * Create a solver for a specific expression.
     *
     * @param expression The expression to work on
     * @throws NullPointerException if the expression is null
     */
    public ElMamunCaravanSolver(Expression expression) {
        if (expression == null) throw new NullPointerException("Creazione di solver con expression null");
        this.expression = expression;
        // TODO implementare
        /*
         * Inizializzare la tabella delle soluzioni ottimali e quella di traceback
         * con dimensioni sufficienti per contenere tutte i sotto problemi
         * dell'espressione.
         */
        this.table = new Integer[expression.size()][expression.size()];
        this.tracebackTable = new Integer[expression.size()][expression.size()];
        this.solved = false;
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
        if (function == null) throw new NullPointerException("Funzione obiettivo nulla");
        if (this.solved) return;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < this.expression.size(); i++) {
            for (int j = 0; j < this.expression.size(); j++) {
                if (i == j && this.expression.get(i).getType() == ItemType.DIGIT) {
                    this.table[i][j] = Integer.parseInt(this.expression.get(i).toString());
                    this.tracebackTable[i][j] = i;
                } else if (i < j && this.expression.get(i).getType() == ItemType.DIGIT && this.expression.get(j).getType() == ItemType.DIGIT) {
                    candidates.add(this.table[i][i + k(i, j)]);
                    candidates.add(Integer.parseInt(this.expression.get(i).toString()) + k(i, j) + 1);
                    candidates.add(this.table[i + k(i, j) + 2][j]);
                    this.table[i][j] = getBest(candidates);
                }
            }
        }
    }


    private int k(int i, int j) {
        // TODO implementare
        int k = 0;
        if (k + i + 2 <= j) {
            return k;
        }
        throw new IllegalStateException("k non rispetta i vincoli");
    }


    /**
     * Returns the optimal value of the expression of this solver, according to
     * the logic of the objective function used in the last call to the solve
     * method.
     *
     * @param candidates The list of candidate values
     * @return the optimal value of the expression of this solver
     * @throws NullPointerException if candidates is null
     * @see #solve(ObjectiveFunction)
     */
    private Integer getBest(List<Integer> candidates) {
        // TODO implementare
        if (candidates == null) throw new NullPointerException("Lista di candidati nulla");
        if (candidates.isEmpty()) return null;
        Integer best = candidates.get(0);

        return best;
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
        if (!this.solved) throw new IllegalStateException("Il problema non è stato risolto");
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
        if (!this.solved) throw new IllegalStateException("Il problema non è stato risolto");
        return this.traceback(0, this.expression.size() - 1);
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

    // TODO implementare: inserire eventuali metodi privati per rendere
    // l'implementazione più modulare
}
