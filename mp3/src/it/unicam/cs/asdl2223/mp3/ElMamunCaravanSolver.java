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

        // Inizializzo la tabella delle soluzioni ottimali
        int numOperands = expression.size() / 2 + 1;
        this.table = new Integer[numOperands][numOperands];
        this.tracebackTable = new Integer[numOperands][numOperands];

        // Il primo passo è semplicemente il valore del primo operando
        for (int i = 0; i < numOperands; i++) {
            table[i][i] = operandValue(i);
        }

        // Per ogni lunghezza di sotto-espressione iniziando dal secondo operando
        for (int i = 1; i < numOperands; i++) {
            // Calcolo il risultato massimo dell'espressione aritmetica
            // quando parentesizzata in questo passaggio per ogni possibile coppia di operandi
            for (int j = 0; j < numOperands - i; j++) {
                int maxResultAtStage = Integer.MIN_VALUE;
                int prevStageIndex = 0;

                // Controllo il risultato massimo dell'espressione aritmetica
                // quando parentesizzato in questo passaggio usando l'operando disponibile
                for (int k = j; k < j + i; k++) {
                    int resultAtStage = 0;

                    // Calcolo il risultato dell'espressione aritmetica
                    // quando parentesizzato in questo passaggio usando la coppia di operandi
                    int leftOperand = table[j][k];
                    int rightOperand = table[k + 1][j + i];
                    char operator = this.expression.get(2 * k + 1).getValue().toString().charAt(0);

                    if (operator == '+') {
                        resultAtStage = leftOperand + rightOperand;
                    } else if (operator == '-') {
                        resultAtStage = leftOperand - rightOperand;
                    } else if (operator == '*') {
                        resultAtStage = leftOperand * rightOperand;
                    } else if (operator == '/') {
                        resultAtStage = leftOperand / rightOperand;
                    }

                    // Aggiorno il risultato massimo e l'indice del passaggio precedente
                    // se il risultato calcolato è maggiore del precedente
                    if (resultAtStage > maxResultAtStage) {
                        maxResultAtStage = resultAtStage;
                        prevStageIndex = k;
                    }

                    // Aggiorno la tabella delle soluzioni ottimali e quella di traceback
                    table[j][j + i] = maxResultAtStage;
                    tracebackTable[j][j + i] = prevStageIndex;
                }
            }
            this.solved = true;
        }
    }


    /**
     * Returns the value of the operand at a given position in the expression.
     *
     * @param i the position of the operand in the expression
     * @return the value of the operand at the given position
     * @throws IllegalArgumentException if the position is not a valid position
     *                                  for an operand in the expression
     */
    private Integer operandValue(int i) {
        // TODO implementare (macmat)
        if (i < 0 || i >= expression.size() / 2 + 1)
            throw new IllegalArgumentException("Posizione non valida per un operando");
        return Integer.parseInt(expression.get(2 * i).toString());
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
        int numOperands = operandValue(expression.size() / 2 + 1);
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
        int numOperands = operandValue(expression.size() / 2 + 1);

        // Creo un oggetto StringBuilder per costruire la stringa di parentesi
        StringBuilder parenthesization = new StringBuilder();

        // Se l'espressione contiene solo un operando, ritorno quell'operando come una stringa
        if (numOperands == 1) {
            return String.valueOf(operandValue(0));
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
            parenthesization.append(operandValue(i));
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