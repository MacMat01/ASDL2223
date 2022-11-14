import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa uno heap binario che può contenere elementi non nulli
 * possibilmente ripetuti.
 *
 * @param <E> il tipo degli elementi dello heap, che devono avere un
 *            ordinamento naturale.
 * @author Template: Luca Tesei, Implementation: collettiva
 */
public class MaxHeap<E extends Comparable<E>> {

    /*
     * L'array che serve come base per lo heap
     */
    private ArrayList<E> heap;

    /**
     * Costruisce uno heap vuoto.
     */
    public MaxHeap() {
        this.heap = new ArrayList<E>();
    }

    /**
     * Restituisce il numero di elementi nello heap.
     *
     * @return il numero di elementi nello heap
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Determina se lo heap è vuoto.
     *
     * @return true se lo heap è vuoto.
     */
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Costruisce uno heap a partire da una lista di elementi.
     *
     * @param list lista di elementi
     * @throws NullPointerException se la lista è nulla
     */
    public MaxHeap(List<E> list) {
        if (list == null) {
            throw new NullPointerException("lista nulla");
        }
        this.heap = new ArrayList<E>(list);
    }

    /**
     * Inserisce un elemento nello heap
     *
     * @param el l'elemento da inserire
     * @throws NullPointerException se l'elemento è null
     */
    public void insert(E el) {
        if (el == null) {
            throw new NullPointerException("elemento nulla");
        }
        for (int i = 0; i < this.heap.size(); i++) {
            if (this.heap.get(i).compareTo(el) > 0) {
                this.heap.add(i, el);
                return;
            }
        }
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int leftIndex(int i) {
        // se è pari
        return 2 * i;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int rightIndex(int i) {
        // se è dispari
        return 2 * i + 1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int parentIndex(int i) {
        /*
         * Se i è pari, il genitore è in posizione i/2, altrimenti è in posizione
         * (i-1)/2
         */
        return i / 2;
    }

    /**
     * Ritorna l'elemento massimo senza toglierlo.
     *
     * @return l'elemento massimo dello heap oppure null se lo heap è vuoto
     */
    public E getMax() {
        /*
         * Se lo heap è vuoto, restituisce null
         */
        if (this.isEmpty()) {
            return null;
        }
        /*
         * Altrimenti restituisce il primo elemento
         */
        return this.heap.get(0);
    }

    /**
     * Estrae l'elemento massimo dallo heap. Dopo la chiamata tale elemento non
     * è più presente nello heap.
     *
     * @return l'elemento massimo di questo heap oppure null se lo heap è vuoto
     */
    public E extractMax() {
        if (this.isEmpty()) {
            return null;
        }
        E max = this.heap.get(0);
        this.heap.remove(0);
        return max;
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sotto alberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
        /*
         * Se il nodo in posizione i non ha figli, non c'è nulla da fare
         */
        if (this.heap.size() == 1) {
            return;
        }

        int left = leftIndex(i);
        int right = rightIndex(i);
        int max = i;
        /*
         * Se il nodo in posizione i ha un figlio sinistro, confronta il nodo in
         * posizione i con il figlio sinistro
         */
        if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(max)) > 0) {
            max = left;
        } else {
            max = i;
        }
        /*
         * Se il nodo in posizione i ha un figlio destro, confronta il nodo in
         * posizione i con il figlio destro
         */
        if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(max)) > 0) {
            max = right;
        }
        /*
         * Se il nodo in posizione i è minore di uno dei suoi figli, scambia il
         * nodo in posizione i con il figlio maggiore e ricorsivamente
         * ricostituisce lo heap a partire dal figlio maggiore
         */
        if (max != i) {
            E temp = this.heap.get(i);
            this.heap.set(i, this.heap.get(max));
            this.heap.set(max, temp);
            heapify(max);
        }
    }

    /**
     * Only for JUnit testing purposes.
     *
     * @return the arraylist representing this max heap
     */
    protected ArrayList<E> getHeap() {
        return this.heap;
    }
}
