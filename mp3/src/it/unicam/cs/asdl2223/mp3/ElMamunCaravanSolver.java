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

    private int operandValues[];

    private char operators[];

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

        // Inizializzo il flag solved a false
        this.solved = false;

        // Inizializzo operandValues e operators facendo il parsing dell'espressione
        this.operandValues = new int[expression.size() / 2 + 1];
        this.operators = new char[expression.size() / 2];
        int operandIndex = 0;
        int operatorIndex = 0;
        for (int i = 0; i < expression.size(); i++) {
            char c = expression.get(i).toString().charAt(0);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Il corrente carattere è un operatore
                // Lo aggiungo all'array operators e incremento l'indice
                operators[operatorIndex] = c;
                operatorIndex++;

            } else if (c >= '0' && c <= '9') {
                // Il corrente carattere è un operando
                // Faccio il parsing del valore dell'operando dall'espressione e lo aggiungo all'array operandValues
                // e incremento l'indice
                int operandValue = Character.getNumericValue(c);
                while (i + 1 < expression.size() && expression.get(i + 1).toString().charAt(0) >= '0' && expression.get(i + 1).toString().charAt(0) <= '9') {
                    operandValue = operandValue * 10 + Character.getNumericValue(expression.get(++i).toString().charAt(0));
                }
                operandValues[operandIndex] = operandValue;
                operandIndex++;
            }
        }

        // Inizializzo la tabella table e tracebackTable
        this.table = new Integer[operandValues.length][operandValues.length];
        this.tracebackTable = new Integer[operandValues.length][operandValues.length];
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

        // Controllo che la funzione obiettivo non sia null
        if (function == null) {
            throw new NullPointerException("La funzione obiettivo è null");
        }

        int numOperands = operandValues.length;

        // Imposto il caso base per la tabella table
        // Il caso base corrisponde ad una espressione con un singolo operando
        for (int i = 0; i < numOperands; i++) {
            table[i][i + 1] = operandValues[i];
        }

        // Itero per ogni sotto problema
        for (int subProblemSize = 2; subProblemSize <= numOperands; subProblemSize++) {
            for (int i = 0; i <= numOperands - subProblemSize; i++) {
                int j = i + subProblemSize;

                // Creo una lista di candidati per il sotto problema corrente
                List<Integer> candidates1 = new ArrayList<>();
                candidates1.add(table[i][i]);
                candidates1.add(table[i + 1][j]);

                // Inizializzo il valore ottimo del sotto problema corrente
                table[i][j] = function.getBest(candidates1);
                tracebackTable[i][j] = i;

                // Itero per ogni possibile posizione di divisione del sotto problema corrente
                for (int k = i + 1; k < j; k++) {
                    // Creo una lista di candidati per il sotto problema corrente
                    List<Integer> candidates2 = new ArrayList<>();
                    candidates2.add(table[i][k]);
                    candidates2.add(table[k][j]);

                    // Computa il valore della scelta corrente
                    int currentValue = function.getBest(candidates2);

                    // Creo una lista di candidati per il sotto problema corrente
                    List<Integer> candidates3 = new ArrayList<>();
                    candidates3.add(currentValue);
                    candidates3.add(table[i][j]);

                    // Aggiorno il valore ottimo e la scelta ottima del sotto problema corrente
                    // Se il valore corrente è migliore del valore ottimo del sotto problema corrente
                    if (function.getBest(candidates3) == currentValue) {
                        table[i][j] = currentValue;
                        tracebackTable[i][j] = k;
                    }
                }
            }
        }
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
        if (!this.solved) {
            throw new IllegalStateException("Il problema non è stato risolto");
        }

        // Restituisco il valore ottimale per l'espressione
        int numOperands = this.operandValues.length;
        return table[0][numOperands - 1];
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
        if (!this.solved) {
            throw new IllegalStateException("Il problema non è stato risolto");
        }
        int numOperands = this.operandValues.length;

        // Creo un oggetto StringBuilder per costruire la stringa di parentesi
        StringBuilder parenthesization = new StringBuilder();

        // Se l'espressione contiene solo un operando, ritorno quell'operando come una stringa
        if (numOperands == 1) {
            return String.valueOf(this.operandValues[0]);
        }

        // Uso un metodo ricorsivo di comodo per costruire la stringa di parentesi
        // iniziando dall'ultima posizione della tabella di traceback
        // passando l'indice del passaggio precedente
        buildParenthesization(0, numOperands - 1, parenthesization);

        // Restituisco la stringa di parentesi
        return parenthesization.toString();
    }

    /**
     * Metodo di comodo per costruire la stringa di parentesi
     *
     * @param i                indice dell'operando di sinistra
     * @param j                indice dell'operando di destra
     * @param parenthesization stringa di parentesi
     */
    private void buildParenthesization(int i, int j, StringBuilder parenthesization) {
        // TODO implementare (macmat)

        // Se l'operando di sinistra e quello di destra sono uguali, allora
        // l'espressione è composta da un solo operando
        if (i == j) {
            parenthesization.append(this.operandValues[i]);
            return;
        } else {
            // Altrimenti, aggiungo la parentesi di apertura
            parenthesization.append("(");
        }

        // Prendo l'indice del passaggio precedente dalla tabella di traceback
        int prevStageIndex = tracebackTable[i][j];

        // Divido l'espressione in due parti e chiamo ricorsivamente il metodo
        int leftStart = i;
        int leftEnd = tracebackTable[i][j];
        int rightStart = leftEnd + 1;
        int righEnd = j;

        // Chiamo ricorsivamente il mentodo per costruire la stringa di parentesi
        // per la parte di sinistra e per quella di destra
        buildParenthesization(leftStart, leftEnd, parenthesization);
        buildParenthesization(rightStart, righEnd, parenthesization);

        // Appendo l'operatore corrispondente alla stringa di parentesi
        parenthesization.append(expression.get(2 * prevStageIndex + 1).getValue());

        // Appendo la parentesi di chiusura
        parenthesization.append(")");
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