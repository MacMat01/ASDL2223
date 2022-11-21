/**
 * 
 */

import java.util.List;

/**
 * Classe che implementa un algoritmo di ordinamento basato su heap.
 * L'implementazione è in loco.
 * 
 * @author Luca Tesei
 *
 */
public class HeapSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    private int heapSize;

    private int numCompare;

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // usa una variante dei metodi della classe
        // MaxHeap in modo da implementare l'algoritmo utilizzando solo un array
        // (arraylist) e alcune variabili locali di appoggio (implementazione
        // cosiddetta "in loco" o "in place", si veda
        // https://it.wikipedia.org/wiki/Algoritmo_in_loco)
        if (l == null)
            throw new NullPointerException(
                    "Tentativo di ordinare una lista null");
        if (l.size() <= 1)
            // per ordinare la lista vuota o con un solo elemento non faccio niente
            return new SortingAlgorithmResult<E>(l, 0);
        // inizializzo heapSize
        this.heapSize = l.size();
        // inizilizzo numCompare
        this.numCompare = 0;
        // il primo nodo che ha almeno un figlio si trova in posizione (l.size()
        // / 2) - 1 dove / è la divisione intera
        for (int i = (l.size() / 2) - 1; i >= 0; i--) {
            heapify(l, i); // heapify fa riferimento alla lunghezza heapSize,
                           // non alla lunghezza effettiva di l
        }
        // Ora l è uno heap
        for (int i = l.size() - 1; i > 0; i--) {
            // il max è sempre in testa a l
            // scambio la testa con l'elemento i
            E app = l.get(i);
            l.set(i, l.get(0));
            l.set(0, app);
            // decremento la heapSize
            this.heapSize--;
            // chiamo heapify
            heapify(l, 0);
        }
        // ora l è ordinata
        return new SortingAlgorithmResult<E>(l, this.numCompare);
    }

    private void heapify(List<E> l, int i) {
        if (!hasLeft(i))
            return;
        int max = i;
        if (l.get(max).compareTo(l.get(leftIndex(i))) < 0)
            max = leftIndex(i);
        this.numCompare++;
        if (hasRight(i)) {
            this.numCompare++;
            if (l.get(max).compareTo(l.get(rightIndex(i))) < 0) {
                max = rightIndex(i);
            }
        }
        if (max == i)
            return; // ho finito
        // scambio i con max e richiamo la funzione ricorsivamente sull'indice
        // del nodo figlio uguale a max
        E app = l.get(i);
        l.set(i, l.get(max));
        l.set(max, app);
        heapify(l, max);

    }

    @Override
    public String getName() {
        return "HeapSort";
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int leftIndex(int i) {
        return 2 * i + 1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int rightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasRight(int i) {
        return rightIndex(i) < this.heapSize;
    }

    private boolean hasLeft(int i) {
        return leftIndex(i) < this.heapSize;
    }

}
