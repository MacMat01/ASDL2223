/**
 *
 */

/**
 * Abstract Data Type Cons List, ovvero liste immutabili a contenuto generico
 * costruite a partire dalla lista vuota con inserimento in testa (operazione
 * cons). L'implementazione di default è data da due classi: EmptyList che
 * implemente la lista vuota e ConsList che implementa l'operazione cons con
 * campi immutabili. In questa interface sono definiti vari metodi ricorsivi
 * generici sulle ADTConsList.
 * <p>
 * Il nome "cons" deriva dal linguaggio LISP in cui questa è l'operazione per
 * creare una lista a partire da due campi: car (corrisponde al nostro first) e
 * cdr (corrisponde al nostro rest). A differenza dell'approccio funzionale
 * classico, questa classe realizza il tipo di dato astratto nel paradigma ad
 * oggetti.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public interface ADTConsList<E> {

    /**
     * Lista vuota.
     */
    @SuppressWarnings("rawtypes")
    public static final ADTConsList EMPTY_LIST = new EmptyList();

    /**
     * Determina se questa lista è vuota.
     *
     * @return true se questa lista è vuota.
     */
    public boolean isEmpty();

    /**
     * Restituisce l'elemento in testa alla lista.
     *
     * @return l'elemento in testa
     * @throws IllegalStateException se il metodo viene chiamato su una
     *                               lista vuota.
     */
    public E first();

    /**
     * Restituisce la "coda" di questa lista, cioè questa lista in cui è stato
     * tolto l'elemento di testa.
     *
     * @return la "coda" di questa lista, potrebbe essere la lista vuota
     * @throws IllegalStateException se il metodo viene chiamato su una
     *                               lista vuota
     */
    public ADTConsList<E> rest();

    /**
     * Aggiunge un elemento in testa a questa lista.
     *
     * @param first l'elemento da aggiungere in testa
     * @return una lista con l'elemento {@code head} in testa e questa lista
     * come "coda"
     */
    public ADTConsList<E> cons(E first);

    /**
     * Restituisce una stringa contenente il toString di tutti gli elementi di
     * questa lista separati da uno spazio.
     *
     * @return una stringa con tutti gli elementi di questa lista
     */
    default String print() {
        // caso base
        if (this.isEmpty()) return "";
        // caso ricorsivo
        return this.first().toString() + " " + this.rest().print();
    }

    /**
     * Cerca se un elemento è in questa lista.
     *
     * @param e l'elemento da cercare
     * @return true se questa lista contiene {@code element}
     */
    default boolean find(E e) {
        // caso base
        if (this.isEmpty()) return false;
        // caso ricorsivo
        if (this.first().equals(e)) return true;
        else return this.rest().find(e);
    }

    /**
     * Cancella la prima occorrenza di un elemento in questa lista, se presente.
     *
     * @param element l'elemento da cancellare
     * @return una lista uguale a questa in cui la prima occorrenza di
     * {@code element}, se presente, è stata cancellata.
     */
    default ADTConsList<E> removeFirst(E element) {
        // caso base
        if (this.isEmpty()) return this;
        // caso ricorsivo
        if (this.first().equals(element)) return this.rest();
        else return this.rest().removeFirst(element).cons(this.first());
    }

    /**
     * Cancella tutti gli elementi uguali a un elemento dato in questa lista.
     *
     * @param element l'elemento da cancellare
     * @return una lista uguale a questa in cui tutte le occorrenze di
     * {@code element} sono state cancellate.
     */
    default ADTConsList<E> removeAll(E element) {
        // caso base
        if (this.isEmpty()) {
            return this;
        }
        // caso ricorsivo
        if (this.first().equals(element)) {
            return this.rest().removeAll(element);
        } else return this.rest().removeAll(element).cons(this.first());
    }

    /**
     * Aggiorna il primo elemento uguale a un elemento dato con un nuovo
     * elemento in questa lista.
     *
     * @param element    l'elemento da aggiornare
     * @param newElement il nuovo elemento che deve sostituire
     *                   {@code element}
     * @return una lista uguale a questa in cui la prima occorrenza
     * dell'elemento {@code element} è stata sostituita con
     * {@code newElement}
     */
    default ADTConsList<E> updateFirst(E element, E newElement) {
        // caso vuoto
        if (this.isEmpty()) {
            return this;
        }
        // caso ricorsivo
        if (this.first().equals(element)) {
            return this.rest().cons(newElement);
        } else return this.rest().updateFirst(element, newElement).cons(this.first());
    }

    /**
     * Aggiorna tutti gli elementi uguali a un elemento dato con un nuovo
     * elemento in questa lista.
     *
     * @param element    l'elemento da aggiornare
     * @param newElement il nuovo elemento che deve sostituire
     *                   {@code element}
     * @return una lista uguale a questa in cui tutte le occorrenze
     * dell'elemento {@code element} sono state sostituite con
     * {@code newElement}
     */
    default ADTConsList<E> updateAll(E element, E newElement) {
        // caso base
        if (this.isEmpty()) {
            return this;
        }
        // caso ricorsivo
        if (this.first().equals(element)) {
            return this.rest().updateAll(element, newElement).cons(newElement);
        } else return this.rest().updateAll(element, newElement).cons(this.first());
    }

    /**
     * Attacca una lista data in fondo a questa lista.
     *
     * @param list la lista da attaccare in fondo
     * @return una lista che contiene gli elementi di questa lista seguita dagli
     * elementi di {@code list}.
     */
    default ADTConsList<E> append(ADTConsList<E> list) {
        // caso base
        if (this.isEmpty()) {
            return list;
        }
        // caso ricorsivo
        return this.rest().append(list).cons(this.first());
    }


    /**
     * Genera una lista che ha gli stessi elementi di questa lista, ma
     * nell'ordine inverso.
     *
     * @return una lista uguale a questa, ma con tutti gli elementi in ordine
     * inverso.
     */
    @SuppressWarnings("unchecked")
    default ADTConsList<E> reverse() {
        // TODO implementare ricorsivamente
        // caso base
        if (this.isEmpty()) {
            return this;
        }
        // caso ricorsivo
        return this.rest().reverse().append(EMPTY_LIST.cons(this.first()));
    }
}
