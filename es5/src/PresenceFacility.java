/**
 * 
 */
/**
 * Una Presence Facility è una facility che può essere presente oppure no. Ad
 * esempio la presenza di un proiettore HDMI oppure la presenza dell'aria
 * condizionata.
 * 
 * @author Luca Tesei
 *
 */
public class PresenceFacility extends Facility {

    /**
     * Costruisce una presence facility.
     * 
     * @param codice
     * @param descrizione
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public PresenceFacility(String codice, String descrizione) {
        super(codice, descrizione);
    }

    /*
     * Una Presence Facility soddisfa una facility solo se la facility passata è
     * una Presence Facility ed ha lo stesso codice.
     * 
     */
    @Override
    public boolean satisfies(Facility o) {
        if (o == null)
            throw new NullPointerException(
                    "Tentativo di controllare la soddisfacibilità di una facility nulla");
        if (o instanceof PresenceFacility) {
            // Controllo se la facility ha lo stesso codice
            PresenceFacility oo = (PresenceFacility) o;
            return this.getCodice().equals(oo.getCodice());
        } else
            // la facility non è una presence facility quindi non può essere
            // soddisfatta
            return false;
    }

}
