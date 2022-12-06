/**
 *
 */

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe Aula che utilizza le Collections.
 *
 * @author Luca Tesei
 */
class AulaTest {

    @Test
    final void testHashCode() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Set<Facility> f = new HashSet<Facility>();
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f4);
        f.add(f5);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra", f);
        Aula aa = new Aula("LA1", " ");
        assertTrue(a.hashCode() == aa.hashCode());
    }

    @Test
    final void testAulaStringString() {
        // Testa se il controllo di null è corretto
        assertThrows(NullPointerException.class, () -> new Aula("LA1", null));
        assertThrows(NullPointerException.class, () -> new Aula(null, "Polo Lodovici Piano Terra"));
        assertThrows(NullPointerException.class, () -> new Aula(null, null));
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        // Controllo inizializzazione dei set
        assertFalse(null == a.getFacilities());
        assertFalse(null == a.getPrenotazioni());
        assertTrue(a.getFacilities().isEmpty());
        assertTrue(a.getPrenotazioni().isEmpty());
    }

    @Test
    final void testAulaStringStringSetOfFacility() {
        // Testa se il controllo di null è corretto
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Set<Facility> f = new HashSet<Facility>();
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f4);
        f.add(f5);
        assertThrows(NullPointerException.class, () -> new Aula("LA1", "Polo Lodovici Piano Terra", null));
        assertThrows(NullPointerException.class, () -> new Aula("LA1", null, f));
        assertThrows(NullPointerException.class, () -> new Aula(null, "Polo Lodovici Piano Terra", f));
        assertThrows(NullPointerException.class, () -> new Aula(null, null, null));
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra", f);
        // Controllo inizializzazione dei set
        assertFalse(null == a.getFacilities());
        assertFalse(null == a.getPrenotazioni());
        assertFalse(a.getFacilities().isEmpty());
        assertTrue(a.getPrenotazioni().isEmpty());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Set<Facility> f = new HashSet<Facility>();
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f4);
        f.add(f5);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra", f);
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

    @Test
    final void testGetFacilities() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Set<Facility> f = new HashSet<Facility>();
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f4);
        f.add(f5);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra", f);
        Set<Facility> cmp = new HashSet<Facility>();
        cmp.add(new PresenceFacility("HDMI", ""));
        cmp.add(new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60));
        cmp.add(new QuantitativeFacility("POSTITC", "", 0));
        assertEquals(cmp, a.getFacilities());
    }

    @Test
    final void testGetNome() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertEquals("LA1", a.getNome());
    }

    @Test
    final void testGetLocation() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertEquals("Polo Lodovici Piano Terra", a.getLocation());
    }

    @Test
    final void testGetPrenotazioni() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        SortedSet<Prenotazione> ps = new TreeSet<Prenotazione>();
        assertEquals(ps, a.getPrenotazioni());
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.getPrenotazioni().isEmpty());
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        ps.add(new Prenotazione(a, ts1, "", ""));
        ps.add(new Prenotazione(a, ts2, "", ""));
        assertEquals(ps, a.getPrenotazioni());
    }

    @Test
    final void testAddFacility() {
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("HDMI", "Presenza di proiettore HDMI");
        QuantitativeFacility f3 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60);
        QuantitativeFacility f4 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 50);
        QuantitativeFacility f5 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 50);
        Set<Facility> f = new HashSet<Facility>();
        f.add(f1);
        f.add(f2);
        f.add(f3);
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra", f);
        Set<Facility> cmp = new HashSet<Facility>();
        cmp.add(new PresenceFacility("HDMI", ""));
        cmp.add(new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 60));
        assertEquals(cmp, a.getFacilities());
        assertFalse(a.addFacility(f4));
        assertTrue(a.addFacility(f5));
        cmp.add(new QuantitativeFacility("POSTITC", "", 0));
        assertEquals(cmp, a.getFacilities());
    }

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
        a.removePrenotazione(new Prenotazione(a, ts1, "", ""));
        assertTrue(a.isFree(ts3));
        // Svuoto le prenotazioni di a
        a.removePrenotazioniBefore(new GregorianCalendar(2020, 0, 1, 8, 0));
        assertTrue(a.getPrenotazioni().isEmpty());
        // Controllo dell'uso dell'ordinamento tra prenotazioni per determinare
        // se l'aula è libera
        // Riempie l'aula di prenotazioni dalle 8 alle 18 per 1 anno per 28
        // giorni al mese
        for (int anno = 2019; anno <= 2019; anno++)
            for (int mese = 0; mese <= 11; mese++)
                for (int giorno = 1; giorno <= 28; giorno++)
                    for (int ora = 8; ora <= 16; ora += 2) {
                        TimeSlot ts = new TimeSlot(new GregorianCalendar(anno, mese, giorno, ora, 0), new GregorianCalendar(anno, mese, giorno, ora + 2, 0));
                        a.addPrenotazione(ts, "LT", "Pippo");
                    }
        // Controlla che venga usato l'ordinamento tra le prenotazioni per
        // effettuare la ricerca. Se così non è, sicuramente il test impiega
        // più di 10 millisecondi e il test fallisce
        assertTimeout(ofMillis(10), () -> {
            boolean isFree = a.isFree(new TimeSlot(new GregorianCalendar(2019, 0, 1, 16, 00), new GregorianCalendar(2019, 0, 1, 18, 0)));
            assertFalse(isFree);
        });
        assertTimeout(ofMillis(10), () -> {
            boolean isFree = a.isFree(new TimeSlot(new GregorianCalendar(2019, 0, 1, 18, 00), new GregorianCalendar(2019, 0, 1, 20, 0)));
            assertTrue(isFree);
        });

    }

    @Test
    final void testSatisfiesFacilities() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertThrows(NullPointerException.class, () -> a.satisfiesFacilities(null));
        Set<Facility> requestedFacilities = new HashSet<Facility>();
        // a deve soddisfare sempre l'insieme vuoto di facilities richieste
        assertTrue(a.satisfiesFacilities(requestedFacilities));
        a.addFacility(new PresenceFacility("WHITEBOARD", ""));
        assertTrue(a.satisfiesFacilities(requestedFacilities));
        PresenceFacility f1 = new PresenceFacility("HDMI", "Presenza di un proiettore HDMI");
        QuantitativeFacility f2 = new QuantitativeFacility("POSTI", "Presenza di posti a sedere normali", 80);
        QuantitativeFacility f3 = new QuantitativeFacility("POSTITC", "Presenza di posti a sedere con Thin Client", 40);
        requestedFacilities.add(f1);
        requestedFacilities.add(f2);
        requestedFacilities.add(f3);
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
        assertThrows(NullPointerException.class, () -> a.addPrenotazione(null, "Luca Tesei", "Lezione ASDL"));
        assertThrows(NullPointerException.class, () -> a.addPrenotazione(ts1, null, "Lezione ASDL"));
        assertThrows(NullPointerException.class, () -> a.addPrenotazione(ts1, "Luca Tesei", null));
        assertThrows(NullPointerException.class, () -> a.addPrenotazione(null, null, "Lezione ASDL"));
        assertThrows(NullPointerException.class, () -> a.addPrenotazione(ts1, null, null));
        // Controllo inserimenti
        assertTrue(a.getPrenotazioni().isEmpty());
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        assertFalse(a.getPrenotazioni().isEmpty());
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        SortedSet<Prenotazione> ps = new TreeSet<Prenotazione>();
        ps.add(new Prenotazione(a, ts1, "", ""));
        ps.add(new Prenotazione(a, ts2, "", ""));
        assertEquals(ps, a.getPrenotazioni());
        // Controllo sovrapposizione
        GregorianCalendar g5 = new GregorianCalendar(2019, 10, 4, 10, 00);
        GregorianCalendar g6 = new GregorianCalendar(2019, 10, 4, 12, 00);
        TimeSlot ts3 = new TimeSlot(g5, g6);
        assertThrows(IllegalArgumentException.class, () -> a.addPrenotazione(ts3, "Luca Tesei", "Pippo"));
    }

    @Test
    final void testRemovePrenotazione() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertThrows(NullPointerException.class, () -> a.removePrenotazione(null));
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        a.addPrenotazione(ts1, "Luca Tesei", "Lezione ASDL");
        a.addPrenotazione(ts2, "Luca Tesei", "Lezione ASDL");
        GregorianCalendar g5 = new GregorianCalendar(2019, 10, 4, 12, 55);
        GregorianCalendar g6 = new GregorianCalendar(2019, 10, 4, 14, 05);
        TimeSlot ts3 = new TimeSlot(g5, g6);
        a.addPrenotazione(ts3, "Luca Tesei", "Pranzo");
        assertTrue(a.getPrenotazioni().contains(new Prenotazione(a, ts1, "", "")));
        assertTrue(a.removePrenotazione(new Prenotazione(a, ts1, "", "")));
        assertFalse(a.getPrenotazioni().contains(new Prenotazione(a, ts1, "", "")));
        assertFalse(a.removePrenotazione(new Prenotazione(a, ts1, "", "")));
        assertTrue(a.removePrenotazione(new Prenotazione(a, ts2, "", "")));
        assertTrue(a.removePrenotazione(new Prenotazione(a, ts3, "", "")));
        assertTrue(a.getPrenotazioni().isEmpty());
    }

    @Test
    final void testRemovePrenotazioniBefore() {
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        assertThrows(NullPointerException.class, () -> a.removePrenotazioniBefore(null));
        // Riempie l'aula di prenotazioni per 1 anno
        for (int anno = 2019; anno <= 2019; anno++)
            for (int mese = 0; mese <= 11; mese++)
                for (int giorno = 1; giorno <= 28; giorno++)
                    for (int ora = 8; ora <= 16; ora += 2) {
                        TimeSlot ts = new TimeSlot(new GregorianCalendar(anno, mese, giorno, ora, 0), new GregorianCalendar(anno, mese, giorno, ora + 2, 0));
                        a.addPrenotazione(ts, "LT", "Pippo");
                    }
        // Controlla che venga usato l'ordinamento tra le prenotazioni per
        // effettuare la cancellazione, fermandosi appena il tempo passato è
        // stato raggiunto. Se così non è, sicuramente il metodo impiega più di
        // 10 millisecondi e il test fallisce
        assertTimeout(ofMillis(10), () -> {
            a.removePrenotazioniBefore(new GregorianCalendar(2019, 0, 1, 16, 00));
        });
        assertTrue(a.isFree(new TimeSlot(new GregorianCalendar(2019, 0, 1, 8, 0), new GregorianCalendar(2019, 0, 1, 18, 0))));
        assertFalse(a.isFree(new TimeSlot(new GregorianCalendar(2019, 0, 2, 8, 0), new GregorianCalendar(2019, 0, 2, 10, 0))));
        assertFalse(a.isFree(new TimeSlot(new GregorianCalendar(2019, 0, 2, 10, 0), new GregorianCalendar(2019, 0, 2, 12, 0))));
        assertFalse(a.removePrenotazioniBefore(new GregorianCalendar(2019, 0, 1, 16, 00)));
    }

}
