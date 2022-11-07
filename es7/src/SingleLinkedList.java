import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Lista concatenata singola che non accetta valori null, ma permette elementi
 * duplicati. Le seguenti operazioni non sono supportate:
 *
 * <ul>
 * <li>ListIterator<E> listIterator()</li>
 * <li>ListIterator<E> listIterator(int index)</li>
 * <li>List<E> subList(int fromIndex, int toIndex)</li>
 * <li>T[] toArray(T[] a)</li>
 * <li>boolean containsAll(Collection<?> c)</li>
 * <li>addAll(Collection<? extends E> c)</li>
 * <li>boolean addAll(int index, Collection<? extends E> c)</li>
 * <li>boolean removeAll(Collection<?> c)</li>
 * <li>boolean retainAll(Collection<?> c)</li>
 * </ul>
 * <p>
 * L'iteratore restituito dal metodo {@code Iterator<E> iterator()} è fail-fast,
 * cioè se c'è una modifica strutturale alla lista durante l'uso dell'iteratore
 * allora lancia una {@code ConcurrentMopdificationException} appena possibile,
 * cioè alla prima chiamata del metodo {@code next()}.
 *
 * @param <E> il tipo degli elementi della lista
 * @author Luca Tesei
 */
public class SingleLinkedList<E> implements List<E> {

    private int size;

    private Node<E> head;

    private Node<E> tail;

    private int numeroModifiche;

