import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PresenceFacilityTest {

    @Test
    final void testSatisfies() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        PresenceFacility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        PresenceFacility f3 = new PresenceFacility("VGA", "Presenza di un proiettore VGA");
        Facility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        Facility f5 = f2;
        assertThrows(NullPointerException.class,
                () -> f1.satisfies(null));
        assertTrue(f1.satisfies(f2));
        assertTrue(f2.satisfies(f1));
        assertTrue(f5.satisfies(f2));
        assertTrue(f2.satisfies(f5));
        assertTrue(f1.satisfies(f5));
        assertTrue(f5.satisfies(f1));
        assertFalse(f1.satisfies(f3));
        assertFalse(f3.satisfies(f1));
        assertFalse(f1.satisfies(f4));
        assertFalse(f4.satisfies(f1));
        assertFalse(f2.satisfies(f3));
        assertFalse(f3.satisfies(f2));
        assertFalse(f2.satisfies(f4));
        assertFalse(f4.satisfies(f2));
    }

    @Test
    final void testPresenceFacility() {
     // Testa se il controllo di null è corretto
        assertThrows(NullPointerException.class,
                () -> new PresenceFacility("HDMI", null));
        assertThrows(NullPointerException.class,
                () -> new PresenceFacility(null, "Presenza di un proiettore HDMI"));
        assertThrows(NullPointerException.class,
                () -> new PresenceFacility(null, null));
    }

    @Test
    final void testHashCode() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        PresenceFacility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        Facility f5 = f2;
        assertTrue(f1.hashCode() == f2.hashCode());
        assertTrue(f5.hashCode() == f2.hashCode());
    }


    @Test
    final void testGetCodice() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        assertEquals("HDMI", f1.getCodice());
    }

    @Test
    final void testGetDescrizione() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        assertEquals("Presenza di un proiettore HDMI", f1.getDescrizione());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        PresenceFacility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        PresenceFacility f3 = new PresenceFacility("VGA", "Presenza di un proiettore VGA");
        Facility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        Facility f5 = f2;
        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f1));
        assertTrue(f5.equals(f2));
        assertTrue(f2.equals(f5));
        assertTrue(f1.equals(f5));
        assertTrue(f5.equals(f1));
        assertFalse(f1.equals(f3));
        assertFalse(f3.equals(f1));
        assertFalse(f1.equals(f4));
        assertFalse(f4.equals(f1));
        assertFalse(f2.equals(f3));
        assertFalse(f3.equals(f2));
        assertFalse(f2.equals(f4));
        assertFalse(f4.equals(f2));
        // Controllo altra classe
        assertFalse(f1.equals("HDMI"));
        // Controllo null
        assertFalse(f1.equals(null));
    }

}
