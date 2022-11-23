/**
 *
 */

import java.util.List;

// TODO completare import

/**
 * Implementazione dell'algoritmo di Merge Sort integrata nel framework di
 * valutazione numerica. Non è richiesta l'implementazione in loco.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class MergeSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

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
        List<E> l1 = l.subList(0, l.size() / 2);
        List<E> l2 = l.subList(l.size() / 2, l.size());
        SortingAlgorithmResult<E> result1 = sort(l1);
        SortingAlgorithmResult<E> result2 = sort(l2);
        countCompare += result1.getCountCompare() + result2.getCountCompare();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l1.size() && j < l2.size()) {
            countCompare++;
            if (l1.get(i).compareTo(l2.get(j)) < 0) {
                l.set(k, l1.get(i));
                i++;
            } else {
                l.set(k, l2.get(j));
                j++;
            }
            k++;
        }
        while (i < l1.size()) {
            l.set(k, l1.get(i));
            i++;
            k++;
        }
        while (j < l2.size()) {
            l.set(k, l2.get(j));
            j++;
            k++;
        }
        return new SortingAlgorithmResult<E>(l, countCompare);
    }

    public String getName() {
        return "MergeSort";
    }
}
