/**
 * 
 */

import java.util.List;

/**
 * Implementazione del QuickSort con scelta della posizione del pivot fissa.
 * L'implementazione è in loco.
 * 
 * @author Luca Tesei
 * @param <E>
 *                il tipo degli elementi della sequenza da ordinare.
 *
 */
public class QuickSort<E extends Comparable<E>> implements SortingAlgorithm<E> {
    
    private int countCompare;

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        if (l == null)
            throw new NullPointerException(
                    "Tentativo di ordinare una lista null");
        if (l.size() <= 1)
            // nel caso di lista vuota o che contiene un solo elemento non
            // faccio niente
            return new SortingAlgorithmResult<E>(l, 0);
        // inizializzo il contatore, uso la variabile istanza come "variabile
        // globale" dei vari metodi privati
        this.countCompare = 0;
        // chiamo la procedura ricorsiva sull'intero array, ma solo se ci sono
        // almeno due elementi (altrimenti sarebbe uscito qui sopra)
        quickSort(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<E>(l, this.countCompare);
    }

    private void quickSort(List<E> l, int p, int r) {
        if (p < r) {
            // la porzione di array(list) contiene almeno due elementi, quindi
            // devo partizionare
            int q = partition(l, p, r);
            // q è la posizione del pivot
            // Chiamata ricorsiva sulla partizione [p,q-1]
            quickSort(l, p, q - 1);
            // Chiamata ricorsiva sulla partizione [q+1,r]
            quickSort(l, q + 1, r);
        }
        // nel caso in cui la porzione di arraylist contiene 1 elemento
        // (p=r) o è vuota (p>r), non faccio niente
    }

    private int partition(List<E> l, int p, int r) {
        // Consideriamo l'elemento pivot
        E x = l.get(r);
        // Inizializziamo l'indice i
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            // effettuo un confronto tra il pivot e l'elemento in posizione j
            int cmp = l.get(j).compareTo(x);
            this.countCompare++;
            if (cmp <= 0) {
                // l'elemento in posizione j è minore o uguale del pivot
                i = i + 1;
                // scambio l'elemento in posizione i con l'elemento in posizione
                // j
                E appoggio = l.get(i);
                l.set(i, l.get(j));
                l.set(j, appoggio);
            }
        }
        // Scambio l'elemento pivot (in posizione r) con l'elemento in posizione
        // i+1 che è il primo degli elementi maggiori del pivot
        E appoggio = l.get(r);
        l.set(r, l.get(i + 1));
        l.set(i + 1, appoggio);
        // Ritorno la nuova posizione del pivot
        return i + 1;
    }

    @Override
    public String getName() {
        return "QuickSort";
    }

}
