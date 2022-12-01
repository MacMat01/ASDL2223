
/**
 * Parametri generali del framework di valutazione degli algoritmi di
 * ordinamento.
 * 
 * @author Luca Tesei
 *
 */
public interface SortingAlgorithmEvaluationFrameworkParameters {
    /**
     * Lunghezza minima delle sequenze da generare
     */
    public static int MIN_LENGTH = 50;

    /**
     * Passo di incremento della lunghezza da MIN_LENGTH a MAX_LENGTH
     */
    public static int INCREMENTO_LUNGHEZZA = 50;

    /**
     * Lunghezza massima delle sequenze da generare
     */
    public static int MAX_LENGTH = 1500;

    /**
     * Numero di sequenze da generare per lunghezza
     */
    public static int NUMBER_OF_SAMPLES_PER_LENGTH = 100;

    /**
     * Maximum non-negative integer number randomly generated in sample
     * sequences
     */
    public static int MAX_GENERATED_INTEGER = 1500;

}
