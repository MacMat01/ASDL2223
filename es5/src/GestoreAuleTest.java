/**
 * 
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Classe di test per GestoreAule
 * 
 * @author Luca Tesei
 *
 */
class GestoreAuleTest {

    @Test
    final void testAddAula() {
        GestoreAule g = new GestoreAule();
        assertThrows(NullPointerException.class, () -> g.addAula(null));
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertTrue(g.addAula(a));
        assertTrue(g.getAule().contains(new Aula("LA1", "")));
        assertFalse(g.addAula(new Aula("LA1", "")));
        Aula aa = new Aula("AA1", "Polo Lodovici Piano Terra");
        assertTrue(g.addAula(aa));
        Aula b = new Aula("AB1", "Polo Lodovici Primo Piano");
        assertTrue(g.addAula(b));
    }

    @Test
    final void testGetAule() {
        GestoreAule g = new GestoreAule();
        assertTrue(g.getAule().isEmpty());
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertTrue(g.addAula(a));
        Aula aa = new Aula("AA1", "Polo Lodovici Piano Terra");
        assertTrue(g.addAula(aa));
        Set<Aula> s = new HashSet<Aula>();
        s.add(new Aula("LA1", ""));
        s.add(new Aula("AA1", ""));
        assertEquals(s, g.getAule());
    }

    @Test
    final void testCercaAuleLibere() {
        GestoreAule g = new GestoreAule();
        Set<Facility> requestedFacilities = new HashSet<Facility>();
        PresenceFacility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        QuantitativeFacility f2 = new QuantitativeFacility("POSTI",
                "Presenza di posti a sedere normali", 80);
        QuantitativeFacility f3 = new QuantitativeFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client", 40);
        requestedFacilities.add(f1);
        requestedFacilities.add(f2);
        requestedFacilities.add(f3);
        TimeSlot requestedTimeSlot = new TimeSlot(
                new GregorianCalendar(2019, 10, 4, 11, 0),
                new GregorianCalendar(2019, 10, 4, 13, 0));
        assertThrows(NullPointerException.class,
                () -> g.cercaAuleLibere(requestedFacilities, null));
        assertThrows(NullPointerException.class,
                () -> g.cercaAuleLibere(null, requestedTimeSlot));
        assertThrows(NullPointerException.class,
                () -> g.cercaAuleLibere(null, null));
        Set<Aula> ris = new HashSet<Aula>();
        // insiemi vuoti
        assertEquals(ris,g.cercaAuleLibere(requestedFacilities, requestedTimeSlot));
        // inserisco le aule
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        a.addFacility(new PresenceFacility("HDMI", ""));
        a.addFacility(new QuantitativeFacility("POSTI", "", 100));
        a.addFacility(new QuantitativeFacility("POSTITC", "", 44));
        a.addFacility(new PresenceFacility("AMPLIFICAZIONE", ""));   
        a.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 9, 0),
                new GregorianCalendar(2019, 10, 4, 11, 5)), "LT", "");
        a.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 3, 11, 0),
                new GregorianCalendar(2019, 10, 3, 13, 0)), "LT", "");
        a.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 15, 0),
                new GregorianCalendar(2019, 10, 4, 18, 0)), "LT", "");
        assertTrue(g.addAula(a));
        Aula aa = new Aula("AA1", "Polo Lodovici Piano Terra");
        aa.addFacility(new PresenceFacility("HDMI", ""));
        aa.addFacility(new QuantitativeFacility("POSTI", "", 100));
        aa.addFacility(new PresenceFacility("AMPLIFICAZIONE", ""));
        aa.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 9, 0),
                new GregorianCalendar(2019, 10, 4, 11, 5)), "LT", "");
        aa.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 3, 11, 0),
                new GregorianCalendar(2019, 10, 3, 13, 0)), "LT", "");
        aa.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 15, 0),
                new GregorianCalendar(2019, 10, 4, 18, 0)), "LT", "");
        assertTrue(g.addAula(aa));
        Aula b = new Aula("LB1", "Polo Lodovici Edificio B");
        b.addFacility(new PresenceFacility("HDMI", ""));
        b.addFacility(new QuantitativeFacility("POSTI", "", 80));
        b.addFacility(new QuantitativeFacility("POSTITC", "", 40));
        b.addFacility(new PresenceFacility("AMPLIFICAZIONE", ""));
        b.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 9, 0),
                new GregorianCalendar(2019, 10, 4, 11, 5)), "LT", "");
        b.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 3, 11, 0),
                new GregorianCalendar(2019, 10, 3, 13, 0)), "LT", "");
        b.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 15, 0),
                new GregorianCalendar(2019, 10, 4, 18, 0)), "LT", "");
        assertTrue(g.addAula(b));
        Aula c = new Aula("C", "Pippo");
        c.addFacility(new PresenceFacility("HDMI", ""));
        c.addFacility(new QuantitativeFacility("POSTI", "", 80));
        c.addFacility(new QuantitativeFacility("POSTITC", "", 40));
        c.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 9, 0),
                new GregorianCalendar(2019, 10, 4, 12, 30)), "LT", "");
        c.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 3, 11, 0),
                new GregorianCalendar(2019, 10, 3, 13, 0)), "LT", "");
        c.addPrenotazione(new TimeSlot(new GregorianCalendar(2019, 10, 4, 15, 0),
                new GregorianCalendar(2019, 10, 4, 18, 0)), "LT", "");
        assertTrue(g.addAula(c));
        // risultati attesi
        ris.add(new Aula("LA1",""));
        ris.add(new Aula("LB1", ""));
        assertEquals(ris,g.cercaAuleLibere(requestedFacilities, requestedTimeSlot));
    }

}
