import java.util.List;

/**
 * Interfaccia per algoritmi di ordinamento generici.
 *
 * @param <E> Tipo degli elementi delle sequenze da ordinare. La classe E
 *            deve avere un ordinamento naturale totale fra i suoi elementi
 *            realizzato tramite l'implementazione dell'interfaccia
 *            Comparable.
 * @author Luca Tesei
 */
public interface SortingAlgorithm<E extends Comparable<E>> {

    /**
     * Ordina una lista di elementi in accordo all'ordinamento totale naturale
     * definito nella classe degli elementi. Non sono ammessi elementi null
     * nella lista da ordinare.
     *
     * @param l la lista da ordinare (dovrebbe essere una ArrayList)
     * @return un oggetto contentente la lista ordinata e il numero di
     * operazioni di comparazione effettuate dall'algoritmo.
     * @throws NullPointerException se la lista l è null oppure se qualche
     *                              elemento della lista è null
     */
    public SortingAlgorithmResult<E> sort(List<E> l);

    /**
     * Restituisce il nome dell'algoritmo di ordinamento.
     *
     * @return il nome dell'algoritmo
     */
    public String getName();

}