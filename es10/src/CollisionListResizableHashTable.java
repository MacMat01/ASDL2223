/**
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Realizza un insieme tramite una tabella hash con indirizzamento primario (la
 * funzione di hash primario deve essere passata come parametro nel costruttore
 * e deve implementare l'interface PrimaryHashFunction) e liste di collisione.
 * <p>
 * La tabella, poiché implementa l'interfaccia Set<E> non accetta elementi
 * duplicati (individuati tramite il metodo equals() che si assume sia
 * opportunamente ridefinito nella classe E) e non accetta elementi null.
 * <p>
 * La tabella ha una dimensione iniziale di default (16) e un fattore di
 * caricamento di defaut (0.75). Quando il fattore di bilanciamento effettivo
 * eccede quello di default la tabella viene raddoppiata e viene fatto un
 * riposizionamento di tutti gli elementi.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class CollisionListResizableHashTable<E> implements Set<E> {

    /*
     * La capacità iniziale. E' una potenza di due e quindi la capacità sarà
     * sempre una potenza di due, in quanto ogni resize raddoppia la tabella.
     */
    private static final int INITIAL_CAPACITY = 16;

    /*
     * Fattore di bilanciamento di default. Tipico valore.
     */
    private static final double LOAD_FACTOR = 0.75;

    /*
     * Numero di elementi effettivamente presenti nella hash table in questo
     * momento. ATTENZIONE: questo valore è diverso dalla capacity, che è la
     * lunghezza attuale dell'array di Object che rappresenta la tabella.
     */
    private int size;

    /*
     * L'idea è che l'elemento in posizione i della tabella hash è un bucket che
     * contiene null oppure il puntatore al primo nodo di una lista concatenata
     * di elementi. Si può riprendere e adattare il proprio codice della
     * Esercitazione 6 che realizzava una lista concatenata di elementi
     * generici. La classe interna Node<E> è ripresa proprio da lì.
     *
     * ATTENZIONE: la tabella hash vera e propria può essere solo un generico
     * array di Object e non di Node<E> per una impossibilità del compilatore di
     * accettare di creare array a runtime con un tipo generics. Ciò infatti
     * comporterebbe dei problemi nel sistema di check dei tipi Java che, a
     * run-time, potrebbe eseguire degli assegnamenti in violazione del tipo
     * effettivo della variabile. Quindi usiamo un array di Object che
     * riempiremo sempre con null o con puntatori a oggetti di tipo Node<E>.
     *
     * Per inserire un elemento nella tabella possiamo usare il polimorfismo di
     * Object:
     *
     * this.table[i] = new Node<E>(item, next);
     *
     * ma quando dobbiamo prendere un elemento dalla tabella saremo costretti a
     * fare un cast esplicito:
     *
     * Node<E> myNode = (Node<E>) this.table[i];
     *
     * Ci sarà dato un warning di cast non controllato, ma possiamo eliminarlo
     * con un tag @SuppressWarning,
     */
    private Object[] table;

    /*
     * Funzione di hash primaria usata da questa hash table. Va inizializzata nel
     * costruttore all'atto di creazione dell'oggetto.
     */
    private final PrimaryHashFunction phf;

    /*
     * Contatore del numero di modifiche. Serve per rendere l'iteratore
     * fail-fast.
     */
    private int modCount;

    // I due metodi seguenti sono di comodo per gestire la capacity e la soglia
    // oltre la quale bisogna fare il resize.

    /* Numero di elementi della tabella corrente */
    private int getCurrentCapacity() {
        return this.table.length;
    }

    /*
     * Valore corrente soglia oltre la quale si deve fare la resize,
     * getCurrentCapacity * LOAD_FACTOR
     */
    private int getCurrentThreshold() {
        return (int) (getCurrentCapacity() * LOAD_FACTOR);
    }

    /**
     * Costruisce una Hash Table con capacità iniziale di default e fattore di
     * caricamento di default.
     */
    public CollisionListResizableHashTable(PrimaryHashFunction phf) {
        this.phf = phf;
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        // TODO implementare
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui cercare
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
         *
         * In questa posizione, se non vuota, si deve cercare l'elemento o
         * utilizzando il metodo equals() su tutti gli elementi della lista
         * concatenata lì presente
         *
         */
        if (o == null) {
            return false;
        }
        /*
         * Calcolo l'indice del bucket in cui cercare l'oggetto o
         */
        int pos = this.phf.hash(o.hashCode(), this.getCurrentCapacity());
        /*
         * Prendo il primo nodo della lista concatenata in posizione pos
         */
        Node<E> n = (Node<E>) this.table[pos];
        /*
         * Scorro la lista concatenata fino a quando non trovo l'elemento o o
         * fino a quando non arrivo alla fine della lista
         */
        while (n != null) {
            if (n.item.equals(o)) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(E e) {
        // TODO implementare
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui inserire
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
         *
         * In questa posizione, se non vuota, si deve inserire l'elemento o
         * nella lista concatenata lì presente. Se vuota, si crea la lista
         * concatenata e si inserisce l'elemento, che sarà l'unico.
         *
         */
        // ATTENZIONE, si inserisca prima il nuovo elemento e poi si controlli
        // se bisogna fare resize(), cioè se this.size >
        // this.getCurrentThreshold()

        if (e == null || this.contains(e)) {
            return false;
        }
        /*
         * Calcolo l'indice del bucket in cui inserire l'oggetto e
         */
        int pos = this.phf.hash(e.hashCode(), this.getCurrentCapacity());
        /*
         * Prendo il primo nodo della lista concatenata in posizione pos
         */
        Node<E> n = (Node<E>) this.table[pos];
        if (n == null) {
            /*
             * Creo un nuovo nodo con l'elemento da inserire
             */
            this.table[pos] = new Node<E>(e, null);
        } else {
            /*
             * Scorro la lista concatenata fino a quando non arrivo alla fine
             */
            while (n.next != null) {
                n = n.next;
            }
            /*
             * Aggiungo il nuovo nodo alla fine della lista
             */
            n.next = new Node<E>(e, null);
        }
        /*
         * Incremento la dimensione della tabella
         */
        this.size++;
        this.modCount++;
        /*
         * Se la tabella è piena, devo fare il resize
         */
        if (this.size > this.getCurrentThreshold()) {
            this.resize();
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    /*
     * Raddoppia la tabella corrente e riposiziona tutti gli elementi. Da
     * chiamare quando this.size diventa maggiore di getCurrentThreshold()
     */ private void resize() {
        // TODO implementare
        /*
         * Creo una nuova tabella con capacità uguale a getCurrentCapacity() * 2
         */
        Object[] newTable = new Object[this.getCurrentCapacity() * 2];
        /*
         * Scorro la tabella corrente e ricalcolo l'indice di ogni elemento
         * inserendolo nella nuova tabella
         */
        for (int i = 0; i < this.getCurrentCapacity(); i++) {
            Node<E> n = (Node<E>) this.table[i];
            while (n != null) {
                int pos = this.phf.hash(n.item.hashCode(), newTable.length);
                Node<E> newN = (Node<E>) newTable[pos];
                if (newN == null) {
                    newTable[pos] = new Node<E>(n.item, null);
                } else {
                    while (newN.next != null) {
                        newN = newN.next;
                    }
                    newN.next = new Node<E>(n.item, null);
                }
                n = n.next;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        // TODO implementare
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui cercare
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
         *
         * In questa posizione, se non vuota, si deve cercare l'elemento o
         * utilizzando il metodo equals() su tutti gli elementi della lista
         * concatenata lì presente. Se presente, l'elemento deve essere
         * eliminato dalla lista concatenata
         *
         */
        // ATTENZIONE: la rimozione, in questa implementazione, **non** comporta
        // mai una resize "al ribasso", cioè un dimezzamento della tabella se si
        // scende sotto il fattore di bilanciamento desiderato.
        if (o == null) {
            return false;
        }
        /*
         * Calcolo l'indice del bucket in cui cercare l'oggetto o
         */
        int pos = this.phf.hash(o.hashCode(), this.getCurrentCapacity());
        /*
         * Prendo il primo nodo della lista concatenata in posizione pos
         */
        Node<E> n = (Node<E>) this.table[pos];
        if (n == null) {
            return false;
        }
        /*
         * Se l'elemento da rimuovere è il primo della lista, lo rimuovo
         */
        if (n.item.equals(o)) {
            this.table[pos] = n.next;
            this.size--;
            this.modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO implementare
        // utilizzare un iteratore della collection e chiamare il metodo
        // contains
        if (c == null) {
            return false;
        }
        /*
         * Scorro la collection c e controllo se ogni elemento è presente nella
         * tabella
         */
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO implementare
        // utilizzare un iteratore della collection e chiamare il metodo add
        if (c == null) {
            return false;
        }
        /*
         * Scorro la collection c e aggiungo ogni elemento alla tabella
         */
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO implementare
        // utilizzare un iteratore della collection e chiamare il metodo remove
        if (c == null) {
            return false;
        }
        /*
         * Scorro la collection c e rimuovo ogni elemento dalla tabella
         */
        for (Object o : c) {
            this.remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        // Ritorno alla situazione iniziale
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    /*
     * Classe per i nodi della lista concatenata. Lo specificatore è protected
     * solo per permettere i test JUnit.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

        /*
         * Crea un nodo "singolo" equivalente a una lista con un solo elemento.
         */
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /*
     * Classe che realizza un iteratore per questa hash table. L'ordine in cui
     * vengono restituiti gli oggetti presenti non è rilevante, ma ogni oggetto
     * presente deve essere restituito dall'iteratore una e una sola volta.
     * L'iteratore deve essere fail-fast, cioè deve lanciare una eccezione
     * ConcurrentModificationException se a una chiamata di next() si "accorge"
     * che la tabella è stata cambiata rispetto a quando l'iteratore è stato
     * creato.
     */
    private class Itr implements Iterator<E> {

        // TODO inserire le variabili che servono

        /*
         * Indice del bucket corrente
         */
        private int pos;

        /*
         * Nodo corrente
         */
        private Node<E> n;

        /*
         * Numero di modifiche alla tabella
         */
        private int numeroModificheAtteso;

        @SuppressWarnings("unchecked")
        private Itr() {
            // TODO implementare il resto
            this.numeroModificheAtteso = modCount;
            this.pos = 0;
            this.n = (Node<E>) table[pos];
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean hasNext() {
            // TODO implementare
            /*
             * Se il nodo corrente è null, allora scorro la tabella fino a
             * trovare un nodo non null
             */
            if (this.n == null) {
                for (int i = this.pos + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        this.pos = i;
                        this.n = (Node<E>) table[i];
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            // TODO implementare
            /*
             * Se il nodo corrente è null, allora scorro la tabella fino a
             * trovare un nodo non null
             */
            if (this.n == null) {
                for (int i = this.pos + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        this.pos = i;
                        this.n = (Node<E>) table[i];
                        return this.n.item;
                    }
                }
            }
            return null;
        }

    }

    /*
     * Only for JUnit testing purposes.
     */
    protected Object[] getTable() {
        return this.table;
    }

    /*
     * Only for JUnit testing purposes.
     */
    protected PrimaryHashFunction getPhf() {
        return this.phf;
    }

}
