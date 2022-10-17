/**
 *
 */
package it.unicam.cs.asdl2223.mp1;

/**
 * Un oggetto di questa classe rappresenta una lampada che ha un appoggio
 * circolare. Implementa l'interfaccia ShelfItem, ma come lunghezza e larghezza
 * ha il diametro della base. Ridefinisce il metodo di default per calcolare la
 * superficie occupata restituiendo l'area del cerchio che corrisponde alla
 * base. Una lampada è identificata dal nome e dal nome del brand.
 *
 * @author Luca Tesei (template) // TODO INSERIRE NOME, COGNOME ED EMAIL
 * xxxx@studenti.unicam.it DELLO STUDENTE (implementazione)
 */
public class RoundLamp implements ShelfItem {

    private final double diameter;

    private final double weight;

    private final String name;

    private final String brandName;

    // TODO definire ulteriori variabili istanza che si ritengono necessarie per
    // implementare tutti i metodi

    /**
     * @param diameter  diametro della base in cm
     * @param weight    peso in grammi
     * @param name      nome del modello della lampada
     * @param brandName nome del brand della lampada
     */
    public RoundLamp(double diameter, double weight, String name, String brandName) {
        this.diameter = diameter;
        this.weight = weight;
        this.name = name;
        this.brandName = brandName;
    }

    /*
     * Restituisce l'area del cerchio corrispondente alla base
     */
    @Override
    public double getOccupiedSurface() {
        // TODO implementare
        double area = (Math.PI * Math.pow(this.diameter, 2)) / 4;
        return area;
    }

    /*
     * Restituisce il diametro della base
     */
    @Override
    public double getLength() {
        return this.diameter;
    }

    /*
     * Restituisce il diametro della base
     */
    @Override
    public double getWidth() {
        return this.diameter;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /*
     * Due lampade sono considerate uguali se hanno lo stesso nome e lo stesso
     * nome del brand.
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
        if (!(obj instanceof RoundLamp)) {
            return false;
        }
        RoundLamp other = (RoundLamp) obj;
        if (!(this.name.equals(other.name) && this.brandName.equals(other.brandName))) {
            return false;
        }
        return true;
    }

    /*
     * L'hashcode viene calcolato usando gli stessi campi usati per definire
     * l'uguaglianza
     */
    @Override
    public int hashCode() {
        // TODO implementare
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.name.hashCode());
        result = prime * result + (this.brandName.hashCode());
        return result;
    }

    // TODO inserire eventuali metodi accessori privati per fini di
    // implementazione

}
