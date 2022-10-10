import java.util.Random;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 *
 * @author Luca Tesei
 */
public class Burglar {
    private CombinationLock aCombinationLock;
    private int attempts = -1;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     *
     * @param aCombinationLock
     * @throw NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        if (aCombinationLock == null) {
            throw new NullPointerException("La cassaforte passata è nulla");
        }
        this.aCombinationLock = aCombinationLock;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     *
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    aCombinationLock.setPosition((char) (i + 'A'));
                    aCombinationLock.setPosition((char) (j + 'A'));
                    aCombinationLock.setPosition((char) (k + 'A'));
                    aCombinationLock.open();
                    attempts++;
                    if (aCombinationLock.isOpen()) {
                        return "" + (char) (i + 'A') + (char) (j + 'A') + (char) (k + 'A');
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     *
     * @return il numero di tentativi che ci sono voluti per trovare la
     * combinazione, oppure -1 se la cassaforte non è stata ancora
     * forzata.
     */
    public long getAttempts() {
        // risultato 0 sta per 1 tentativo
        return attempts;
    }
}
