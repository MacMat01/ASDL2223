import java.util.List;

// TODO completare import

/**
 * Implementazione dell'algoritmo di Insertion Sort integrata nel framework di
 * valutazione numerica. L'implementazione è in loco.
 *
 * @param <E> Una classe su cui sia definito un ordinamento naturale.
 * @author Template: Luca Tesei, Implementazione: Collettiva
 */
public class InsertionSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare
        if (l == null) {
            throw new NullPointerException("Tentativo di ordinare una lista null");
        }
        if (l.size() <= 1) {
            // per ordinare la lista vuota o con un solo elemento non faccio niente
            return new SortingAlgorithmResult<E>(l, 0);
        }
        int countCompare = 0;
        for (int i = 1; i < l.size(); i++) {
            E appoggio = l.get(i);
            int j = i - 1;
            while (j >= 0 && l.get(j).compareTo(appoggio) > 0) {
                countCompare++;
                l.set(j + 1, l.get(j));
                j--;
            }
            l.set(j + 1, appoggio);
        }
        return new SortingAlgorithmResult<E>(l, countCompare);
    }

    public String getName() {
        return "InsertionSort";
    }
}
