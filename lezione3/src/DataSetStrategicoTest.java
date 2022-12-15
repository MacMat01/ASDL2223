import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DataSetStrategicoTest {

    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    /**
     * Un oggetto RectangleAreaMeasurer è in grado di misurare un oggetto della
     * classe <code>Rectangle</code>. La misura restituita dalla misurazione è
     * l'area del rettangolo.
     *
     * @author Luca Tesei
     */
    class RectangleAreaMeasurer implements Measurer {
        /**
         * Misura un oggetto che deve essere della classe
         * <code>Rectangle</code>.
         *
         * @param anObject l'oggetto <code>Rectangle</code> da misurare
         * @return l'area del rettangolo passato
         * @throws IllegalArgumentException se anObject non è un oggetto
         *                                  <code>Rectangle</code>
         */
        public double measure(Object anObject) {
            if (!(anObject instanceof Rectangle))
                throw new IllegalArgumentException("Tentativo di misurare" + " un oggetto che non è un Rectangle con un " + "misuratore strategico che misura solo Rectangle");
            Rectangle aRectangle = (Rectangle) anObject;
            double area = aRectangle.getHeight() * aRectangle.getWidth();
            return area;
        }
    }

    // Un misuratore di rettangoli usato nei vari test
    // Variabile Polimorfa
    private Measurer m = new RectangleAreaMeasurer();

    @Test
    final void testDataSetStrategico() {
        DataSetStrategico d = new DataSetStrategico(m);
        assertTrue(d.isEmpty());
    }

    @Test
    final void testAdd() {
        DataSetStrategico d = new DataSetStrategico(m);
        d.add(new Rectangle(0, 4, 20, 12));
        d.add(new Rectangle(0, 2, 15, 15));
        d.add(new Rectangle(0, 0, 10, 20));
        // Il metodo add esegue la misurazione, quindi se si passa un oggetto
        // non misurabile deve essere lanciata una eccezione
        assertThrows(IllegalArgumentException.class, () -> d.add(new BankAccount(0)));
    }

    @Test
    final void testIsEmpty() {
        DataSetStrategico d = new DataSetStrategico(m);
        assertTrue(d.isEmpty());
        d.add(new Rectangle(0, 4, 20, 12));
        assertFalse(d.isEmpty());
    }

    @Test
    final void testGetAverage() {
        DataSetStrategico d = new DataSetStrategico(m);
        assertThrows(IllegalStateException.class, () -> d.getAverage());
        d.add(new Rectangle(0, 4, 20, 12));
        assertEquals(d.getAverage(), 20.0 * 12);
        d.add(new Rectangle(0, 2, 15, 15));
        assertEquals(d.getAverage(), (240.0 + 225) / 2);
        d.add(new Rectangle(0, 0, 10, 20));
        assertTrue(Math.abs(d.getAverage() - 221.66666666666666) < EPSILON);
    }

    @Test
    final void testGetMaximum() {
        DataSetStrategico d = new DataSetStrategico(m);
        assertThrows(IllegalStateException.class, () -> d.getMaximum());
        Rectangle r3 = new Rectangle(0, 0, 10, 20);
        d.add(r3);
        assertEquals(d.getMaximum(), r3);
        Rectangle r2 = new Rectangle(0, 2, 15, 15);
        d.add(r2);
        assertEquals(d.getMaximum(), r2);
        Rectangle r1 = new Rectangle(0, 4, 20, 12);
        d.add(r1);
        assertEquals(d.getMaximum(), r1);
        Rectangle r4 = new Rectangle(0, 0, 1, 2);
        d.add(r4);
        assertEquals(d.getMaximum(), r1);
        Rectangle r5 = new Rectangle(0, 4, 20, 12);
        d.add(r5);
        assertTrue(d.getMaximum().equals(r1) || d.getMaximum().equals(r5));
    }

    @Test
    final void testGetMinimum() {
        DataSetStrategico d = new DataSetStrategico(m);
        assertThrows(IllegalStateException.class, () -> d.getMinimum());
        Rectangle r1 = new Rectangle(0, 4, 20, 12);
        d.add(r1);
        assertEquals(d.getMinimum(), r1);
        Rectangle r2 = new Rectangle(0, 2, 15, 15);
        d.add(r2);
        assertEquals(d.getMinimum(), r2);
        Rectangle r3 = new Rectangle(0, 0, 10, 20);
        d.add(r3);
        assertEquals(d.getMinimum(), r3);
        Rectangle r4 = new Rectangle(0, 0, 100, 200);
        d.add(r4);
        assertEquals(d.getMinimum(), r3);
        Rectangle r5 = new Rectangle(0, 0, 10, 20);
        d.add(r5);
        assertTrue(d.getMinimum().equals(r3) || d.getMinimum().equals(r5));
    }

}
