/**
 *
 */
package it.unicam.cs.asdl2223.es1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei, Implementation: Collettiva da Esercitazione a
 * Casa
 */
class EquazioneSecondoGradoModificabileConRisolutoreTest {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    static final double EPSILON = 1.0E-15;

    @Test
    final void testEquazioneSecondoGradoModificabileConRisolutore() {
        // controllo che il valore 0 su a lanci l'eccezione
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EquazioneSecondoGradoModificabileConRisolutore(0, 1, 1));
        // devo controllare che comunque nel caso normale il costruttore
        // funziona
        EquazioneSecondoGradoModificabileConRisolutore eq = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 1);
        // Controllo che all'inizio l'equazione non sia risolta
        assertFalse(eq.isSolved());
    }

    @Test
    final void testGetA() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e1 = new EquazioneSecondoGradoModificabileConRisolutore(x, 1, 1);
        // controllo che il valore restituito sia quello che ho messo
        // all'interno
        // dell'oggetto
        Assertions.assertTrue(x == e1.getA());
        // in generale si dovrebbe usare assertTrue(Math.abs(x -
        // e1.getA())<EPSILON) ma in
        // questo caso il valore che testiamo non ha subito manipolazioni quindi
        // la sua rappresentazione sarà la stessa di quella inserita nel
        // costruttore senza errori di approssimazione

    }

    @Test
    final void testSetA() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e2 = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 1);
        e2.setA(x);
        assertEquals(x, e2.getA());
    }

    @Test
    final void testGetB() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e3 = new EquazioneSecondoGradoModificabileConRisolutore(1, x, 1);
        assertEquals(x, e3.getB());
    }

    @Test
    final void testSetB() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e4 = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 1);
        e4.setB(x);
        assertEquals(x, e4.getB());
    }

    @Test
    final void testGetC() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e5 = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, x);
        assertEquals(x, e5.getC());
    }

    @Test
    final void testSetC() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e6 = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 1);
        e6.setC(x);
        assertEquals(x, e6.getC());
    }

    @Test
    final void testIsSolved() {
        EquazioneSecondoGradoModificabileConRisolutore e7 = new EquazioneSecondoGradoModificabileConRisolutore(1, 2, 4);
        e7.solve();
        assertTrue(e7.isSolved());
    }

    @Test
    final void testSolve() {
        EquazioneSecondoGradoModificabileConRisolutore e3 = new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 3);
        // controllo semplicemente che la chiamata a solve() non generi errori
        e3.solve();
        // i test con i valori delle soluzioni vanno fatti nel test del metodo
        // getSolution()
    }

    @Test
    final void testGetSolution() {
        EquazioneSecondoGradoModificabileConRisolutore e8 = new EquazioneSecondoGradoModificabileConRisolutore(1, -3, 2);
        e8.solve();
        double soluzione1 = e8.getSolution().getS1();
        double soluzione2 = e8.getSolution().getS2();
        Assertions.assertEquals(2, soluzione1);
        Assertions.assertEquals(1, soluzione2);
    }

}
