/**
 *
 */
package it.unicam.cs.asdl2223.mp2;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the Java SE Double-ended Queue (Deque) interface
 * (<code>java.util.Deque</code>) based on a double linked list. This deque does
 * not have capacity restrictions, i.e., it is always possible to insert new
 * elements and the number of elements is unbound. Duplicated elements are
 * permitted while <code>null</code> elements are not permitted. Being
 * <code>Deque</code> a sub-interface of
 * <code>Queue<code>, this class can be used also as an implementaion of a <code>Queue</code>
 * and of a <code>Stack</code>.
 * <p>
 * The following operations are not supported:
 * <ul>
 * <li><code>public <T> T[] toArray(T[] a)</code></li>
 * <li><code>public boolean removeAll(Collection<?> c)</code></li>
 * <li><code>public boolean retainAll(Collection<?> c)</code></li>
 * <li><code>public boolean removeFirstOccurrence(Object o)</code></li>
 * <li><code>public boolean removeLastOccurrence(Object o)</code></li>
 * </ul>
 *
 * @author Template: Luca Tesei, Implementation: Matteo Machella - matteo.machella@studenti.unicam.it
 */
public class ASDL2223Deque<E> implements Deque<E> {

    /*
     * Current number of elements in this deque
     */
    private int size;

    /*
     * Pointer to the first element of the double-linked list used to implement
     * this deque
     */
    private Node<E> first;

    /*
     * Pointer to the last element of the double-linked list used to implement
     * this deque
     */
    private Node<E> last;

    /*
     * Current number of modifications to this deque
     */
    private int modCount;

    /**
     * Constructs an empty deque.
     */
    public ASDL2223Deque() {
        size = 0;
        first = null;
        last = null;
        modCount = 0;
    }

    @Override
    public boolean isEmpty() {
        // return true if the deque is empty, false otherwise
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E e : this) {
            array[i] = e;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // return true if all the elements of c are in this deque, else return false
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // return true if this deque is modified, else return false
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            this.addLast(e);
            modCount++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
        modCount = 0;
    }

