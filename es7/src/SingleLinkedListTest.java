import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

    @Test
    void testContains() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertFalse(list.contains(1));
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    void testAddE() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertThrows(NullPointerException.class, () -> list.add(null));

        assertTrue(list.add(1));
        assertTrue(list.size() == 1);
        assertTrue(list.contains(1));

        assertTrue(list.add(2));
        assertTrue(list.size() == 2);
        assertTrue(list.contains(2));

        assertTrue(list.add(3));
        assertTrue(list.size() == 3);
        assertTrue(list.contains(3));

        assertTrue(list.add(1));
        assertTrue(list.size() == 4);
    }

    @Test
    void testRemoveObject() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(NullPointerException.class, () -> list.remove(null));

        assertFalse(list.remove("Pippo"));
        assertTrue(list.size() == 0);

        list.add("Pippo");
        assertTrue(list.remove("Pippo"));
        assertTrue(list.size() == 0);

        list.add("Pluto");
        list.add("Pluto");
        assertTrue(list.remove("Pluto"));

        assertTrue(list.size() == 1);
        assertTrue(list.contains("Pluto"));
    }

    @Test
    void testClear() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        list.add("");
        list.add(" ");
        list.add("  ");
        list.add("   ");
        list.add("    ");

        list.clear();

        assertFalse(list.contains(""));
        assertFalse(list.contains(" "));
        assertFalse(list.contains("  "));
        assertFalse(list.contains("   "));
        assertFalse(list.contains("    "));

        assertTrue(list.isEmpty());
    }

    @Test
    void testGet() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));

        assertTrue(list.get(0).equals("1"));
        assertTrue(list.get(1).equals("2"));
        assertTrue(list.get(2).equals("3"));
        assertTrue(list.get(3).equals("4"));
        assertTrue(list.get(4).equals("5"));
    }

    @Test
    void testSet() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        list.add("0");
        assertThrows(NullPointerException.class, () -> list.set(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "Pippo"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "Pippo"));

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        assertEquals("0", list.set(0, "0"));
        assertEquals("2", list.set(2, "2.5"));
        assertEquals("4", list.set(4, "6"));

        assertTrue(list.get(0).equals("0"));
        assertTrue(list.get(1).equals("1"));
        assertTrue(list.get(2).equals("2.5"));
        assertTrue(list.get(3).equals("3"));
        assertTrue(list.get(4).equals("6"));

        assertTrue(list.size() == 6);
    }

    @Test
    void testAddIntE() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(NullPointerException.class, () -> list.add(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "1"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "2"));

        list.add("0");
        assertTrue(list.get(0).equals("0"));

        list.add("1");
        list.add(1, "1.5");
        assertTrue(list.get(1).equals("1.5"));
        assertTrue(list.get(2).equals("1"));

        list.add("2");
        list.add("3");
        list.add("4");
        assertTrue(list.get(3).equals("2"));
        assertTrue(list.get(4).equals("3"));
        assertTrue(list.get(5).equals("4"));

        list.add(6, "6");
        assertTrue(list.get(6).equals("6"));

        assertTrue(list.size() == 7);
    }

    @Test
    void testRemoveInt() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));

        list.remove(0);
        assertTrue(list.get(0).equals("2"));
        assertTrue(list.get(1).equals("3"));
        assertTrue(list.get(2).equals("4"));
        assertTrue(list.get(3).equals("5"));

        list.remove(1);
        assertTrue(list.get(0).equals("2"));
        assertTrue(list.get(1).equals("4"));
        assertTrue(list.get(2).equals("5"));

        list.remove(2);
        assertTrue(list.get(0).equals("2"));
        assertTrue(list.get(1).equals("4"));

        assertTrue(list.size() == 2);
    }

    @Test
    void testIndexOf() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(NullPointerException.class, () -> list.indexOf(null));
        assertTrue(list.indexOf("0") == -1);

        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");

        assertTrue(list.indexOf("1") == 0);
        assertTrue(list.indexOf("2") == 1);
        assertTrue(list.indexOf("3") == 3);
        assertTrue(list.indexOf("0") == -1);
    }

    @Test
    void testLastIndexOf() {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        assertThrows(NullPointerException.class, () -> list.lastIndexOf(null));
        assertTrue(list.lastIndexOf("0") == -1);

        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");

        assertTrue(list.lastIndexOf("1") == 0);
        assertTrue(list.lastIndexOf("2") == 2);
        assertTrue(list.lastIndexOf("3") == 3);
        assertTrue(list.lastIndexOf("0") == -1);
    }

    @Test
    final void testIterator() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> sLL.iterator().next());
        {
            Iterator<Integer> itr = sLL.iterator();
            sLL.add(0);
            assertThrows(ConcurrentModificationException.class, () -> itr.next());
        }
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        Iterator<Integer> itr = sLL.iterator();
        assertTrue(itr.hasNext());
        assertEquals(0, itr.next());
        assertTrue(itr.hasNext());
        assertEquals(1, itr.next());
        for (int i = 0; i < 7; i++) {
            assertTrue(itr.hasNext());
            assertEquals(2, itr.next());
        }
        assertFalse(itr.hasNext());
    }

    @Test
    final void testContains1() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        assertThrows(NullPointerException.class, () -> sLL.contains(null));
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        assertTrue(sLL.contains(1));
        assertFalse(sLL.contains(3));
    }

    @Test
    final void testRemove() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        assertFalse(sLL.remove((Object) 3));
        assertTrue(sLL.remove((Object) 2));
        assertEquals(8, sLL.size());
    }

    @Test
    final void testClear1() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        sLL.clear();
        assertEquals(0, sLL.size());
        assertEquals(new SingleLinkedList<>(), sLL);
    }

    @Test
    final void testGet1() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        assertEquals(0, sLL.get(0));
        assertEquals(1, sLL.get(1));
        assertEquals(2, sLL.get(3));
        assertEquals(2, sLL.get(6));
    }

    @Test
    final void testSet1() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        assertThrows(NullPointerException.class, () -> sLL.set(0, null));
        assertEquals(2, sLL.set(4, 3));
        assertEquals(9, sLL.size());
        assertEquals(3, sLL.get(4));
    }

    @Test
    final void testAddAndRemoveAtIndex() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        assertThrows(NullPointerException.class, () -> sLL.add(0, null));
        sLL.add(5, 5);
        assertEquals(2, sLL.get(4));
        assertEquals(5, sLL.get(5));
        assertEquals(2, sLL.get(6));
        assertTrue(sLL.remove(5) == 5);
        assertEquals(2, sLL.get(4));
        assertEquals(2, sLL.get(5));
        assertEquals(2, sLL.get(6));
    }

    @Test
    final void testIndexes() {
        SingleLinkedList<Integer> sLL = new SingleLinkedList<>();
        sLL.add(0);
        sLL.add(1);
        for (int i = 0; i < 7; i++) {
            sLL.add(2);
        }
        sLL.add(1);
        assertEquals(1, sLL.indexOf(1));
        assertEquals(9, sLL.lastIndexOf(1));
        assertEquals(-1, sLL.indexOf(23));
        assertEquals(-1, sLL.lastIndexOf(23));
    }
}
