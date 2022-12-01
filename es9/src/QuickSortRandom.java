/**
 * 
 */

import java.util.List;
import java.util.Random;

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
        quickSortRandom(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<E>(l, this.countCompare);
    }

    private void quickSortRandom(List<E> l, int p, int r) {
        if (p < r) {
            // la porzione di array(list) contiene almeno due elementi, quindi
            // devo partizionare
            int q = partitionRandom(l, p, r);
            // q è la posizione del pivot
            // Chiamata ricorsiva sulla partizione [p,q-1]
            quickSortRandom(l, p, q - 1);
            // Chiamata ricorsiva sulla partizione [q+1,r]
            quickSortRandom(l, q + 1, r);
        }
        // nel caso in cui la porzione di arraylist contiene 1 elemento
        // (p=r) o è vuota (p>r), non faccio niente
    }

    private int partitionRandom(List<E> l, int p, int r) {
        // Consideriamo come pivot uno elemento a caso tra le
        // posizioni p ed r
        // Scelgo un numero a caso
        int shift = randomGenerator.nextInt(r - p + 1);
        // la posizione random scelta è p + shift che corrisponde a una
        // posizione a caso tra p ed r (inclusi)
        // sposto l'elemento in posizione p + shift con l'elemento in posizione
        // r, in modo che diventi lui il pivot; a meno che p+shift non sia già r
        if (p + shift != r) {
            // scambio
            E appoggio = l.get(p + shift);
            l.set(p + shift, l.get(r));
            l.set(r, appoggio);
        }
        // Chiamo la partizione normale
        return partition(l, p, r);
    }

    private int partition(List<E> l, int p, int r) {
        // Consideriamo l'elemento pivot
        E x = l.get(r);
        // Inizializziamo l'indice i
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            // effettuo un confronto tra il pivo e l'elemento in posizione j
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
        return "QuickSortRandom";
    }

}
