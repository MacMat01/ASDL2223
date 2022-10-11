/**
 * Un oggetto DataSetPolimorfo acquisisce un insieme di oggetti di classi che
 * implementano l'interfaccia <code>Measurable</code> ed è in grado di
 * restituire il minimo, il massimo e la media delle misurazioni degli oggetti
 * che sono stati aggiunti fino a quel momento.
 */
public class DataSetPolimorfo {

    /* Accumula i valori misurati degli oggetti inseriti */
    private double sum;

    /* Conta il numero degli oggetti inseriti */
    private int count;

    /* Oggetto Measurable con valore massimo attuale */
    private Measurable maximum;

    /* Oggetto Measurable con valore minimo attuale */
    private Measurable minimum;

    /**
     * Inizializza le variabili istanza con valori relativi ad un insieme di
     * dati vuoto.
     */
    public DataSetPolimorfo() {
        this.sum = 0;
        this.maximum = null;
        this.minimum = null;
        this.count = 0;
    }

    /**
     * Aggiunge un oggetto all'insieme di dati.
     *
     * @param x un oggetto di una classe misurabile dal misuratore
     */
    public void add(Measurable x) {
        this.sum += x.getMeasure();
        if (this.count == 0 || this.maximum.getMeasure() < x.getMeasure()) this.maximum = x;
        if (this.count == 0 || this.minimum.getMeasure() > x.getMeasure()) this.minimum = x;
        this.count++;
    }

    /**
     * Determina se il dataset è vuoto.
     *
     * @return true se al momento non ci sono oggetti inseriti
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Restituisce la media delle misure degli oggetti inseriti.
     *
     * @return la media
     * @throws IllegalStateException se il dataset è vuoto
     */
    public double getAverage() {
        if (this.count == 0) throw new IllegalStateException("Dataset vuoto");
        else return this.sum / this.count;
    }

    /**
     * Restituisce l'oggetto la cui misura è massima rispetto a tutti gli
     * oggetti inseriti.
     *
     * @return l'oggetto con misura massima
     * @throws IllegalStateException se il dataset è vuoto
     */
    public Measurable getMaximum() {
        if (this.count == 0) throw new IllegalStateException("Dataset vuoto");
        return this.maximum;
    }

    /**
     * Restituisce l'oggetto la cui misura è minima.
     *
     * @return l'oggetto con misura minima
     * @throws IllegalStateException se il dataset è vuoto
     */
    public Measurable getMinimum() {
        if (this.count == 0) throw new IllegalStateException("Dataset vuoto");
        return this.minimum;
    }
}
