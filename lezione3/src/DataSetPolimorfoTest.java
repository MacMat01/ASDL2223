import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataSetPolimorfoTest {

    @Test
    final void testDataSetPolimorfo() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        assertTrue(d.isEmpty());
    }

    @Test
    final void testAdd() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        d.add(new BankAccount(0));
        // variabile c1 polimorfa!
        Measurable c1 = new Coin(Coins.PENNY_VALUE, Coins.PENNY_NAME);
        d.add(c1);
        // variabile b2 polimorfa!
        Measurable b2 = new BankAccount(1000);
        d.add(b2);
        Coin c2 = new Coin(Coins.DIME_VALUE, Coins.DIME_NAME);
        d.add(c2);
    }

    @Test
    final void testIsEmpty() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        assertTrue(d.isEmpty());
        d.add(new BankAccount(0));
        assertFalse(d.isEmpty());
    }

    @Test
    final void testGetAverage() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        assertThrows(IllegalStateException.class, () -> d.getAverage());
        d.add(new BankAccount(0));
        assertEquals(d.getAverage(), 0.0);
        d.add(new BankAccount(1000));
        assertEquals(d.getAverage(), 1000.0 / 2);
        d.add(new BankAccount(10000));
        assertEquals(d.getAverage(), (1000.0 + 10000) / 3);
    }

    @Test
    final void testGetMaximum() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        assertThrows(IllegalStateException.class, () -> d.getMaximum());
        BankAccount b1 = new BankAccount(0);
        d.add(b1);
        assertEquals(d.getMaximum(), b1);
        BankAccount b2 = new BankAccount(10);
        d.add(b2);
        assertEquals(d.getMaximum(), b2);
        BankAccount b3 = new BankAccount(5);
        d.add(b3);
        assertEquals(d.getMaximum(), b2);
        BankAccount b4 = new BankAccount(15);
        d.add(b4);
        assertEquals(d.getMaximum(), b4);
        BankAccount b5 = new BankAccount(15);
        d.add(b5);
        assertTrue(d.getMaximum().equals(b4) || d.getMaximum().equals(b5));
    }

    @Test
    final void testGetMinimum() {
        DataSetPolimorfo d = new DataSetPolimorfo();
        assertThrows(IllegalStateException.class, () -> d.getMinimum());
        BankAccount b1 = new BankAccount(110);
        d.add(b1);
        assertEquals(d.getMinimum(), b1);
        BankAccount b2 = new BankAccount(50);
        d.add(b2);
        assertEquals(d.getMinimum(), b2);
        BankAccount b3 = new BankAccount(75);
        d.add(b3);
        assertEquals(d.getMinimum(), b2);
        BankAccount b4 = new BankAccount(15);
        d.add(b4);
        assertEquals(d.getMinimum(), b4);
        BankAccount b5 = new BankAccount(15);
        d.add(b5);
        assertTrue(d.getMinimum().equals(b4) || d.getMinimum().equals(b5));
    }

}
