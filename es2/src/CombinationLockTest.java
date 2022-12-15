import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinationLockTest {

    @Test
    final void testCombinationLock() {
        assertThrows(NullPointerException.class, () -> new CombinationLock(null));
        assertThrows(IllegalArgumentException.class, () -> new CombinationLock("AA"));
        assertThrows(IllegalArgumentException.class, () -> new CombinationLock("ABa"));
        new CombinationLock("ABC");
    }

    @Test
    final void testSetPosition() {
        CombinationLock c = new CombinationLock("ABC");
        assertThrows(IllegalArgumentException.class, () -> c.setPosition('a'));
        c.lock();
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('C');
        c.open();
        assertTrue(c.isOpen());
        c.lock();
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('A');
        c.open();
        assertFalse(c.isOpen());
    }

    @Test
    final void testOpen() {
        CombinationLock c = new CombinationLock("ABC");
        c.lock();
        c.open();
        assertFalse(c.isOpen());
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('A');
        c.open();
        assertFalse(c.isOpen());
        c.setPosition('B');
        c.setPosition('C');
        c.open();
        assertFalse(c.isOpen());
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('C');
        assertFalse(c.isOpen());
        c.open();
        assertTrue(c.isOpen());
    }

    @Test
    final void testIsOpen() {
        CombinationLock c = new CombinationLock("ABC");
        assertTrue(c.isOpen());
        c.lock();
        assertFalse(c.isOpen());
    }

    @Test
    final void testLock() {
        CombinationLock c = new CombinationLock("ABC");
        assertTrue(c.isOpen());
        c.lock();
        assertFalse(c.isOpen());
        c.open();
        assertFalse(c.isOpen());
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('C');
        c.open();
        assertTrue(c.isOpen());
        c.lock();
        c.open();
        assertFalse(c.isOpen());
    }

    @Test
    final void testLockAndChangeCombination() {
        CombinationLock c = new CombinationLock("ABC");
        assertTrue(c.isOpen());
        c.lock();
        assertFalse(c.isOpen());
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('C');
        c.open();
        assertTrue(c.isOpen());
        assertThrows(NullPointerException.class, () -> c.lockAndChangeCombination(null));
        assertThrows(IllegalArgumentException.class, () -> c.lockAndChangeCombination("AA"));
        assertThrows(IllegalArgumentException.class, () -> c.lockAndChangeCombination("ABa"));
        c.lockAndChangeCombination("BCA");
        assertFalse(c.isOpen());
        c.setPosition('A');
        c.setPosition('B');
        c.setPosition('C');
        c.open();
        assertFalse(c.isOpen());
        c.setPosition('B');
        c.setPosition('C');
        c.setPosition('A');
        c.open();
        assertTrue(c.isOpen());
        c.lockAndChangeCombination("BCA");
        c.open();
        assertFalse(c.isOpen());
    }

}
