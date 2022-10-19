/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

    private final TimeSlot timeSlot;

    private String docente;

    private String motivo;

    /**
     * Costruisce una prenotazione.
     *
     * @param aula     l'aula a cui la prenotazione si riferisce
     * @param timeSlot il time slot della prenotazione
     * @param docente  il nome del docente che ha prenotato l'aula
     * @param motivo   il motivo della prenotazione
     * @throws NullPointerException se uno qualsiasi degli oggetti passati è
     *                              null
     */
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente, String motivo) {
        if (aula == null) {
            throw new NullPointerException("L'aula non è stata passata");
        }
        if (timeSlot == null) {
            throw new NullPointerException("Il timeSlot non è stato passato");
        }
        if (docente == null) {
            throw new NullPointerException("Il docente non è stato passato");
        }
        if (motivo == null) {
            throw new NullPointerException("Il motivo non è stato passato");
        }
        this.aula = aula;
        this.timeSlot = timeSlot;
        this.docente = docente;
        this.motivo = motivo;
    }

    /**
     * @return the aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @return the timeSlot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(String docente) {
        this.docente = docente;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        // TODO implementare
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aula == null) ? 0 : aula.hashCode());
        result = prime * result + ((timeSlot == null) ? 0 : timeSlot.hashCode());
        return result;
    }

    /*
     * L'uguaglianza è data solo da stessa aula e stesso time slot. Non sono
     * ammesse prenotazioni diverse con stessa aula e stesso time slot.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO implementare
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Prenotazione)) {
            return false;
        }
        Prenotazione other = (Prenotazione) obj;
        return timeSlot.equals(other.timeSlot) && aula.equals(other.aula);
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot. Se
     * due prenotazioni hanno lo stesso time slot allora una precede l'altra in
     * base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {
        // TODO implementare
        if (o == null) {
            throw new NullPointerException("Tentativo di confrontare con un null");
        }
        if (this.timeSlot.equals(o.timeSlot)) {
            return this.aula.compareTo(o.aula);
        }
        return this.timeSlot.compareTo(o.timeSlot);
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
