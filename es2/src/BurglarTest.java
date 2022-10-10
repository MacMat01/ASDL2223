import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BurglarTest {

    @Test
    final void testBurglar() {
        assertThrows(NullPointerException.class, () -> new Burglar(null));
        new Burglar(new CombinationLock("AAA"));
    }

    @Test
    final void testFindCombination() {
        CombinationLock cl = new CombinationLock("XHS");
        cl.lock();
        Burglar b = new Burglar(cl);
        String comb = b.findCombination();
        assertTrue(comb.equals("XHS"));
        assertTrue(b.getAttempts() > 0);
    }

}
