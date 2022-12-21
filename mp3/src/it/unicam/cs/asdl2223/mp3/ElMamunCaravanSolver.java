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

    // creo una lista di candidati per la funzione best
    private List<Integer> candidates;

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

        // inizializzo la lista di candidati
        this.candidates = new ArrayList<>();
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
        if (function == null) throw new NullPointerException("This function is null!");

        // riempio la diagonale della tabella con le cifre dell'espressione
        for (int i = 0; i < expression.size(); i += 2) {

            // se l'elemento è un numero
            table[i][i] = (Integer) expression.get(i).getValue();
        }

        // scorro l'espressione per riempire la tabella con le soluzioni ottimali
        for (int h = 0; h < expression.size(); h += 2)
            for (int j = 0; j < expression.size() - h; j += 2) {
                int i = j + h;

                // se j < i allora uso un ciclo per variare il valore di k
                if (j < i) {

                    // controllo se i e j sono numeri
                    // k deve variare di due in due per tutti i valori consentiti
                    for (int k = 0; k + j + 2 <= i; k += 2) {

                        //separo i due casi in cui posso trovare il + o il *
                        if (expression.get(j + k + 1).getValue().equals("+")) {
                            candidates.add(table[j][j + k] + table[j + k + 2][i]);
                        } else {
                            candidates.add(table[j][j + k] * table[j + k + 2][i]);
                        }
                    }

                    // salvo la soluzione ottimale
                    table[j][i] = function.getBest(candidates);

                    // salvo il valore della funzione getBestIndex nella tabella
                    tracebackTable[j][i] = function.getBestIndex(candidates) * 2;

                    // svuoto la lista dei candidati
                    candidates.clear();
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

        // ritorno la soluzione ottimale
        return table[0][expression.size() - 1];
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

        // ritorno la parentesizzazione ottimale
        return traceback(0, expression.size() - 1);
    }

    /**
     * Metodo ricorsivo per il traceback
     *
     * @param i indice iniziale
     * @param j indice finale
     * @return la parentesizzazione ottimale
     */
    private String traceback(int i, int j) {

        // se i e j sono uguali allora ritorno la cifra
        if (i == j) {
            return expression.get(i).getValue().toString();
        }

        // altrimenti ritorno la parentesizzazione ottimale
        return "(" + traceback(i, i + tracebackTable[i][j]) + expression.get(i + tracebackTable[i][j] + 1).getValue() + traceback(i + tracebackTable[i][j] + 2, j) + ")";
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