/**
 * 
 */

/**
 * Implementa una funzione hash primaria che usa il metodo della divisione.
 * 
 * @author Luca Tesei
 *
 */
public class DivisionPrimaryHashFunction implements PrimaryHashFunction {

    /*
     * (non-Javadoc)
     * 
     * @see it.unicam.cs.asdl1920.solhash.PrimaryHashFunction#hash(int, int)
     */
    @Override
    public int hash(int key, int m) {
        return Math.abs(key % m); // calcola il modulo della key, in caso di valore
                        // negativo considera il valore assoluto
    }

}
