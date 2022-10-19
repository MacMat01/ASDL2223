/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

/**
 * @author Luca Tesei
 *
 */
class AulaTest {

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.Aula#hashCode()}.
     */
    @Test
    final void testHashCode() {
        PresenceFacility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI",
                "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 50);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        a.addFacility(f1);
        a.addFacility(f2);
        a.addFacility(f3);
        a.addFacility(f4);
        a.addFacility(f5);
        Aula aa = new Aula("LA1", " ");
        assertTrue(a.hashCode() == aa.hashCode());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#Aula(java.lang.String, java.lang.String)}.
     */
    @Test
    final void testAulaStringString() {
        // Testa se il controllo di null è corretto
        assertThrows(NullPointerException.class, () -> new Aula("LA1", null));
        assertThrows(NullPointerException.class,
                () -> new Aula(null, "Polo Lodovici Piano Terra"));
        assertThrows(NullPointerException.class, () -> new Aula(null, null));
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        // Controllo inizializzazione dei set
        assertFalse(null == a.getFacilities());
        assertFalse(null == a.getPrenotazioni());
        assertTrue(a.getFacilities().length == Aula.INIT_NUM_FACILITIES);
        assertTrue(a.getPrenotazioni().length == Aula.INIT_NUM_PRENOTAZIONI);
        assertTrue(a.getNumeroFacilities() == 0);
        assertTrue(a.getNumeroPrenotazioni() == 0);
    }

    

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#equals(java.lang.Object)}.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        PresenceFacility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI",
                "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 50);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        a.addFacility(f1);
        a.addFacility(f2);
        a.addFacility(f3);
        a.addFacility(f4);
        a.addFacility(f5);
        Aula aa = new Aula("LA1", " ");
        Aula b = new Aula("AA1", "Polo Lodovici Piano Terra");
        assertTrue(a.equals(aa));
        assertTrue(aa.equals(a));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(aa.equals(b));
        assertFalse(b.equals(aa));
        // Controllo altra classe
        assertFalse(a.equals("Pippo"));
        // Controllo null
        assertFalse(a.equals(null));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#compareTo(it.unicam.cs.asdl1920.mp1.Aula)}.
     */
    @Test
    final void testCompareTo() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        Aula aa = new Aula("LA1", " ");
        Aula b = new Aula("AA1", "Polo Lodovici Piano Terra");
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(aa) < 0);
        assertTrue(a.compareTo(aa) == 0);
        assertTrue(aa.compareTo(a) == 0);
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.Aula#getFacilities()}.
     */
    @Test
    final void testGetFacilities() {
        PresenceFacility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI",
                "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 50);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        a.addFacility(f1);
        a.addFacility(f2);
        a.addFacility(f3);
        a.addFacility(f4);
        a.addFacility(f5);
        Facility[] cmp = a.getFacilities();
        assertFalse(cmp == null);
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.Aula#getNome()}.
     */
    @Test
    final void testGetNome() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertEquals("LA1", a.getNome());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.Aula#getLocation()}.
     */
    @Test
    final void testGetLocation() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertEquals("Polo Lodovici Piano Terra", a.getLocation());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl1920.mp1.Aula#getPrenotazioni()}.
     */
    @Test
    final void testGetPrenotazioni() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.getNumeroPrenotazioni() == 0);
        assertTrue(a.getNumeroPrenotazioni() == 1);
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        assertTrue(a.getNumeroPrenotazioni() == 2);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#addFacility(it.unicam.cs.asdl1920.mp1.Facility)}.
     */
    @Test
    final void testAddFacility() {
        PresenceFacility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI",
                "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 50);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        a.addFacility(f1);
        a.addFacility(f2);
        a.addFacility(f3);
        a.addFacility(f4);
        a.addFacility(f5);
        assertTrue(a.getNumeroFacilities() == 3);
        Facility[] cmp = a.getFacilities();
        assertTrue(cmp[0].equals(f2));
        assertTrue(cmp[1].equals(f3));
        assertTrue(cmp[2].equals(f5));
        assertTrue(cmp[3] == null);
        a.addFacility(new PresenceFacility("HDMI", ""));
        assertTrue(a.getNumeroFacilities() == 3);
        a.addFacility(new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 60));
        assertTrue(a.getNumeroFacilities() == 3);
        a.addFacility(new QuantitativeFacility("PIPPO", "", 0));
        assertTrue(a.getNumeroFacilities() == 4);
        a.addFacility(new QuantitativeFacility("PLUTO", "", 0));
        assertTrue(a.getNumeroFacilities() == 5);
        // raddoppio dell'array
        a.addFacility(new PresenceFacility("MINNIE", ""));
        assertTrue(a.getNumeroFacilities() == 6);
        assertTrue(a.getFacilities().length == 10);       
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#isFree(it.unicam.cs.asdl1920.mp1.TimeSlot)}.
     */
    @Test
    final void testIsFree() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertThrows(NullPointerException.class, () -> a.isFree(null));
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        assertTrue(a.isFree(ts1));
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.isFree(ts1));
        assertTrue(a.isFree(ts2));
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.isFree(ts1));
        GregorianCalendar g5 = new GregorianCalendar(2019, 10, 4, 12, 54);
        GregorianCalendar g6 = new GregorianCalendar(2019, 10, 4, 14, 00);
        TimeSlot ts3 = new TimeSlot(g5, g6);
        assertFalse(a.isFree(ts3));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#satisfiesFacilities(java.util.Set)}.
     */
    @Test
    final void testSatisfiesFacilities() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertThrows(NullPointerException.class,
                () -> a.satisfiesFacilities(null));
        Facility[] requestedFacilities = new Facility[3];
        // a deve soddisfare sempre l'insieme vuoto di facilities richieste
        assertTrue(a.satisfiesFacilities(requestedFacilities));
        a.addFacility(new PresenceFacility("WHITEBOARD", ""));
        assertTrue(a.satisfiesFacilities(requestedFacilities));
        Facility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        QuantitativeFacility f2 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 80);
        QuantitativeFacility f3 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 40);
        requestedFacilities[0] = f1;
        requestedFacilities[1] = f2;
        requestedFacilities[2] = f3;
        assertFalse(a.satisfiesFacilities(requestedFacilities));
        a.addFacility(new PresenceFacility("VGA", ""));
        a.addFacility(new PresenceFacility("HDMI", ""));
        a.addFacility(new QuantitativeFacility("POSTI", "", 80));
        assertFalse(a.satisfiesFacilities(requestedFacilities));
        a.addFacility(new QuantitativeFacility("USCITESICUREZZA", "", 2));
        assertFalse(a.satisfiesFacilities(requestedFacilities));
        a.addFacility(new QuantitativeFacility("POSTITC", "", 40));
        assertTrue(a.satisfiesFacilities(requestedFacilities));

    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.mp1.Aula#addPrenotazione(it.unicam.cs.asdl1920.mp1.TimeSlot, java.lang.String, java.lang.String)}.
     */
    @Test
    final void testAddPrenotazione() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        // Controllo null
        assertThrows(NullPointerException.class,
                () -> a.addPrenotazione(null, "Luca Tesei", "Lezione ASDL"));
        assertThrows(NullPointerException.class,
                () -> a.addPrenotazione(ts1, null, "Lezione ASDL"));
        assertThrows(NullPointerException.class,
                () -> a.addPrenotazione(ts1, "Luca Tesei", null));
        assertThrows(NullPointerException.class,
                () -> a.addPrenotazione(null, null, "Lezione ASDL"));
        assertThrows(NullPointerException.class,
                () -> a.addPrenotazione(ts1, null, null));
        // Controllo inserimenti
        assertTrue(a.getNumeroPrenotazioni() == 0);
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.getNumeroPrenotazioni() == 0);
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        // Controllo sovrapposizione
        GregorianCalendar g5 = new GregorianCalendar(2019, 10, 4, 10, 00);
        GregorianCalendar g6 = new GregorianCalendar(2019, 10, 4, 12, 00);
        TimeSlot ts3 = new TimeSlot(g5, g6);
        assertThrows(IllegalArgumentException.class,
                () -> a.addPrenotazione(ts3, "Luca Tesei", "Pippo"));
    }

}
