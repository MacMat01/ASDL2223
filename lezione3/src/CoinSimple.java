/**
 * Un oggetto di questa classe rappresenta una moneta degli USA con il suo
 * valore.
 */
public class CoinSimple {
    // gli oggetti di questa classe sono immutabili

    private final double value;

    private final String name;

    /**
     * Costruisce una moneta con un valore e un nome.
     *
     * @param aValue valore della moneta
     * @param aName  nome della moneta
     */
    public CoinSimple(double aValue, String aName) {
        value = aValue;
        name = aName;
    }

    /**
     * @return il valore della moneta
     */
    public double getValue() {
        return value;
    }

    /**
     * @return il nome della moneta
     */
    public String getName() {
        return name;
    }

    /*
     * Due monete sono uguali se e solo se hanno lo stesso valore e lo stesso
     * nome.
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof CoinSimple)) return false;
        CoinSimple other = (CoinSimple) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) return false;
        return true;
    }

    /*
     * L'hash di una moneta è calcolato sulla base del valore e del nome, campi
     * immutabili usati per determinare l'uguaglianza.
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
