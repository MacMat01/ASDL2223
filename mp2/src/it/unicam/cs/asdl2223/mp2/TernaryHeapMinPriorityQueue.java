package it.unicam.cs.asdl2223.mp2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class that provides an implementation of a "dynamic" min-priority queue based
 * on a ternary heap. "Dynamic" means that the priority of an element already
 * present in the queue may be decreased, so possibly this element may become
 * the new minimum element. The elements that can be inserted may be of any
 * class implementing the interface <code>PriorityQueueElement</code>. This
 * min-priority queue does not have capacity restrictions, i.e., it is always
 * possible to insert new elements and the number of elements is unbound.
 * Duplicated elements are permitted while <code>null</code> elements are not
 * permitted.
 *
 * @author Template: Luca Tesei, Implementation: Matteo Machella - matteo.machella@studenti.unicam.it
 */
public class TernaryHeapMinPriorityQueue {

    /*
     * ArrayList for representing the ternary heap. Use all positions, including
     * position 0 (the JUnit tests will assume so). You have to adapt
     * child/parent indexing formulas consequently.
     */
    private ArrayList<PriorityQueueElement> heap;

    /**
     * Create an empty queue.
     */
    public TernaryHeapMinPriorityQueue() {
        this.heap = new ArrayList<PriorityQueueElement>();
    }

    /**
     * Return the current size of this queue.
     *
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the ternary heap. The handle of the element will also be set
     * accordingly.
     *
     * @param element the new element to add
     * @throws NullPointerException if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        if (element == null) {
            throw new NullPointerException();
        }
        /*
         * If heap is empty, add the element and set the handle to 0.
         */
        if (size() == 0) {
            this.heap.add(element);
            element.setHandle(0);
            /*
             * If the heap is not empty, add the element to the end of the heap
             * and then bubble it up to the correct position using the priority.
             */
        } else {
            this.heap.add(element);
            element.setHandle(size() - 1);
            bubbleUp(size() - 1);
        }
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the ternary heap.
     *
     * @return the current minimum element of this min-priority queue
     * @throws NoSuchElementException if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        if (this.heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        // Return the first element of the heap, which is the minimum.
        return this.heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     *
     * @return the current minimum element
     * @throws NoSuchElementException if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        if (this.heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        /*
         * Swap the first and the last element of the heap and then bubble the
         * first element down to the correct position.
         */
        PriorityQueueElement min = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.set(this.heap.size() - 1, min);
        this.heap.get(0).setHandle(0);
        this.heap.remove(this.heap.size() - 1);
        int i = 0;
        while (i < this.heap.size()) {
            int minIndex = i;
            /*
             * If the first child is smaller than the parent, set the minIndex
             * to the first child.
             */
            if (3 * i + 1 < this.heap.size() && this.heap.get(3 * i + 1).getPriority() < this.heap.get(minIndex).getPriority()) {
                minIndex = 3 * i + 1;
            }
            /*
             * If the second child is smaller than the parent, set the minIndex
             * to the second child.
             */
            if (3 * i + 2 < this.heap.size() && this.heap.get(3 * i + 2).getPriority() < this.heap.get(minIndex).getPriority()) {
                minIndex = 3 * i + 2;
            }
            /*
             * If the third child is smaller than the parent, set the minIndex
             * to the third child.
             */
            if (3 * i + 3 < this.heap.size() && this.heap.get(3 * i + 3).getPriority() < this.heap.get(minIndex).getPriority()) {
                minIndex = 3 * i + 3;
            }
            /*
             * If the minIndex is not the parent, swap the parent with the
             * smallest child and then set the handle of the parent and the
             * child.
             */
            if (minIndex != i) {
                PriorityQueueElement temp = this.heap.get(i);
                this.heap.set(i, this.heap.get(minIndex));
                this.heap.set(minIndex, temp);
                this.heap.get(i).setHandle(i);
                this.heap.get(minIndex).setHandle(minIndex);
                i = minIndex;
            } else {
                break;
            }
        }
        // Return the minimum element.
        return min;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the ternary heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     *
     * @param element     the element whose priority will be decreased, it
     *                    must currently be inside this min-priority queue
     * @param newPriority the new priority to assign to the element
     * @throws NoSuchElementException   if the element is not currently
     *                                  present in this min-priority queue
     * @throws IllegalArgumentException if the specified newPriority is not
     *                                  strictly less than the current
     *                                  priority of the element
     */
    public void decreasePriority(PriorityQueueElement element, double newPriority) {
        if (element.getHandle() == -1) {
            throw new NoSuchElementException();
        }
        if (element.getPriority() <= newPriority) {
            throw new IllegalArgumentException();
        }
        /*
         * Update the priority of the element and then bubble it up to the
         * correct position.
         */
        element.setPriority(newPriority);
        bubbleUp(element.getHandle());
    }

    private void bubbleUp(int i) {
        /*
         * If the parent is smaller than the child, swap the parent and the
         * child and then set the handle of the parent and the child.
         */
        while (i > 0 && this.heap.get(i).getPriority() < this.heap.get((i - 1) / 3).getPriority()) {
            PriorityQueueElement temp = this.heap.get(i);
            this.heap.set(i, this.heap.get((i - 1) / 3));
            this.heap.set((i - 1) / 3, temp);
            this.heap.get(i).setHandle(i);
            this.heap.get((i - 1) / 3).setHandle((i - 1) / 3);
            i = (i - 1) / 3;
        }
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected ArrayList<PriorityQueueElement> getTernaryHeap() {
        return this.heap;
    }

}
