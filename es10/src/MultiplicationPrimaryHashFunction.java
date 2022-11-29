/**
 * 
 */

/**
 * Implementa una funzione di hash primaria con il metodo della moltiplicazione.
 * 
 * @author Luca Tesei
 *
 */
public class MultiplicationPrimaryHashFunction implements PrimaryHashFunction {

    /* (non-Javadoc)
     * @see it.unicam.cs.asdl1920.solhash.PrimaryHashFunction#hash(int, int)
     */
    @Override
    public int hash(int key, int m) {
        double phi = (Math.sqrt(5) - 1) / 2;
        double v = key * phi;
        double v1 = m * (v - Math.floor(v));
        return Math.abs((int) v1);
    }

}
