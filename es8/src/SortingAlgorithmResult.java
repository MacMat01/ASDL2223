import java.util.List;

/**
 * Risultato di un algoritmo di ordinamento. Contiene la sequenza ordinata di
 * elementi e il numero di operazioni di confronto effettuate.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *                Tipo degli elementi della sequenza ordinata.
 */
public class SortingAlgorithmResult<E extends Comparable<E>> {

    private List<E> l;

    private int countCompare;

    /**
     * Costruisce un risultato di un algoritmo di odinamento.
     * 
     * @param l
     *                         una lista ordinata in maniera crescente
     * @param countCompare
     *                         numero di operazioni di confronto effettuate
     *                         durante l'ordinamento
     */
    public SortingAlgorithmResult(List<E> l, int countCompare) {
        this.l = l;
        this.countCompare = countCompare;
    }

    /**
     * Restituisce la lista ordinata.
     * 
     * @return la lista ordinata
     */
    public List<E> getL() {
        return l;
    }

    /**
     * Restituisce il numero di confronti effettuati.
     * 
     * @return il numero di confronti effettuati.
     */
    public int getCountCompare() {
        return countCompare;
    }

    /**
     * Controlla che la lista restituita sia in ordine crescente.
     * 
     * @return true se la lista è ordinata in maniera crescente, false
     *         altrimenti
     */
    public boolean checkOrder() {
        for (int i = 0; i < l.size() - 1; i++)
            if (this.l.get(i).compareTo(this.l.get(i+1)) > 0)
                return false;
        return true;
    }

}
