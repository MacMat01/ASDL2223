/**
 * Un oggetto DataSet acquisisce un insieme di dati double ed è in grado di
 * restituire il minimo, il massimo e la media dei valori che sono stati
 * aggiunti fino a quel momento.
 *
 * @author Luca Tesei
 */
public class DataSet {

    /* Conta il numero di valori inseriti */
    private int count;

    /* Valore massimo inserito */
    private double maximum;

    /* Valore minimo inserito */
    private double minimum;

    /* Accumula la somma dei valori inseriti */
    private double sum;

    /**
     * Costruisce un dataset vuoto.
     */
    public DataSet() {
        this.count = 0;
    }

    /**
     * Aggiunge un valore al dataset.
     *
     * @param value un double da aggiungere
     */
    public void add(double value) {
        if (this.count == 0) {
            this.minimum = value;
            this.maximum = value;
            this.sum = value;
            this.count++;
        } else {
            this.sum += value;
            this.count++;
            if (value > this.maximum) this.maximum = value;
            if (value < minimum) this.minimum = value;
        }
    }

    /**
     * @return il massimo valore inserito finora, Double.NaN se non è stato
     * immesso nessun valore
     */
    public double getMaximum() {
        if (this.count == 0) return Double.NaN;
        else return this.maximum;
    }

    /**
     * @return il minimo valore inserito finora, Double.NaN se non è stato
     * immesso nessun valore
     */
    public double getMinimum() {
        if (this.count == 0) return Double.NaN;
        else return this.minimum;
    }

    /**
     * @return la media dei valori inseriti finora, Double.NaN se non è stato
     * immesso nessun valore
     */
    public double getAverage() {
        if (this.count == 0) return Double.NaN;
        else return this.sum / this.count;
    }
}
