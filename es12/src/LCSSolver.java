/**
 * Un oggetto di questa classe è un risolutore del problema della più lunga
 * sottosequenza comune tra due stringhe date.
 *
 * @author Luca Tesei
 */
public class LCSSolver {

    /* Prima stringa */
    private final String x;

    /* Seconda stringa */
    private final String y;

    /* Matrice per il calcolo di una soluzione ottima */
    private final int[][] m;

    /*
     * Flag che indica che questo solver ha svolto il proprio calcolo. Alla
     * creazione del solver non viene svolto il calcolo. Esso viene eseguito
     * successivamente alla chiamata del metodo solve()
     */
    private boolean isSolved;

    /**
     * Costruisce un risolutore LCS fra due stringhe date.
     *
     * @param x la prima stringa
     * @param y la seconda stringa
     * @throws NullPointerException se almeno una delle due stringhe passate
     *                              è nulla
     */
    public LCSSolver(String x, String y) {
        if (x == null || y == null)
            throw new NullPointerException("Creazione di un solver con una o due stringhe null");
        this.x = x;
        this.y = y;
        // creo la matrice
        this.m = new int[this.x.length() + 1][this.y.length() + 1];
        this.isSolved = false;
    }

    /*
     * Determina se una stringa è sottosequenza di un'altra stringa.
     *
     * @param z la stringa da testare
     *
     * @param w la stringa di cui z dovrebbe essere sottosequenza
     *
     * @return true se z è sottosequenza di w, false altrimenti
     */
    private static boolean isSubsequence(String z, String w) {
        // Caso base
        if (z.equals("")) return true;
        // considero il primo carattere di z
        char c = z.charAt(0);
        // cerco la prima occorrenza di c in w
        int i = w.indexOf(c);
        if (i != -1)
            // richiamo la funzione sul resto di z dopo aver fatto match del
            // primo carattere di z in w
            return isSubsequence(z.substring(1), w.substring(i + 1));
            // il primo carattere di z non occorre mai in w, quindi z non può essere
            // una sottosequenza
        else return false;
    }

    /**
     * @return the string x
     */
    public String getX() {
        return x;
    }

    /**
     * @return the string y
     */
    public String getY() {
        return y;
    }

    /**
     * Risolve il problema LCS delle due stringhe di questo solver, se non è
     * stato già risolto precedentemente. Dopo l'esecuzione di questo metodo la
     * prima volta il problema verrà considerato risolto.
     */
    public void solve() {
        if (this.isSolved) return;
        // inizializzazione a zero (si può evitare perché le caselle dell'array
        // sono già inizializzate a zero)
        for (int i = 0; i <= this.x.length(); i++) {
            this.m[i][0] = 0;
        }
        // inizializzazione a zero (si può evitare perché le caselle dell'array
        // sono già inizializzate a zero)
        for (int j = 1; j <= this.y.length(); j++) {
            this.m[0][j] = 0;
        }
        // Ciclo di riempimento della matrice
        for (int i = 1; i <= this.x.length(); i++)
            for (int j = 1; j <= this.y.length(); j++) {
                if (this.x.charAt(i - 1) == this.y.charAt(j - 1)) {
                    this.m[i][j] = this.m[i - 1][j - 1] + 1;
                } else if (this.m[i - 1][j] >= this.m[i][j - 1]) {
                    this.m[i][j] = this.m[i - 1][j];
                } else {
                    this.m[i][j] = this.m[i][j - 1];
                }
            }
        // Cambio del flag
        this.isSolved = true;
    }

    /**
     * Determina se questo solver ha già risolto il problema.
     *
     * @return true se il problema LCS di questo solver è già stato risolto
     * precedentemente, false altrimenti
     */
    public boolean isSolved() {
        return this.isSolved;
    }

    /**
     * Determina la lunghezza massima delle sottosequenze comuni.
     *
     * @return la massima lunghezza delle sottosequenze comuni di x e y.
     * @throws IllegalStateException se il solver non ha ancora risolto il
     *                               problema LCS
     */
    public int getLengthOfSolution() {
        if (!this.isSolved)
            throw new IllegalStateException("Richiesta delle soluzioni prima della risoluzione del problema");
        return this.m[this.x.length()][this.y.length()];
    }

    /**
     * Restituisce una soluzione del problema LCS.
     *
     * @return una sottosequenza di this.x e this.y di lunghezza massima
     * @throws IllegalStateException se il solver non ha ancora risolto il
     *                               problema LCS
     */
    public String getOneSolution() {
        if (!this.isSolved)
            throw new IllegalStateException("Richiesta delle soluzioni prima della risoluzione del problema");
        return traceBack(this.x.length(), this.y.length());
    }

    /*
     * NOTA: Determina una soluzione ottime ripercorrendo le caselle della
     * matrice m seguendo le condizioni con cui sono state costruite. In questo
     * caso non deve venire usata una matrice di "supporto" per ricostruire una
     * soluzione ottima, ma va usata la stessa matrice m
     */
    private String traceBack(int i, int j) {
        if (i == 0 || j == 0) {
            return "";
        }
        if (this.x.charAt(i - 1) == this.y.charAt(j - 1)) {
            return traceBack(i - 1, j - 1) + this.x.charAt(i - 1);
        } else if (this.m[i - 1][j] >= this.m[i][j - 1]) {
            return traceBack(i - 1, j);
        } else {
            return traceBack(i, j - 1);
        }
    }

    /**
     * Determina se una certa stringa è una sottosequenza comune delle due
     * stringhe di questo solver.
     *
     * @param z la string da controllare
     * @return true se z è sottosequenza di this.x e di this.y, false altrimenti
     * @throws NullPointerException se z è null
     */
    public boolean isCommonSubsequence(String z) {
        if (z == null) throw new NullPointerException("Test di una sequenza nulla");
        return isSubsequence(z, this.x) && isSubsequence(z, this.y);
    }
}