    @Override
    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        Node<E> node = new Node<>(null, e, null);
        if (size == 0) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
        modCount++;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        Node<E> node = new Node<>(null, e, null);
        if (size == 0) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        modCount++;
        size++;
    }

    @Override
    public boolean offerFirst(E e) {
        // return true if the element is added
        if (e == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        Node<E> node = new Node<>(null, e, null);
        if (size == 0) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
        modCount++;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        // return true if the element is added
        if (e == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        Node<E> node = new Node<>(null, e, null);
        if (size == 0) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        modCount++;
        size++;
        return true;
    }

    @Override
    public E removeFirst() {
        // return the first element of the deque
        if (size == 0) {
            throw new NoSuchElementException("The deque is empty.");
        }
        if (size == 1) {
            E e = first.item;
            first = null;
            last = null;
            size--;
            modCount++;
            return e;
        }
        E e = first.item;
        first = first.next;
        modCount++;
        size--;
        return e;
    }

    @Override
    public E removeLast() {
        // return the last element of the deque
        if (size == 0) {
            throw new NoSuchElementException("The deque is empty.");
        }
        if (size == 1) {
            E e = last.item;
            first = null;
            last = null;
            size--;
            modCount++;
            return e;
        }
        E e = last.item;
        last = last.prev;
        last.next = null;
        modCount++;
        size--;
        return e;
    }

    @Override
    public E pollFirst() {
        // return the first element of the deque, or null if the deque is empty
        if (size == 0) {
            return null;
        }
        return removeFirst();
    }

    @Override
    public E pollLast() {
        // return the last element of the deque, or null if the deque is empty
        if (size == 0) {
            return null;
        }
        return removeLast();
    }

    @Override
    public E getFirst() {
        // return the first element of the deque
        if (size == 0) {
            throw new NoSuchElementException("The deque is empty.");
        }
        return first.item;
    }

    @Override
    public E getLast() {
        // return the last element of the deque
        if (size == 0) {
            throw new NoSuchElementException("The deque is empty.");
        }
        return last.item;
    }

    @Override
    public E peekFirst() {
        // return the first element of the deque, or null if the deque is empty
        if (size == 0) {
            return null;
        }
        return getFirst();
    }

    @Override
    public E peekLast() {
        // return the last element of the deque, or null if the deque is empty
        if (size == 0) {
            return null;
        }
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public boolean add(E e) {
        // return true if the element is added
        if (e == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        this.addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        // return true if the element is added
        return this.offerLast(e);
    }

    @Override
    public E remove() {
        // return the first element of the deque
        return this.removeFirst();
    }

    @Override
    public E poll() {
        // return the first element of the deque, or null if the deque is empty
        return this.pollFirst();
    }

    @Override
    public E element() {
        // return the first element of the deque
        return this.getFirst();
    }

    @Override
    public E peek() {
        // return the first element of the deque, or null if the deque is empty
        return this.peekFirst();
    }

    @Override
    public void push(E e) {
        this.addFirst(e);
    }

    @Override
    public E pop() {
        // return the first element of the deque
        return this.removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        // return true if the element is removed
        if (o == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        if (size == 0) {
            return false;
        }
        Node<E> node = first;
        while (node != null) {
            if (node.item.equals(o)) {
                if (size == 1) {
                    first = null;
                    last = null;
                } else if (node == first) {
                    first = first.next;
                    first.prev = null;
                } else if (node == last) {
                    last = last.prev;
                    last.next = null;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                modCount++;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // return true if the element is in the deque
        if (o == null) {
            throw new NullPointerException("Null elements are not permitted.");
        }
        if (size == 0) {
            return false;
        }
        Node<E> node = first;
        while (node != null) {
            if (node.item.equals(o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /*
     * Class for representing the nodes of the double-linked list used to
     * implement this deque. The class and its members/methods are protected
     * instead of private only for JUnit testing purposes.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

        protected Node<E> prev;

        protected Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /*
     * Class for implementing an iterator for this deque. The iterator is
     * fail-fast: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class Itr implements Iterator<E> {
        private Node<E> node;

        private Node<E> lastReturned;

        private int expectedModCount;


        Itr() {
            /*
             * The iterator starts at the first element of the deque. If the
             * deque is empty, the iterator starts at null.
             */
            node = first;
            lastReturned = null;
            expectedModCount = modCount;
        }

        public boolean hasNext() {
            if (lastReturned == null) {
                return node != null;
            } else {
                return lastReturned.next != null;
            }
        }

        public E next() {
            /*
             * If the deque has been modified by a method of the main class the
             * first attempt to call next() must throw a
             * ConcurrentModificationException
             */
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("The deque has been modified.");
            }
            /*
             * If the iterator is at the end of the deque, throw a
             * NoSuchElementException
             */
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elements in the deque.");
            }
            /*
             * If the iterator is at the beginning of the deque, return the
             * first element and update the lastReturned field
             */
            if (lastReturned == null) {
                lastReturned = node;
            } else {
                /*
                 * If the iterator is not at the beginning of the deque, return the
                 * next element and update the lastReturned field
                 */
                lastReturned = lastReturned.next;
            }
            node = node.next;
            return lastReturned.item;
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescItr();
    }

    /*
     * Class for implementing a descending iterator for this deque. The iterator
     * is fail-fast: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class DescItr implements Iterator<E> {
        private Node<E> node;

        private Node<E> lastReturned;

        private int expectedModCount;

        DescItr() {
            /*
             * The iterator starts at the last element of the deque. If the
             * deque is empty, the iterator starts at null.
             */
            node = last;
            lastReturned = null;
            expectedModCount = modCount;
        }

        public boolean hasNext() {
            if (lastReturned == null) {
                return node != null;
            } else {
                return lastReturned.prev != null;
            }
        }

        public E next() {
            /*
             * If the deque has been modified by a method of the main class the
             * first attempt to call next() must throw a
             * ConcurrentModificationException
             */
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("The deque has been modified.");
            }
            /*
             * If the iterator is at the beginning of the deque, throw a
             * NoSuchElementException
             */
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elements in the deque.");
            }
            /*
             * If the iterator is at the end of the deque, return the
             * last element and update the lastReturned field
             */
            if (lastReturned == null) {
                lastReturned = node;
            } else {
                /*
                 * If the iterator is not at the end of the deque, return the
                 * previous element and update the lastReturned field
                 */
                lastReturned = lastReturned.prev;
            }
            node = node.prev;
            return lastReturned.item;
        }

    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getFirstNode() {
        return this.first;
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getLastNode() {
        return this.last;
    }
}
