import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test JUnit per la classe BinarySearchTree
 *
 * @author Daniele Marchei
 */
class BinarySearchTreeTest {

    @Test
    void testBinarySearchTreeShouldNotThrow() {
        assertDoesNotThrow(() -> {
            new BinarySearchTree<>(42);
        });
    }

    @Test
    void testBinarySearchTreeShouldThrow() {
        assertThrows(NullPointerException.class, () -> {
            new BinarySearchTree<>(null);
        });
    }

    @Test
    void testIsEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertTrue(bst.isEmpty());
    }

    @Test
    void testIsNotEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(42);
        assertFalse(bst.isEmpty());
    }

    @Test
    void testSize0() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertEquals(bst.size(), 0);
    }

    @Test
    void testSize1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(42);
        assertEquals(bst.size(), 1);
    }

    @Test
    void testSize100() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < 100; i++)
            bst.add(i);
        assertEquals(bst.size(), 100);
    }

    @Test
    void testClearEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.clear();
        assertTrue(bst.isEmpty());
    }

    @Test
    void testClearFull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < 100; i++)
            bst.add(i);
        bst.clear();
        assertTrue(bst.isEmpty());
    }

    @Test
    void testGetHeightSorted() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < 100; i++)
            bst.add(i);

        int h = bst.getHeight();
        assertEquals(h, 99);
    }

    @Test
    void testGetHeight0() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        int h = bst.getHeight();
        assertEquals(h, -1);
    }

    @Test
    void testGetHeight1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(42);
        int h = bst.getHeight();
        assertEquals(h, 0);
    }

    @Test
    void testGetHeight7() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        int h = bst.getHeight();
        assertEquals(h, 3);
    }

    @Test
    void testAddOrderedLabelsTo() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        List<Integer> labels = new ArrayList<Integer>();
        bst.addOrderedLabelsTo(labels);
        List<Integer> true_labels = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(labels, true_labels);
    }

    @Test
    void testAddOrderedLabelsToShouldThrow() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(NullPointerException.class, () -> {
            bst.addOrderedLabelsTo(null);
        });
    }

    @Test
    void testGetOrderedLabels() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        List<Integer> labels = bst.getOrderedLabels();
        List<Integer> true_labels = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(labels, true_labels);
    }

    @Test
    void testGetOrderedLabelsEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        List<Integer> labels = bst.getOrderedLabels();
        List<Integer> true_labels = new ArrayList<>();
        assertEquals(labels, true_labels);
    }

    @Test
    void testContains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertTrue(bst.contains(5));
    }

    @Test
    void testNotContains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertFalse(bst.contains(42));
    }

    @Test
    void testContainsShouldThrow() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(NullPointerException.class, () -> {
            bst.contains(null);
        });
    }

    @Test
    void testGetMin() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        int min = bst.getMin();
        assertEquals(min, 1);
    }

    @Test
    void testGetMinEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertNull(bst.getMin());
    }

    @Test
    void testGetMax() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        int min = bst.getMax();
        assertEquals(min, 7);
    }

    @Test
    void testGetMaxEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertNull(bst.getMax());
    }


    @Test
    void testGetSuccessor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        int succ = bst.getSuccessor(3);
        assertEquals(succ, 4);
    }

    @Test
    void testGetSuccessorEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertThrows(IllegalArgumentException.class, () -> {
            bst.getSuccessor(3);
        });
    }

    @Test
    void testGetSuccessorNotExists() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(IllegalArgumentException.class, () -> {
            bst.getSuccessor(42);
        });
    }

    @Test
    void testGetSuccessorNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(NullPointerException.class, () -> {
            bst.getSuccessor(null);
        });
    }

    @Test
    void testGetPredecessor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        int pred = bst.getPredecessor(6);
        assertEquals(pred, 5);
    }

    @Test
    void testGetPredecessorEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertThrows(IllegalArgumentException.class, () -> {
            bst.getPredecessor(3);
        });
    }

    @Test
    void testGetPredecessorNotExists() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(IllegalArgumentException.class, () -> {
            bst.getPredecessor(42);
        });
    }

    @Test
    void testGetPredecessorNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertThrows(NullPointerException.class, () -> {
            bst.getPredecessor(null);
        });
    }

    @Test
    void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertTrue(bst.add(2));
    }

    @Test
    void testAddAlreadyThere() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        assertFalse(bst.add(2));
    }

    @Test
    void testAddNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertThrows(NullPointerException.class, () -> {
            bst.add(null);
        });
    }

    @Test
    void testRemove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(42);
        assertTrue(bst.remove(42));
    }

    @Test
    void testAddNotPresent() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertFalse(bst.remove(2));
    }

    @Test
    void testRemoveNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertThrows(NullPointerException.class, () -> {
            bst.remove(null);
        });
    }

    @Test
    void testGetRoot() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(5);
        bst.add(7);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        assertEquals(2, bst.getRoot().getLabel());
    }


}
