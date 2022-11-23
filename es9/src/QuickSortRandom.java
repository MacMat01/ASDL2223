/**
 *
 */

import java.util.List;
import java.util.Random;

//TODO completare import

/**
 * Implementazione del QuickSort con scelta della posizione del pivot scelta
 * randomicamente tra le disponibili. L'implementazione è in loco.
 *
 * @param <E> il tipo degli elementi della sequenza da ordinare.
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class QuickSortRandom<E extends Comparable<E>> implements SortingAlgorithm<E> {

    private static final Random randomGenerator = new Random();

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare
        if (l == null) {
            throw new NullPointerException("Tentativo di ordinare una lista null");
        }
        if (l.size() <= 1) {
            // per ordinare la lista vuota o con un solo elemento non faccio
            // niente
            return new SortingAlgorithmResult<E>(l, 0);
        }
        int countCompare = 0;
        int pivot = randomGenerator.nextInt(l.size());
        int i = 0;
        int j = l.size() - 1;
        E appoggio = null;
        while (i < j) {
            countCompare++;
            if (l.get(i).compareTo(l.get(j)) > 0) {
                appoggio = l.get(i);
                l.set(i, l.get(j));
                l.set(j, appoggio);
                pivot = j;
                j = i;
                i = pivot;
            }
            if (i < j) {
                i++;
            }
            if (i < j) {
                j--;
            }
        }
        List<E> l1 = l.subList(0, pivot);
        List<E> l2 = l.subList(pivot + 1, l.size());
        sort(l1);
        sort(l2);
        return new SortingAlgorithmResult<E>(l, countCompare);
    }

    @Override
    public String getName() {
        return "QuickSortRandom";
    }

}
