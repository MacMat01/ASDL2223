
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Applica diversi algoritmi di ordinamento generici alle stesse sequenze di
 * lunghezza crescente. Per ogni lunghezza genera un certo numero dato di
 * sequenze. I dati relativi al numero di confronti e il tempo di esecuzione in
 * nanosecondi di ogni algoritmo su ogni sequenza sono scritti su un file .csv
 * (Comma Separated Values). In un altro file .csv sono riportate le sequenze
 * generate.
 * 
 * Il main può essere chiamato con il nome della directory di destinazione dei
 * file come parametro di linea di comando. Se non è presente nessun parametro
 * allora si assume la directory corrente.
 * 
 * @author Luca Tesei
 *
 */
public class SortingAlgorithmEvaluationFramework {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String dirName = null;
        if (args.length > 0)
            dirName = args[0];
        else
            dirName = ".";
        // Variabili per il conteggio del tempo di esecuzione
        long startTimeNano = 0;
        long elapsedTimeNano = 0;
        // Creo i file di output
        PrintStream o = null;
        PrintStream sequences = null;
        try {
            o = new PrintStream(new File(dirName + "/" + "evalfram.csv"));
            sequences = new PrintStream(
                    new File(dirName + "/" + "sequences.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Errore creazione file di ouput" + dirName + "/"
                    + "xxxx.csv");
            System.exit(1);
        }
        // Creo una lista di algoritmi generici di ordinamento
        List<SortingAlgorithm<Integer>> algs = new ArrayList<SortingAlgorithm<Integer>>();
        // Inserisco gli algoritmi che voglio testare
        algs.add(new BubbleSort<Integer>());
        algs.add(new InsertionSort<Integer>());
        algs.add(new MergeSort<Integer>());
        algs.add(new QuickSort<Integer>());
        algs.add(new QuickSortRandom<Integer>());
        algs.add(new HeapSort<Integer>());
        // Creo una lista di liste per contenere le copie delle liste da
        // ordinare, una per ogni algoritmo
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (@SuppressWarnings("unused")
        SortingAlgorithm<Integer> a : algs)
            lists.add(new ArrayList<Integer>());
        // Inserisco la linea di intestazione dei dati nei file csv
        o.print("SeqId,");
        for (SortingAlgorithm<Integer> a : algs) {
            o.print(a.getName() + "NComp,");
            o.print(a.getName() + "Tns,");
        }
        o.print("\n"); // Fine riga
        sequences.print("SeqId,");
        sequences.print("\n");

        // Generazione delle sequenze e dei dati
        // Creo un generatore di numeri casuali da inserire nella sequenza
        Random randomGenerator = new Random();
        // Indice per le lunghezze
        int n;
        // Contatore sequenze della stessa lunghezza per codice sequenza
        int count = 0;
        // Ciclo esterno
        for (n = SortingAlgorithmEvaluationFrameworkParameters.MIN_LENGTH; n <= SortingAlgorithmEvaluationFrameworkParameters.MAX_LENGTH; n += SortingAlgorithmEvaluationFrameworkParameters.INCREMENTO_LUNGHEZZA) {
            // Ciclo interno
            for (int i = 0; i < SortingAlgorithmEvaluationFrameworkParameters.NUMBER_OF_SAMPLES_PER_LENGTH; i++) {
                // Scrivo in output il nome della sequenza
                o.print("seq" + "_" + n + "_" + count + ",");
                sequences.print("seq" + "_" + n + "_" + count + ",");
                // Genero la sequenza
                for (int j = 0; j < n; j++) {
                    Integer x = new Integer(randomGenerator.nextInt(
                            SortingAlgorithmEvaluationFrameworkParameters.MAX_GENERATED_INTEGER));
                    // Aggiungo l'elemento a tutte le liste
                    for (List<Integer> l : lists)
                        l.add(x);
                    // Salvo l'elemento sul file delle sequenze
                    sequences.print(x.intValue() + ",");
                } // Sequenza generata
                sequences.print("\n"); // Fine riga
                System.out.println(
                        "Generata sequenza " + "seq" + "_" + n + "_" + count);
                // Incremento il puntatore della sequenza
                count++;
                // Indice associato ad ogni algoritmo per fare get sulla list
                // associata di Integer
                int idx = 0;
                // Chiamo tutti gli algoritmi di ordinamento sulla sequenza e
                // scrivo i risultati in output
                for (SortingAlgorithm<Integer> a : algs) {
                    // Clono la lista corrente per l'eventuale messaggio di
                    // errore
                    ArrayList<Integer> cloned = ((ArrayList<Integer>) lists
                            .get(idx));
                    cloned = (ArrayList<Integer>) cloned.clone();
                    // Guardo il tempo corrente in nanosecondi
                    startTimeNano = System.nanoTime();
                    // Chiamo l'algoritmo di ordinamento
                    SortingAlgorithmResult<Integer> result = a
                            .sort(lists.get(idx));
                    // Registro il tempo impiegato dall'algoritmo
                    elapsedTimeNano = System.nanoTime() - startTimeNano;
                    // Controllo se l'ordinamento è stato effettuato
                    // correttamente
                    if (!result.checkOrder()) {
                        // Stampo un messaggio di errore e lancio una eccezione
                        o.close();
                        sequences.close();
                        System.out.println("L'algoritmo " + a.getName()
                                + " non ha ordinato correttamente la sequenza "
                                + cloned.toString()
                                + "\nSequenza ordinata non corretta risultante: "
                                + result.toString());
                        throw new SortingException("L'algoritmo " + a.getName()
                                + " non ha ordinato correttamente la sequenza "
                                + cloned.toString()
                                + "\nSequenza ordinata non corretta risultante: "
                                + result.toString());
                        // Il framework termina con errore
                    }
                    // Scrivo sul file di output
                    o.print(result.getCountCompare() + ",");
                    o.print(elapsedTimeNano + ",");
                    idx++;
                }
                o.print("\n"); // Fine riga
                // Azzero tutte le liste
                for (List<Integer> l : lists)
                    l.clear();
            } // End for interno
            count = 0; // riazzero il contatore
        } // End for esterno
        o.close();
        sequences.close();
    } // end main

}
