
import java.util.List;

/**
 * Implementazione dell'algoritmo di Insertion Sort integrata nel framework di
 * valutazione numerica. L'implementazione è in loco.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *            Una classe su cui sia definito un ordinamento naturale.
 */
public class InsertionSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {
    public SortingAlgorithmResult<E> sort(List<E> l) {
        if (l==null)
            throw new NullPointerException("Tentativo di ordinare una lista null");
        if (l.size() <=1)
            return new SortingAlgorithmResult<E>(l, 0);
        int countCompare = 0;
        for (int i = 1; i < l.size(); i++) {
            E appoggio = l.get(i);
            int j = i - 1;
            while (j >= 0 && l.get(j).compareTo(appoggio) > 0) {
                // Devo spostarmi di uno verso sinistra
                // Copio il valore in posizione j nella posizione j + 1
                // Non perdo niente perché la posizione originale di i è
                // salvata in appoggio
                l.set(j + 1, l.get(j));
                j--;
            }
            // countCompare deve essere incrementato con il
            // numero di volte che è stato eseguito il while
            // + 1, cioè la valutazione della guardia che l'ha fatto uscire
            countCompare += i - j; // ((i-1) - j) + 1;
            // L'elemento salvato in appoggio va in posizione j + 1
            if (j != i - 1)
                l.set(j + 1, appoggio);
        }
        return new SortingAlgorithmResult<E>(l, countCompare);
    }

    public String getName() {
        return "InsertionSort";
    }
}
