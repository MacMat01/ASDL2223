/**
 *
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Luca Tesei
 */
class DataSetTest {

    /*
     * Costante piccola per il confronto di due numeri double
     */
    static final double EPSILON = 1.0E-15;

    @Test
    final void testDataSet() {
        // Testo semplicemente che si può costruire un data set e che all'inizio
        // non ci sono valori
        DataSet d = new DataSet();
        assertEquals(d.getMaximum(), Double.NaN);
        assertEquals(d.getMinimum(), Double.NaN);
        assertEquals(d.getAverage(), Double.NaN);
    }

    @Test
    final void testAdd() {
        DataSet d = new DataSet();
        d.add(1);
        assertEquals(d.getMinimum(), 1);
        assertEquals(d.getMaximum(), 1);
        assertEquals(d.getAverage(), 1);
        d.add(2);
        assertEquals(d.getMinimum(), 1);
        assertEquals(d.getMaximum(), 2);
        assertEquals(d.getAverage(), 1.5);
    }

    @Test
    final void testGetMaximum() {
        DataSet d = new DataSet();
        d.add(1);
        assertEquals(d.getMaximum(), 1);
        d.add(-1);
        assertEquals(d.getMaximum(), 1);
        d.add(3);
        assertEquals(d.getMaximum(), 3);
    }

    @Test
    final void testGetMinimum() {
        DataSet d = new DataSet();
        d.add(1);
        assertEquals(d.getMinimum(), 1);
        d.add(-1);
        assertEquals(d.getMinimum(), -1);
        d.add(-1);
        assertEquals(d.getMinimum(), -1);
    }

    @Test
    final void testGetAverage() {
        DataSet d = new DataSet();
        d.add(1.5);
        assertEquals(d.getAverage(), 1.5);
        d.add(3);
        assertEquals(d.getAverage(), (1.5 + 3) / 2);
        d.add(5.5);
        assertTrue(Math.abs(d.getAverage() - 3.3333333333333335) < EPSILON);
    }

}
