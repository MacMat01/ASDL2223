/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Merge Sort integrata nel framework di
 * valutazione numerica. Non è richiesta l'implementazione in loco.
 * 
 * @author Luca Tesei
 *
 */
public class MergeSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    private int countCompare;

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
        mergeSort(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<E>(l, this.countCompare);
    }

    private void mergeSort(List<E> l, int start, int stop) {
        if (start == stop) {
            // C'è solo un elemento, non faccio niente
            return;
        }
        int nElements = stop - start + 1;
        int middle = start + (nElements / 2) - 1;
        // L'elemento middle, nel caso in cui il numero degli
        // elementi è dispari, è scelto in modo che l'elemento
        // in più vada nella parte destra
        // se il numero degli elementi è due allora start == middle
        // Chiamata ricorsiva sulla parte sinistra
        this.mergeSort(l, start, middle);
        // Chiamata ricorsiva sulla parte destra
        this.mergeSort(l, middle + 1, stop);
        // Merge delle due parti ordinate
        this.merge(l, start, middle, stop);
    }

    private void merge(List<E> l, int start, int middle, int stop) {
        // Creo due liste accessorie per salvare gli elementi
        // dei due sottoarray ordinati di cui fare il merge
        // Sono costretto a farlo perché il risultato lo scrivo
        // su l, quindi mi scriverei sopra
        // Questo rende l'implementazione del merge sort **non** in loco
        List<E> leftCopy = new ArrayList<E>();
        for (int i = start; i <= middle; i++)
            leftCopy.add(l.get(i));
        List<E> rightCopy = new ArrayList<E>();
        for (int i = middle + 1; i <= stop; i++)
            rightCopy.add(l.get(i));
        int i, j, k;
        // i scorre su l da start a stop
        i = start;
        // j scorre su leftCopy da 0
        j = 0;
        // k scorre su rightCopy da 0
        k = 0;
        while (j < leftCopy.size() && k < rightCopy.size()) {
            // Esiste ancora un elemento in entrambe le sottoliste
            // Faccio un confronto
            this.countCompare++;
            if (leftCopy.get(j).compareTo(rightCopy.get(k)) < 0) {
                // Metto in posizione i l'elemento della leftCopy
                l.set(i, leftCopy.get(j));
                i++;
                j++;
            } else {
                // Metto in posizione i l'elemento della rightCopy
                l.set(i, rightCopy.get(k));
                i++;
                k++;
            }
        }
        // Una delle due sottoliste è stata completata
        if (j >= leftCopy.size())
            // Metto nella lista l tutti gli elementi che rimangono in rightCopy
            for (; k < rightCopy.size(); k++) {
                l.set(i, rightCopy.get(k));
                i++;
            }
        else
            // Metto nella lista l tutti gli elementi che rimangono in leftCopy
            for (; j < leftCopy.size(); j++) {
                l.set(i, leftCopy.get(j));
                i++;
            }
    }

    public String getName() {
        return "MergeSort";
    }
}
