/**
 * 
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luca Tesei
 *
 */
class QuantitativeFacilityTest {

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.QuantitativeFacility#satisfies(it.unicam.cs.asdl1920.mp1.Facility)}.
     */
    @Test
    final void testSatisfies() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Facility f6 = f4;
        assertThrows(NullPointerException.class,
                () -> f3.satisfies(null));
        assertTrue(f3.satisfies(f4));
        assertTrue(f3.satisfies(f3));
        assertTrue(f3.satisfies(f6));
        assertFalse(f4.satisfies(f3));
        assertFalse(f6.satisfies(f3));
        assertFalse(f3.satisfies(f1));
        assertFalse(f1.satisfies(f3));
        assertFalse(f2.satisfies(f3));
        assertFalse(f3.satisfies(f2));
        assertFalse(f2.satisfies(f4));
        assertFalse(f4.satisfies(f2));
        assertFalse(f5.satisfies(f4));
        assertFalse(f4.satisfies(f5));
        assertFalse(f5.satisfies(f3));
        assertFalse(f3.satisfies(f5));
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.QuantitativeFacility#QuantitativeFacility(java.lang.String, java.lang.String, int)}.
     */
    @Test
    final void testQuantitativeFacility() {
     // Testa se il controllo di null è corretto
        assertThrows(NullPointerException.class,
                () -> new QuantitativeFacility("POSTI", null, 50));
        assertThrows(NullPointerException.class,
                () -> new QuantitativeFacility(null, "Presenza posti a sedere normali", 50));
        assertThrows(NullPointerException.class,
                () -> new QuantitativeFacility(null, null, 50));
    }
    
    @Test
    final void testGetCodice() {
        QuantitativeFacility f1 = new QuantitativeFacility("POSTI", "Presenza posti a sedere normali", 50);
        assertEquals("POSTI", f1.getCodice());
    }

    @Test
    final void testGetDescrizione() {
        QuantitativeFacility f1 = new QuantitativeFacility("POSTI", "Presenza posti a sedere normali", 50);
        assertEquals("Presenza posti a sedere normali", f1.getDescrizione());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.QuantitativeFacility#getQuantity()}.
     */
    @Test
    final void testGetQuantity() {
        QuantitativeFacility f1 = new QuantitativeFacility("POSTI", "Presenza posti a sedere normali", 50);
        assertTrue(f1.getQuantity() == 50);
    }

}
