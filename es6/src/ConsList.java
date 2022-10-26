/**
 * Classe che implementa {@code ADTConsList<E>} con due campi immutabili.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *            il tipo degli elementi della lista.
 */
public final class ConsList<E> implements ADTConsList<E> {
    
    private final E first;

    private final ADTConsList<E> rest;

    /**
     * Costruisce una lista a partire dalla lista vuota inserendo in testa un
     * elemento.
     * 
     * @param first
     *                 l'elemento da inserire nella lista.
     */
    @SuppressWarnings("unchecked")
    public ConsList(E first) {
        this.first = first;
        this.rest = ADTConsList.EMPTY_LIST;
    }

    /**
     * Costruisce una lista a partire da una {@code ADTConsList<E>} e inserendo in testa un
     * nuovo elemento.
     * 
     * @param first l'elemento in testa
     * @param rest la lista di partenza
     */
    public ConsList(E first, ADTConsList<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E first() {
        return this.first;
    }

    @Override
    public ADTConsList<E> rest() {
        return this.rest;
    }

    @Override
    public ADTConsList<E> cons(E first) {
        return new ConsList<E>(first, this);
    }

}
