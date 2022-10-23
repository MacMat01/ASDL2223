import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 *
 * @author Template: Luca Tesei, Implementazione: Collettiva
 */
public class Aula implements Comparable<Aula> {
    // Identificativo unico di un'aula
    private final String nome;

    // Location dell'aula
    private final String location;

    // Insieme delle facilities di quest'aula
    private final Set<Facility> facilities;

    // Insieme delle prenotazioni per quest'aula, segue l'ordinamento naturale
    // delle prenotazioni
    private final SortedSet<Prenotazione> prenotazioni;

    /**
     * Costruisce una certa aula con nome e location. Il set delle facilities è
     * vuoto. L'aula non ha inizialmente nessuna prenotazione.
     *
     * @param nome     il nome dell'aula
     * @param location la location dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni
     *                              richieste è nulla
     */
    public Aula(String nome, String location) {
        if (nome == null || location == null) {
            throw new NullPointerException("Il nome o la location non esiste");
        }
        this.nome = nome;
        this.location = location;
        this.facilities = new HashSet<Facility>();
        this.prenotazioni = new TreeSet<Prenotazione>();
    }

    /**
     * Costruisce una certa aula con nome, location e insieme delle facilities.
     * L'aula non ha inizialmente nessuna prenotazione.
     *
     * @param nome       il nome dell'aula
     * @param location   la location dell'aula
     * @param facilities l'insieme delle facilities dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni
     *                              richieste è nulla
     */
    public Aula(String nome, String location, Set<Facility> facilities) {
        if (nome == null || location == null || facilities == null) {
            throw new NullPointerException("Il nome, la location o le facilities non esiste");
        }
        this.nome = nome;
        this.location = location;
        this.facilities = facilities;
        this.prenotazioni = new TreeSet<Prenotazione>();
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + (this.nome.hashCode());
    }

    /* Due aule sono uguali se e solo se hanno lo stesso nome */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) obj;
        if (!(this.nome == other.nome)) {
            return false;
        }
        return true;
    }

    /* L'ordinamento naturale si basa sul nome dell'aula */
    @Override
    public int compareTo(Aula o) {
        return this.nome.compareTo(o.nome);
    }

    /**
     * @return the facilities
     */
    public Set<Facility> getFacilities() {
        return facilities;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the prenotazioni
     */
    public SortedSet<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula.
     *
     * @param f la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     * aggiunta, false altrimenti
     * @throws NullPointerException se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        if (f == null) {
            throw new NullPointerException("La facility passata è nulla");
        }
        return facilities.add(f);
    }

    /**
     * Determina se l'aula è libera in un certo time slot.
     *
     * @param ts il time slot da controllare
     * @return true se l'aula risulta libera per tutto il periodo del time slot
     * specificato
     * @throws NullPointerException se il time slot passato è nullo
     */
    public boolean isFree(TimeSlot ts) {
        /*
         * NOTA: sfruttare l'ordinamento tra le prenotazioni per rispondere in
         * maniera efficiente: poiché le prenotazioni sono in ordine crescente
         * di time slot se arrivo a una prenotazione che segue il time slot
         * specificato posso concludere che l'aula è libera nel time slot
         * desiderato e posso interrompere la ricerca
         */
        if (ts == null) {
            throw new NullPointerException("Il timeslot passato è nullo");
        }
        if (prenotazioni.isEmpty()) {
            return true;
        }
        for (Prenotazione p : prenotazioni) {
            if (p.getTimeSlot().overlapsWith(ts)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     *
     * @param requestedFacilities l'insieme di facilities richieste da
     *                            soddisfare
     * @return true se e solo se tutte le facilities di
     * {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Set<Facility> requestedFacilities) {
        int requestedFacilitiesIndex = 0;
        if (requestedFacilities == null) {
            throw new NullPointerException("Il set di facility è nullo");
        }
        if (requestedFacilities.isEmpty()) {
            return true;
        }
        for (Facility f : this.facilities) {
            for (Facility r : requestedFacilities) {
                if (r.satisfies(f)) {
                    requestedFacilitiesIndex++;
                }
            }
        }
        if (requestedFacilities.size() == requestedFacilitiesIndex) {
            return true;
        } else return false;
    }

    /**
     * Prenota l'aula controllando eventuali sovrapposizioni.
     *
     * @param ts
     * @param docente
     * @param motivo
     * @throws IllegalArgumentException se la prenotazione comporta una
     *                                  sovrapposizione con un'altra
     *                                  prenotazione nella stessa aula.
     * @throws NullPointerException     se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public void addPrenotazione(TimeSlot ts, String docente, String motivo) {
        if (ts == null || docente == null || motivo == null) {
            throw new NullPointerException("Uno degli elementi passati è nullo");
        }
        for (Prenotazione a : prenotazioni) {
            if (a.getTimeSlot().overlapsWith(ts)) {
                throw new IllegalArgumentException("La prenotazione si sovrappone con un'altra");
            }
        }
        prenotazioni.add(new Prenotazione(this, ts, docente, motivo));
    }

    /**
     * Cancella una prenotazione di questa aula.
     *
     * @param p la prenotazione da cancellare
     * @return true se la prenotazione è stata cancellata, false se non era
     * presente.
     * @throws NullPointerException se la prenotazione passata è null
     */
    public boolean removePrenotazione(Prenotazione p) {
        if (p == null) {
            throw new NullPointerException("La prenotazione passata non esiste");
        }
        return prenotazioni.remove(p);
    }

    /**
     * Rimuove tutte le prenotazioni di questa aula che iniziano prima (o
     * esattamente in) di un punto nel tempo specificato.
     *
     * @param timePoint un certo punto nel tempo
     * @return true se almeno una prenotazione è stata cancellata, false
     * altrimenti.
     * @throws NullPointerException se il punto nel tempo passato è nullo.
     */
    public boolean removePrenotazioniBefore(GregorianCalendar timePoint) {
        // TODO implementare
        /*
         * NOTA: sfruttare l'ordinamento tra le prenotazioni per rispondere in
         * maniera efficiente: poiché le prenotazioni sono in ordine crescente
         * di time slot se ho raggiunto una prenotazione con tempo di inizio
         * maggiore del tempo indicato posso smettere la procedura
         */
        if (timePoint == null) {
            throw new NullPointerException("Il punto nel tempo passato è nullo");
        }
        SortedSet<Prenotazione> tmp = new TreeSet<Prenotazione>();
        for (Prenotazione p : prenotazioni) {
            if (p.getTimeSlot().getStart().before(timePoint)) {
                tmp.add(p);
            }
        }
        return prenotazioni.removeAll(tmp);
    }
}