    /**
     * Crea una lista vuota.
     */
    public SingleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.numeroModifiche = 0;
    }

    /*
     * Classe per i nodi della lista concatenata. E' dichiarata static perché
     * gli oggetti della classe Node<E> non hanno bisogno di accedere ai campi
     * della classe principale per funzionare.
     */
    private static class Node<E> {
        private E item;

        private Node<E> next;

        /*
         * Crea un nodo "singolo" equivalente a una lista con un solo elemento.
         */
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /*
     * Classe che realizza un iteratore per SingleLinkedList.
     * L'iteratore deve essere fail-fast, cioè deve lanciare una eccezione
     * ConcurrentModificationException se a una chiamata di next() si "accorge"
     * che la lista è stata cambiata rispetto a quando l'iteratore è stato
     * creato.
     *
     * La classe è non-static perché l'oggetto iteratore, per funzionare
     * correttamente, ha bisogno di accedere ai campi dell'oggetto della classe
     * principale presso cui è stato creato.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;

        private int numeroModificheAtteso;

        private Itr() {
            // All'inizio non è stato fatto nessun next
            this.lastReturned = null;
            this.numeroModificheAtteso = SingleLinkedList.this.numeroModifiche;
        }

        @Override
        public boolean hasNext() {
            if (this.lastReturned == null)
                // sono all'inizio dell'iterazione
                return SingleLinkedList.this.head != null;
            else
                // almeno un next è stato fatto
                return lastReturned.next != null;
        }

        @Override
        public E next() {
            // controllo concorrenza
            if (this.numeroModificheAtteso != SingleLinkedList.this.numeroModifiche) {
                throw new ConcurrentModificationException("Lista modificata durante l'iterazione");
            }
            // controllo hasNext()
            if (!hasNext()) throw new NoSuchElementException("Richiesta di next quando hasNext è falso");
            // c'è sicuramente un elemento di cui fare next
            // aggiorno lastReturned e restituisco l'elemento next
            if (this.lastReturned == null) {
                // sono all’inizio e la lista non è vuota
                this.lastReturned = SingleLinkedList.this.head;
                return SingleLinkedList.this.head.item;
            } else {
                // non sono all’inizio, ma c’è ancora qualcuno
                lastReturned = lastReturned.next;
                return lastReturned.item;
            }
        }
    }

    /*
     * Una lista concatenata è uguale a un'altra lista se questa è una lista
     * concatenata e contiene gli stessi elementi nello stesso ordine.
     *
     * Si noti che si poteva anche ridefinire il metodo equals in modo da
     * accettare qualsiasi oggetto che implementi List<E> senza richiedere che
     * sia un oggetto di questa classe:
     *
     * obj instanceof List
     *
     * In quel caso si può fare il cast a List<?>:
     *
     * List<?> other = (List<?>) obj;
     *
     * e usando l'iteratore si possono tranquillamente controllare tutti gli
     * elementi (come è stato fatto anche qui):
     *
     * Iterator<E> thisIterator = this.iterator();
     *
     * Iterator<?> otherIterator = other.iterator();
     *
     * ...
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof SingleLinkedList)) return false;
        SingleLinkedList<?> other = (SingleLinkedList<?>) obj;
        // Controllo se entrambe liste vuote
        if (head == null) {
            if (other.head != null) return false;
            else return true;
        }
        // Liste non vuote, scorro gli elementi di entrambe
        Iterator<E> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            E o1 = thisIterator.next();
            // uso il polimorfismo di Object perché non conosco il tipo ?
            Object o2 = otherIterator.next();
            // il metodo equals che si usa è quello della classe E
            if (!o1.equals(o2)) return false;
        }
        // Controllo che entrambe le liste siano terminate
        return !(thisIterator.hasNext() || otherIterator.hasNext());
    }

    /*
     * L'hashcode è calcolato usando gli hashcode di tutti gli elementi della
     * lista.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        // implicitamente, col for-each, uso l'iterator di questa classe
        for (E e : this)
            hashCode = 31 * hashCode + e.hashCode();
        return hashCode;
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
    public boolean contains(Object o) {
        // controllo se l'oggetto passato sia nullo
        if (o == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        // scorro tutta la lista alla ricerca dell'oggetto desiderato
        for (E e : this) {
            if (e.equals(o)) {
                return true;
            }
        }
        // se non è stato trovato ritorno false
        return false;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        // Se la lista è vuota creo un nuovo nodo e lo inserisco in testa
        if (head == null) {
            head = new Node<>(e, null);
            tail = head;
            numeroModifiche++;
        }
        // altrimenti aggiungo l'elemento in coda
        else {
            tail.next = new Node<>(e, null);
            tail = tail.next;
            numeroModifiche++;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        Node<E> prev = null;
        Node<E> current;
        E element;
        Itr iterator = new Itr();
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.equals(o)) {
                current = iterator.lastReturned;
                if (prev == null) {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = iterator.lastReturned;
        }
        return false;
    }

    @Override
    public void clear() {
        // cancellando la testa vengono cancellati dal garbage collector
        // anche tutti gli altri oggetti perché non avranno più un riferimento
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        int otherIndex = 0;
        for (E element : this) {
            if (otherIndex == index) {
                return element;
            } else {
                otherIndex++;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException("L'elemento passato è nullo");
        }
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        /*
         * scorro la lista fino all'elemento desiderato e lo sostituisco con
         * quello passato come parametro
         */
        int otherIndex = 0;
        Node<E> prev = null;
        Node<E> current;
        E otherElement;
        Itr iterator = new Itr();
        while (iterator.hasNext()) {
            otherElement = iterator.next();
            if (otherIndex == index) {
                current = iterator.lastReturned;
                current = new Node<E>(element, current.next);
                if (prev == null) {
                    head = current;
                    tail = head;
                } else {
                    prev.next = current;
                }
                return otherElement;
            } else {
                prev = iterator.lastReturned;
                otherIndex++;
            }
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException("L'elemento passato è nullo");
        }
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.size) {
            this.add(element);
            return;
        } else {
            int otherIndex = 0;
            Node<E> prev = null;
            Node<E> current;
            E otherElement;
            Itr iterator = new Itr();
            while (iterator.hasNext()) {
                otherElement = iterator.next();
                if (otherIndex == index) {
                    current = iterator.lastReturned;
                    current = new Node<E>(element, current);
                    if (prev == null) {
                        head = current;
                        tail = head;
                    } else {
                        prev.next = current;
                    }
                    size++;
                    return;
                } else {
                    prev = iterator.lastReturned;
                    otherIndex++;
                }
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E remove(int index) {
        // index deve essere compreso tra 0 e size-1
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int otherIndex = 0;
        Node<E> prev = null;
        Node<E> current;
        E otherElement;
        Itr iterator = new Itr();
        while (iterator.hasNext()) {
            otherElement = iterator.next();
            if (otherIndex == index) {
                current = iterator.lastReturned;
                if (prev == null) {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    prev.next = current.next;
                }
                size--;
                return otherElement;
            } else {
                prev = iterator.lastReturned;
                otherIndex++;
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        /*
         * scorro tutta la lista alla ricerca dell'oggetto desiderato
         */
        int index = 0;
        for (E element : this) {
            if (element.equals(o)) {
                return index;
            } else {
                index++;
            }
        }
        // se non è stato trovato ritorno -1
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        /*
         * scorro tutta la lista alla ricerca dell'oggetto desiderato
         */
        int index = 0;
        int lastIndex = -1;
        for (E element : this) {
            if (element.equals(o)) {
                lastIndex = index;
            }
            index++;
        }
        return lastIndex;
    }

    @Override
    public Object[] toArray() {
        // creo un array di Object di dimensione size
        Object[] array = new Object[this.size];
        int index = 0;
        // scorro tutta la lista e aggiungo gli elementi all'array
        for (E element : this) {
            array[index] = element;
            index++;
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }
}
