/**
 *
 */

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di test per i time slot.
 *
 * @author Luca Tesei
 */

class TimeSlotTest {

    @Test
    final void testHashCode() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        assertTrue(ts1.equals(ts2) && ts2.equals(ts1));
        assertTrue(ts1.hashCode() == ts2.hashCode());
    }

    @Test
    final void testTimeSlot() {
        // Testa se il controllo di null è corretto
        assertThrows(NullPointerException.class, () -> new TimeSlot(null, new GregorianCalendar()));
        assertThrows(NullPointerException.class, () -> new TimeSlot(new GregorianCalendar(), null));
        assertThrows(NullPointerException.class, () -> new TimeSlot(null, null));
        // Testa il controllo su start uguale o successivo a stop
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 01), new GregorianCalendar(2019, 10, 4, 12, 00)));
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 00)));
    }

    @Test
    final void testGetStart() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts = new TimeSlot(g1, g2);
        assertEquals(new GregorianCalendar(2019, 10, 4, 11, 00), ts.getStart());
    }

    @Test
    final void testGetStop() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts = new TimeSlot(g1, g2);
        assertEquals(new GregorianCalendar(2019, 10, 4, 13, 00), ts.getStop());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        assertTrue(ts2.equals(ts1));
        assertTrue(ts1.equals(ts2));
        // sposto in avanti di un'ora l'inizio di g3
        g3.roll(Calendar.HOUR, 1);
        assertFalse(ts2.equals(ts1));
        assertFalse(ts1.equals(ts2));
        // classe diversa
        assertFalse(ts1.equals(new Integer(34)));
        // null
        assertFalse(ts1.equals(null));
    }

    @Test
    final void testCompareTo() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 10, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        //  ts2 inizia prima di ts1
        assertTrue(ts1.compareTo(ts2) > 0);
        assertTrue(ts2.compareTo(ts1) < 0);
        g3.roll(Calendar.HOUR_OF_DAY, 1);
        // ts2 == ts1
        assertTrue(ts1.compareTo(ts2) == 0);
        g3.roll(Calendar.HOUR_OF_DAY, 1);
        // ts2 inizia dopo ts1
        assertTrue(ts1.compareTo(ts2) < 0);
        assertTrue(ts2.compareTo(ts1) > 0);
        // ts2 inizia dopo ts1
        g4.roll(Calendar.HOUR_OF_DAY, 1);
        assertTrue(ts2.compareTo(ts1) > 0);
        assertTrue(ts1.compareTo(ts2) < 0);
    }

    @Test
    final void testGetMinutesOfOverlappingWith() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        // [ ] < >
        assertEquals(-1, ts1.getMinutesOfOverlappingWith(ts2));
        assertEquals(-1, ts2.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts3 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 13, 00), g4);
        // [ ]< >
        assertEquals(-1, ts1.getMinutesOfOverlappingWith(ts3));
        assertEquals(-1, ts3.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts4 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 56), g4);
        // [ < 4 min ] >
        assertEquals(4, ts1.getMinutesOfOverlappingWith(ts4));
        assertEquals(4, ts4.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts4Bis = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 55, 53), g4);
        // [ < 4 min e rotti ] >
        assertEquals(4, ts1.getMinutesOfOverlappingWith(ts4Bis));
        assertEquals(4, ts4Bis.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts5 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 55), g4);
        // [ < 5 min ] >
        assertEquals(5, ts1.getMinutesOfOverlappingWith(ts5));
        assertEquals(5, ts5.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts6 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 54), g4);
        // [ < 6 min ] >
        assertEquals(6, ts1.getMinutesOfOverlappingWith(ts6));
        assertEquals(6, ts6.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts7 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 30));
        // [ < 30 min > ]
        assertEquals(30, ts1.getMinutesOfOverlappingWith(ts7));
        assertEquals(30, ts7.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts8 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 6));
        // [ < 6 min > ]
        assertEquals(6, ts1.getMinutesOfOverlappingWith(ts8));
        assertEquals(6, ts8.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts9 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 5));
        // [ < 5 min > ]
        assertEquals(5, ts1.getMinutesOfOverlappingWith(ts9));
        assertEquals(5, ts9.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts10 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 30));
        // < [ 30 min > ]
        assertEquals(30, ts1.getMinutesOfOverlappingWith(ts10));
        assertEquals(30, ts10.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts11 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 6));
        // < [ 6 min > ]
        assertEquals(6, ts1.getMinutesOfOverlappingWith(ts11));
        assertEquals(6, ts11.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts12 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 4));
        // < [ 4 min > ]
        assertEquals(4, ts1.getMinutesOfOverlappingWith(ts12));
        assertEquals(4, ts12.getMinutesOfOverlappingWith(ts1));
        TimeSlot ts13 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 10, 58));
        // < > [ ]
        assertEquals(-1, ts1.getMinutesOfOverlappingWith(ts13));
        assertEquals(-1, ts13.getMinutesOfOverlappingWith(ts1));
    }

    @Test
    final void testOverlapsWith() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 16, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        // [ ] < >
        assertFalse(ts1.overlapsWith(ts2));
        assertFalse(ts2.overlapsWith(ts1));
        TimeSlot ts3 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 13, 00), g4);
        // [ ]< >
        assertFalse(ts1.overlapsWith(ts3));
        assertFalse(ts3.overlapsWith(ts1));
        TimeSlot ts4 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 56), g4);
        // [ < 4 min ] >
        assertFalse(ts1.overlapsWith(ts4));
        assertFalse(ts4.overlapsWith(ts1));
        TimeSlot ts5 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 55), g4);
        // [ < 5 min ] >
        assertFalse(ts1.overlapsWith(ts5));
        assertFalse(ts5.overlapsWith(ts1));
        TimeSlot ts6 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 54), g4);
        // [ < 6 min ] >
        assertTrue(ts1.overlapsWith(ts6));
        assertTrue(ts6.overlapsWith(ts1));
        TimeSlot ts7 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 30));
        // [ < 30 min > ]
        assertTrue(ts1.overlapsWith(ts7));
        assertTrue(ts7.overlapsWith(ts1));
        TimeSlot ts8 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 6));
        // [ < 6 min > ]
        assertTrue(ts1.overlapsWith(ts8));
        assertTrue(ts8.overlapsWith(ts1));
        TimeSlot ts9 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 12, 00), new GregorianCalendar(2019, 10, 4, 12, 5));
        // [ < 5 min > ]
        assertFalse(ts1.overlapsWith(ts9));
        assertFalse(ts9.overlapsWith(ts1));
        TimeSlot ts10 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 30));
        // < [ 30 min > ]
        assertTrue(ts1.overlapsWith(ts10));
        assertTrue(ts10.overlapsWith(ts1));
        TimeSlot ts11 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 6));
        // < [ 6 min > ]
        assertTrue(ts1.overlapsWith(ts11));
        assertTrue(ts11.overlapsWith(ts1));
        TimeSlot ts12 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 11, 4));
        // < [ 4 min > ]
        assertFalse(ts1.overlapsWith(ts12));
        assertFalse(ts12.overlapsWith(ts1));
        TimeSlot ts13 = new TimeSlot(new GregorianCalendar(2019, 10, 4, 10, 30), new GregorianCalendar(2019, 10, 4, 10, 58));
        // < > [ ]
        assertFalse(ts1.overlapsWith(ts13));
        assertFalse(ts13.overlapsWith(ts1));
    }

    @Test
    final void testToString() {
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 10, 11, 15);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 10, 23, 45);
        TimeSlot ts2 = new TimeSlot(g3, g4);
        assertEquals("[4/10/2019 11.0 - 4/10/2019 13.0]", ts1.toString());
        assertEquals("[10/10/2019 11.15 - 10/10/2019 23.45]", ts2.toString());
    }

}
