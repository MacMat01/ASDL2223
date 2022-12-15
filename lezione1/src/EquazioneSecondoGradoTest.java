/**
 *
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe di test JUnit 5 per le equazioni di secondo grado.
 *
 * @author Luca Tesei
 */
class EquazioneSecondoGradoTest {

    @Test
    final void testEquazioneSecondoGrado() {
        // controllo del lancio dell'eccezione
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EquazioneSecondoGrado(0, 1, 1));
        // costruzione senza eccezione
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        Assertions.assertEquals(1.0, e1.getA());
        Assertions.assertEquals(1.0, e1.getB());
        Assertions.assertEquals(1.0, e1.getC());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, 2, 1);
        // controllo l'uguaglianza in entrambe le direzioni
        Assertions.assertEquals(e1, e2);
        Assertions.assertEquals(e2, e1);
        // controllo la disuguaglianza in entrambe le direzioni
        Assertions.assertNotEquals(e1, e3);
        Assertions.assertNotEquals(e3, e2);
        // controllo la disuguaglianza con altre classi e null
        Assertions.assertNotEquals(null, e1);
        Assertions.assertNotEquals("Pippo", e1);
    }

    @Test
    final void testCompareTo() {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, 2, 1);
        EquazioneSecondoGrado e4 = new EquazioneSecondoGrado(1, 2, 2);
        EquazioneSecondoGrado e5 = new EquazioneSecondoGrado(2, 1, 1);
        Assertions.assertTrue(e1.compareTo(e3) < 0); // e1 < e3
        Assertions.assertTrue(e3.compareTo(e1) > 0); // e3 > e1
        Assertions.assertEquals(0, e1.compareTo(e2)); // e1 == e2
        Assertions.assertTrue(e3.compareTo(e4) < 0); // e3 < e4
        Assertions.assertTrue(e4.compareTo(e3) > 0); // e4 > e3
        Assertions.assertTrue(e5.compareTo(e4) > 0); // e5 > e4
        Assertions.assertTrue(e4.compareTo(e5) < 0); // e4 < e5
    }

}
