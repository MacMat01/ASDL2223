import java.util.List;

/**
 * Un sover prende una certa sequenza di matrici da moltiplicare e calcola una
 * parentesizzazione ottima, cioè che minimizza il numero di moltiplicazione
 * scalari necessarie per moltiplicare tutte le matrici.
 *
 * @author Luca Tesei
 */
public class MatrixMultiplicationSolver {

    // sequenza delle dimensioni delle matrici da moltiplicare
    private List<Integer> p;

    // matrice dei costi minimi
    private int[][] m;

    // matrice delle scelte dei k che corrispondono al costo minimo
    private int[][] b;

    /**
     * Costruisce un solver per una certa sequenza di matrici da moltiplicare,
     * date le loro dimensioni righeXcolonne. Il calcolo della soluzione ottima
     * viene eseguito subito, cioè come parte di questo costruttore.
     *
     * @param p è una lista di valori che sono le dimensioni delle matrici,
     *          ad esempio se p = [10, 100, 5, 50] allora sto moltiplicando
     *          3 matrici (p.size() - 1) le cui dimensioni sono A_{0} =
     *          10x100, A_{1} = 100x5, A_{2} = 5x50
     * @throws NullPointerException     se la lista passata è null
     * @throws IllegalArgumentException se la lista p contiene meno di due
     *                                  elementi (cioè deve contenere almeno
     *                                  una matrice. Nel caso di una unica
     *                                  matrice la soluzione è 0 e la
     *                                  parentesizzazione è la matrice
     *                                  stessa, cioè "A_{0}")
     */
    public MatrixMultiplicationSolver(List<Integer> p) {
        if (p == null) throw new NullPointerException("Lista nulla");
        if (p.size() <= 1) throw new IllegalArgumentException("Lista di dimensione non valida");
        this.p = p;
        this.m = new int[p.size() - 1][p.size() - 1];
        this.b = new int[p.size() - 1][p.size() - 1];
        this.solve();
    }

    /*
     * Risolve il problema della parentesizzazione ottima con la programmazione
     * dinamica.
     */
    private void solve() {
        // inizializzo la diagonale principale della matrice m a zero
        for (int i = 0; i < p.size() - 1; i++) {
            m[i][i] = 0;
            b[i][i] = -1; // non c'è nessun k da usare
        }
        for (int h = 1; h < p.size() - 1; h++)
            for (int i = 0; i < p.size() - 1 - h; i++) {
                int j = i + h;
                // posso assegnare a m[i][j] il minimo dei valori al variare di
                // k
                m[i][j] = Integer.MAX_VALUE; // fa il ruolo di + infinito
                b[i][j] = -1; // valore dummy
                for (int k = i; k < j; k++) {
                    // Calcolo il numero di operazioni per moltiplicare (A_i x
                    // ... x A_k) e (A_{k+1} x ... x A_j)
                    int costo = p.get(i).intValue() * p.get(k + 1).intValue() * p.get(j + 1).intValue();
                    if (m[i][j] > (m[i][k] + m[k + 1][j] + costo)) {
                        // aggiorno il valore di m[i][j] per arrivare a
                        // calcolare il minimo
                        m[i][j] = m[i][k] + m[k + 1][j] + costo;
                        // salvo in b il k che corrisponde al minimo attuale
                        b[i][j] = k;
                    }
                }
            }
    }

    /**
     * Restituisce il numero minimo di moltiplicazioni necessarie per
     * moltiplicare la sequenza di matrici di questo solver.
     *
     * @return il numero minimo di moltiplicazioni soluzione del problema di
     * parentesizzazione
     */
    public int getOptimalCost() {
        return m[0][p.size() - 2];
    }

    /**
     * Restituisce una parentesizzazione ottima.
     * <p>
     * Il formato prevede l'uso di "A_{i}" per indicare la i-esima matrice di
     * dimensione p.get(i) x p.get(i+1) con 0 <= i <= p.size() - 2. Ad esempio
     * la parentesizzazione con una sola matrice deve restituire "A_{0}", la
     * parentesizzazione con due matrici deve restituire "(A_{0} x A_{1})", la
     * parentesizzazione con tre matrici deve restituire "((A_{0} x A_{1}) x
     * A_{2})" oppure "(A_{0} x (A_{1} x A_{2}))" e così via.
     *
     * @return una parentesizzazione ottima
     */
    public String getOptimalParenthesization() {
        return traceBack(0, p.size() - 2);
    }

    /*
     * Effettua il traceback utilizzando la matrice b che è stata riempita
     * appositamente durante il processo di calcolo del costo minimo
     */
    private String traceBack(int i, int j) {
        if (i == j) return "A_{" + i + "}";
        // i < j
        return "(" + traceBack(i, b[i][j]) // b[i][j]== k
                + " x " + traceBack(b[i][j] + 1, j) + ")";
    }

}
