/**
 * Misuratore di oggetti. Le classi che implementano questa interfaccia
 * utilizzeranno diverse strategie per la misurazione degli oggetti.
 */
public interface Measurer {
    /**
     * Misura un oggetto
     *
     * @param anObject l'oggetto da misurare
     * @return la misura dell'oggetto
     */
    double measure(Object anObject);
}
