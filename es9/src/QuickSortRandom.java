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
 * @author Template: Luca Tesei, Implementazione: collettiva
 * @param <E>
 *                il tipo degli elementi della sequenza da ordinare.
 *
 */
public class QuickSortRandom<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    private static final Random randomGenerator = new Random();

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare
        return null;
    }

    @Override
    public String getName() {
        return "QuickSortRandom";
    }

}
