import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author Luca Tesei
 */
class MaxHeapTest {

    @Test
    final void testMaxHeap() {
        MaxHeap<Integer> h = new MaxHeap<Integer>();
        assertTrue(h.isEmpty());
    }

    @Test
    final void testSize() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(43);
        list.add(58);
        list.add(12);
        list.add(0);
        list.add(-34);
        list.add(-300);
        list.add(-1);
        list.add(28);
        list.add(99);
        MaxHeap<Integer> h = new MaxHeap<Integer>(list);
        assertTrue(h.size() == 9);
        h = new MaxHeap<Integer>();
        assertTrue(h.size() == 0);
        h.insert(1);
        assertTrue(h.size() == 1);
        h.insert(2);
        assertTrue(h.size() == 2);
        h.extractMax();
        assertTrue(h.size() == 1);
        h.extractMax();
        assertTrue(h.size() == 0);
    }

    @Test
    final void testIsEmpty() {
        MaxHeap<Integer> h = new MaxHeap<Integer>();
        assertTrue(h.isEmpty());
        h.insert(1);
        assertFalse(h.isEmpty());
        h.insert(2);
        h.extractMax();
        assertFalse(h.isEmpty());
        h.extractMax();
        assertTrue(h.isEmpty());
    }

    @Test
    final void testMaxHeapListOfE() {
        assertThrows(NullPointerException.class, () -> new MaxHeap<Integer>(null));
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(43);
        list.add(58);
        list.add(12);
        list.add(0);
        list.add(-34);
        list.add(-300);
        list.add(-1);
        list.add(28);
        list.add(99);
        MaxHeap<Integer> h = new MaxHeap<Integer>(list);
        assertTrue(h.extractMax().equals(99));
        assertTrue(h.extractMax().equals(58));
        //assertTrue(h.extractMax().equals(43));
        assertTrue(h.extractMax().equals(28));
        assertTrue(h.extractMax().equals(12));
        assertTrue(h.extractMax().equals(0));
        assertTrue(h.extractMax().equals(-1));
        assertTrue(h.extractMax().equals(-34));
        assertTrue(h.extractMax().equals(-300));
    }

    @Test
    final void testInsert() {
        MaxHeap<Integer> h = new MaxHeap<Integer>();
        assertThrows(NullPointerException.class, () -> h.insert(null));
        h.insert(39);
        ArrayList<Integer> a = h.getHeap();
        assertTrue(a.get(0).equals(39));
        assertTrue(a.size() == 1);
        h.insert(50);
        assertTrue(a.get(0).equals(50));
        assertTrue(a.get(1).equals(39));
        h.insert(27);
        assertTrue(a.get(0).equals(50));
        assertTrue(a.get(1).equals(39));
        assertTrue(a.get(2).equals(27));
        h.insert(45);
        assertTrue(a.get(0).equals(50));
        assertTrue(a.get(1).equals(45));
        assertTrue(a.get(2).equals(27));
        assertTrue(a.get(3).equals(39));
        h.insert(60);
        assertTrue(a.get(0).equals(60));
        assertTrue(a.get(1).equals(50));
        assertTrue(a.get(2).equals(27));
        assertTrue(a.get(3).equals(39));
        assertTrue(a.get(4).equals(45));
        h.insert(15);
        assertTrue(a.get(0).equals(60));
        assertTrue(a.get(1).equals(50));
        assertTrue(a.get(2).equals(27));
        assertTrue(a.get(3).equals(39));
        assertTrue(a.get(4).equals(45));
        assertTrue(a.get(5).equals(15));
        h.insert(30);
        assertTrue(a.get(0).equals(60));
        assertTrue(a.get(1).equals(50));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(39));
        assertTrue(a.get(4).equals(45));
        assertTrue(a.get(5).equals(15));
        assertTrue(a.get(6).equals(27));
        h.insert(55);
        assertTrue(a.get(0).equals(60));
        assertTrue(a.get(1).equals(55));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(50));
        assertTrue(a.get(4).equals(45));
        assertTrue(a.get(5).equals(15));
        assertTrue(a.get(6).equals(27));
        assertTrue(a.get(7).equals(39));
    }

    @Test
    final void testGetMax() {
        MaxHeap<Integer> h = new MaxHeap<Integer>();
        assertTrue(h.getMax() == null);
        h.insert(12);
        assertTrue(h.getMax().equals(12));
        h.insert(20);
        assertTrue(h.getMax().equals(20));
        h.insert(2);
        assertTrue(h.getMax().equals(20));
        h.insert(5);
        assertTrue(h.getMax().equals(20));
        h.insert(60);
        assertTrue(h.getMax().equals(60));
    }

    @Test
    final void testExtractMax() {
        MaxHeap<Integer> h = new MaxHeap<Integer>();
        assertTrue(h.extractMax() == null);
        h.insert(39);
        h.insert(50);
        h.insert(27);
        h.insert(45);
        h.insert(60);
        h.insert(15);
        h.insert(30);
        h.insert(55);
        ArrayList<Integer> a = h.getHeap();
        assertTrue(a.get(0).equals(60));
        assertTrue(a.get(1).equals(55));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(50));
        assertTrue(a.get(4).equals(45));
        assertTrue(a.get(5).equals(15));
        assertTrue(a.get(6).equals(27));
        assertTrue(a.get(7).equals(39));
        assertTrue(h.extractMax().equals(60));
        assertTrue(a.get(0).equals(55));
        assertTrue(a.get(1).equals(50));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(39));
        assertTrue(a.get(4).equals(45));
        assertTrue(a.get(5).equals(15));
        assertTrue(a.get(6).equals(27));
        assertTrue(h.size() == 7);
        assertTrue(h.extractMax().equals(55));
        assertTrue(a.get(0).equals(50));
        assertTrue(a.get(1).equals(45));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(39));
        assertTrue(a.get(4).equals(27));
        assertTrue(a.get(5).equals(15));
        assertTrue(h.size() == 6);
        assertTrue(h.extractMax().equals(50));
        assertTrue(a.get(0).equals(45));
        assertTrue(a.get(1).equals(39));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(15));
        assertTrue(a.get(4).equals(27));
        assertTrue(h.size() == 5);
        assertTrue(h.extractMax().equals(45));
        assertTrue(a.get(0).equals(39));
        assertTrue(a.get(1).equals(27));
        assertTrue(a.get(2).equals(30));
        assertTrue(a.get(3).equals(15));
        assertTrue(h.size() == 4);
        assertTrue(h.extractMax().equals(39));
        assertTrue(a.get(0).equals(30));
        assertTrue(a.get(1).equals(27));
        assertTrue(a.get(2).equals(15));
        assertTrue(h.size() == 3);
        assertTrue(h.extractMax().equals(30));
        assertTrue(a.get(0).equals(27));
        assertTrue(a.get(1).equals(15));
        assertTrue(h.size() == 2);
        assertTrue(h.extractMax().equals(27));
        assertTrue(a.get(0).equals(15));
        assertTrue(h.size() == 1);
        assertTrue(h.extractMax().equals(15));
        assertTrue(h.size() == 0);
    }

}
