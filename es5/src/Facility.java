/**
 * Una facility generica è una caratteristica o delle dotazioni che una certa
 * aula può avere. La classe va specificata ulteriormente per definire i diversi
 * tipi di facilities.
 * 
 * @author Luca Tesei
 *
 */
public abstract class Facility {

    private final String codice;

    private final String descrizione;

    /**
     * Costruisce una certa facility generica.
     * 
     * @param codice
     *                        identifica la facility univocamente
     * @param descrizione
     *                        descrizione della facility
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public Facility(String codice, String descrizione) {
        if (codice == null || descrizione == null)
            throw new NullPointerException(
                    "Tentativo di costruire una facility con almeno una informazione nulla");
        this.codice = codice;
        this.descrizione = descrizione;
    }

    /**
     * @return the codice
     */
    public String getCodice() {
        return codice;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /*
     * Poiché l'uguaglianza è basata sul codice, anche l'hashcode deve essere
     * basato sul codice
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codice == null) ? 0 : codice.hashCode());
        return result;
    }

    /*
     * L'uguaglianza di due facilities è basata unicamente sul codice
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Facility))
            return false;
        Facility other = (Facility) obj;
        if (codice == null) {
            if (other.codice != null)
                return false;
        } else if (!codice.equals(other.codice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Facility [codice=" + codice + ", descrizione=" + descrizione
                + "]";
    }

    /**
     * Determina se questa facility soddisfa un'altra facility data. Ad esempio
     * se questa facility indica che è presente un proiettore HDMI, allora essa
     * soddisfa la facility "presenza di un proiettore HDMI". Un altro esempio:
     * se questa facility indica un numero di posti a sedere pari a 30, allora
     * essa soddisfa ogni altra facility che indica che ci sono un numero di
     * posti minore o uguale a 30. Il metodo dipende dal tipo di facility, per
     * questo è astratto e va definito nelle varie sottoclassi.
     * 
     * @param o
     *              l'altra facility con cui determinare la compatibilità
     * @return true se questa facility soddisfa la facility passata, false
     *         altrimenti
     * @throws NullPointerException
     *                                  se la facility passata è nulla.
     */
    public abstract boolean satisfies(Facility o);

}
