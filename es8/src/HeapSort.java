/**
 *
 */

import java.util.List;


/**
 * Classe che implementa un algoritmo di ordinamento basato su heap.
 *
 * @author Template: Luca Tesei, Implementation: collettiva
 */
public class HeapSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        if (l == null) {
            throw new NullPointerException("Tentativo di ordinare una lista null");
        }
        if (l.size() <= 1) {
            // per ordinare la lista vuota o con un solo elemento non faccio niente
            return new SortingAlgorithmResult<E>(l, 0);
        }
        int countCompare = 0;
        for (int i = l.size() - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (l.get(j).compareTo(l.get(i)) > 0) {
                    E temp = l.get(i);
                    l.set(i, l.get(j));
                    l.set(j, temp);
                    countCompare++;
                }
            }
        }
        return new SortingAlgorithmResult<E>(l, countCompare);
    }

    @Override
    public String getName() {
        return "HeapSort";
    }
}
