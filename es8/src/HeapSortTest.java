import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HeapSortTest {

    @Test
    final void testSort() {
        SortingAlgorithm<Integer> heapSort = new HeapSort<Integer>();
        assertThrows(NullPointerException.class, () -> heapSort.sort(null));
        ArrayList<Integer> list = new ArrayList<Integer>();
        SortingAlgorithmResult<Integer> res = heapSort.sort(list);
        assertTrue(res.getL().size() == 0);
        assertTrue(res.getCountCompare() == 0);
        list.add(4);
        res = heapSort.sort(list);
        assertTrue(res.getL().get(0).equals(4));
        assertTrue(res.getL().size() == 1);
        list.add(0);
        list.add(90);
        list.add(-30);
        list.add(200);
        list.add(1);
        res = heapSort.sort(list);
        assertTrue(res.getL().size() == 6);
        // controllo esecuzione in loco
        assertTrue(res.getL() == list);
        assertTrue(res.getL().get(0).equals(-30));
        assertTrue(res.getL().get(1).equals(0));
        assertTrue(res.getL().get(2).equals(1));
        assertTrue(res.getL().get(3).equals(4));
        assertTrue(res.getL().get(4).equals(90));
        assertTrue(res.getL().get(5).equals(200));
        // caso con un numero ripetuto
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(8);
        list1.add(-10);
        list1.add(10);
        list1.add(2);
        list1.add(8);
        res = heapSort.sort(list1);
        assertTrue(res.getL().size() == 5);
        // controllo esecuzione in loco
        assertTrue(res.getL() == list1);
        assertTrue(res.getL().get(0).equals(-10));
        assertTrue(res.getL().get(1).equals(2));
        assertTrue(res.getL().get(2).equals(8));
        assertTrue(res.getL().get(3).equals(8));
        assertTrue(res.getL().get(4).equals(10));
    }

}
