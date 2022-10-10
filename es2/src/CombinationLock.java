/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 *
 * @author Luca Tesei
 */
public class CombinationLock {
    private String aCombination = "";   // creo una stringa che rappresenta la combinazione
    private StringBuffer insertedCombination = new StringBuffer(26);    // creo un buffer per rappresentare le combinazioni inserite
    private boolean unlocked = true;    // creo una variabile booleana per indicare se la cassaforte è aperta o meno

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     *
     * @param aCombination la combinazione che deve essere una stringa di 3
     *                     lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
        char[] combinationChars = aCombination.toCharArray();   // creo un array di char e ci inserisco la combinazione convertita
        if (aCombination == null) {
            throw new NullPointerException("La combinazione fornita è nulla");
        }
        for (int i = 0; i < 3; i++) {
            if (aCombination.length() != 3 || !Character.isLetter(combinationChars[i]) || !Character.isUpperCase(combinationChars[i])) {
                throw new IllegalArgumentException("La combinazione fornita non è una stringa di 3 lettere maiuscole dell'alfabeto inglese");
            }
        }
        this.aCombination = aCombination;
    }

    /**
     * Imposta la manopola su una certaposizione.
     *
     * @param aPosition un carattere lettera maiuscola su cui viene
     *                  impostata la manopola
     * @throws IllegalArgumentException se il carattere fornito non è una
     *                                  lettera maiuscola dell'alfabeto
     *                                  inglese
     */
    public void setPosition(char aPosition) {
        if (!Character.isUpperCase(aPosition)) {
            throw new IllegalArgumentException("Il carattere fornito non è una lettera maiuscola dell'alfabeto inglese");
        }
        insertedCombination.append(aPosition);  // imposto la manopola nella posizione aPosition
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {
        if (!unlocked) {
            if (insertedCombination.toString().equals(aCombination)) {
                unlocked = true;
            } else {
                insertedCombination.delete(0, 3);
            }
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     *
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return unlocked;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        unlocked = false;
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     *
     * @param aCombination la nuova combinazione che deve essere una stringa
     *                     di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        char[] combinationChars = aCombination.toCharArray();   // creo un array di char e ci inserisco la combinazione convertita
        if (aCombination == null) {
            throw new NullPointerException("La combinazione fornita è nulla");
        }
        for (int i = 0; i < 3; i++) {
            if (aCombination.length() != 3 || !Character.isLetter(combinationChars[i]) || !Character.isUpperCase(combinationChars[i])) {
                throw new IllegalArgumentException("La combinazione fornita non è una stringa di 3 lettere maiuscole dell'alfabeto inglese");
            }
        }
        if (unlocked) {
            lock();
            if (!aCombination.equals(this.aCombination)) this.aCombination = aCombination;
        }
    }
}