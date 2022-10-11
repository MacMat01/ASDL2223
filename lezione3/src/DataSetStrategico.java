/**
 * Un oggetto DataSetStrategico acquisisce un insieme di oggetti ed è in grado
 * di restituire il minimo, il massimo e la media delle misurazioni degli
 * oggetti che sono stati aggiunti fino a quel momento usando un oggetto
 * misuratore definito nell'interfaccia strategica <code>Measurer</code>.
 */
public class DataSetStrategico {

    /* Oggetto misuratore strategico */
    private Measurer measurer;

    /* Accumula i valori misurati degli oggetti inseriti */
    private double sum;

    /* Conta il numero degli oggetti inseriti */
    private int count;

    /* Oggetto con valore massimo attuale */
    private Object maximum;

    /* Oggetto con valore minimo attuale */
    private Object minimum;

    /**
     * Inizializza le variabili istanza con valori relativi ad un insieme di
     * dati vuoto e riceve un misuratore di oggetti.
     *
     * @param aMeasurer un misuratore che implementa una certa strategia
     */
    public DataSetStrategico(Measurer aMeasurer) {
        this.measurer = aMeasurer;
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
    public void add(Object x) {
        this.sum += this.measurer.measure(x);
        if (this.count == 0 || this.measurer.measure(this.maximum) < this.measurer.measure(x)) this.maximum = x;
        if (this.count == 0 || this.measurer.measure(this.minimum) > this.measurer.measure(x)) this.minimum = x;
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
    public Object getMaximum() {
        if (this.count == 0) throw new IllegalStateException("Dataset vuoto");
        return this.maximum;
    }

    /**
     * Restituisce l'oggetto la cui misura è minima rispetto a tutti gli oggetti
     * inseriti.
     *
     * @return l'oggetto con misura minima
     * @throws IllegalStateException se il dataset è vuoto
     */
    public Object getMinimum() {
        if (this.count == 0) throw new IllegalStateException("Dataset vuoto");
        return this.minimum;
    }

}
