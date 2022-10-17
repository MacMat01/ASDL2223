package it.unicam.cs.asdl2223.mp1;

/**
 * Un oggetto di questa classe rappresenta una mensola su cui possono essere
 * appoggiati degli oggetti. Tali oggetti possono essere di diverso tipo, ma
 * tutti implementano l'interface ShelfItem. Un oggetto non può essere
 * appoggiato sulla mensola se ha lunghezza o larghezza che eccedono quelle
 * della mensola stessa. La mensola può contenere un numero non precisato di
 * oggetti, ma ad un certo punto non si possono appoggiare oggetti la cui
 * superficie occupata o il cui peso fanno eccedere la massima superficie
 * occupabile o il massimo peso sostenibile definiti nel costruttore della
 * mensola.
 *
 * @author Luca Tesei (template) // TODO INSERIRE NOME, COGNOME ED EMAIL
 * xxxx@studenti.unicam.it DELLO STUDENTE (implementazione)
 */
public class Shelf {
    /*
     * Dimensione iniziale dell'array items. Quando non è più sufficiente
     * l'array deve essere raddoppiato, anche più volte se necessario.
     */
    private final int INITIAL_SIZE = 5;

    /*
     * massima lunghezza di un oggetto che può essere appoggiato sulla mensola
     * in cm
     */
    private final double maxLength;

    /*
     * massima larghezza di un oggetto che può essere appoggiato sulla mensola
     * in cm
     */
    private final double maxWidth;

    /*
     * massima superficie occupabile della mensola in cm^2
     */
    private final double maxOccupableSurface;

    /*
     * massimo peso sostenibile dalla mensola in grammi
     */
    private final double maxTotalWeight;

    /*
     * array contenente tutti gli oggetti attualmente poggiati sulla mensola. In
     * caso di necessità viene raddoppiato nel momento che si poggia un nuovo
     * oggetto che fa superare la capacità dell'array.
     */
    private ShelfItem[] items;

    /*
     * variabile che indica il numero corrente di caselle nell'array che sono
     * occupate
     */
    private int numberOfItems;

    // TODO definire ulteriori variabili istanza che si ritengono necessarie per
    // implementare tutti i metodi

    /**
     * Costruisce una mensola con le sue caratteristiche. All'inizio nessun
     * oggetto è posato sulla mensola.
     *
     * @param maxLength           lunghezza massima di un oggetto
     *                            appoggiabile in cm
     * @param maxWidth            larghezza massima di un oggetto
     *                            appoggiabile in cm
     * @param maxOccupableSurface massima superficie occupabile di questa
     *                            mensola in cm^2
     * @param maxTotalWeight      massimo peso sostenibile da questa mensola
     *                            in grammi
     */
    public Shelf(double maxLength, double maxWidth, double maxOccupableSurface, double maxTotalWeight) {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxOccupableSurface = maxOccupableSurface;
        this.maxTotalWeight = maxTotalWeight;
        this.items = new ShelfItem[INITIAL_SIZE];
        this.numberOfItems = 0;
        // TODO implementare
    }

    /**
     * Aggiunge un nuovo oggetto sulla mensola. Qualora non ci sia più spazio
     * nell'array che contiene gli oggetti correnti, tale array viene
     * raddoppiato per fare spazio al nuovo oggetto.
     *
     * @param i l'oggetto da appoggiare
     * @return true se l'oggetto è stato inserito, false se è già presente
     * @throws IllegalArgumentException se il peso dell'oggetto farebbe
     *                                  superare il massimo peso consentito
     *                                  oopure se la superficie dell'oggetto
     *                                  farebbe superare la massima
     *                                  superficie occupabile consentita,
     *                                  oppure se la lunghezza o larghezza
     *                                  dell'oggetto superano quelle massime
     *                                  consentite
     * @throws NullPointerException     se l'oggetto passato è null
     */
    public boolean addItem(ShelfItem i) {
        // TODO implementare
        if (i == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        if ((i.getWeight() + getCurrentTotalWeight()) > this.maxTotalWeight || (i.getOccupiedSurface() + getCurrentTotalOccupiedSurface()) > this.maxOccupableSurface || i.getLength() > this.maxLength || i.getWidth() > this.maxWidth) {
            throw new IllegalArgumentException("Il valore del peso, della superficie occupata," + "della lunghezza o della larghezza dell'oggetto superano i limiti previsti");
        }
        for (int j = 0; j < this.items.length; j++) {
            if (this.items[j].equals(i)) {
                // oggetto già inserito
                return false;
            }
            // se la casella è vuota inserisco l'oggetto
            else if (this.items[j] == null) {
                this.items[j] = i;
                numberOfItems++;
                return true;
            }
            // se sono arrivato all'ultima iterazione senza aver inserito l'oggetto, raddoppio lo spazio dell'array
            if (j == this.items.length - 1) {
                this.items = new ShelfItem[INITIAL_SIZE * 2];
            }
        }
        throw new IllegalStateException("");
    }

    /**
     * Cerca se è presente un oggetto sulla mensola. La ricerca utilizza il
     * metodo equals della classe dell'oggetto.
     *
     * @param i un oggetto per cercare sulla mensola un oggetto uguale a i
     * @return null se sulla mensola non c'è nessun oggetto uguale a i,
     * altrimenti l'oggetto x che si trova sulla mensola tale che
     * i.equals(x) == true
     * @throws NullPointerException se l'oggetto passato è null
     */
    public ShelfItem search(ShelfItem i) {
        // TODO implementare
        if (i == null) {
            throw new NullPointerException("L'oggetto passato è nullo");
        }
        // ciclo per ricercare l'oggetto i
        for (int j = 0; j < items.length; j++) {
            if (items[j].equals(i)) {
                // ritorno l'oggetto della mensola
                return items[j];
            }
        }
        // se i non è stato trovato ritorna null
        return null;
    }

    /**
     * @return il numero attuale di oggetti appoggiati sulla mensola
     */
    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    /*
     * protected, per solo scopo di JUnit testing
     */
    protected ShelfItem[] getItems() {
        return this.items;
    }

    /**
     * @return the currentTotalWeight
     */
    public double getCurrentTotalWeight() {
        // TODO implementare
        double totalCurrentWeight = 0;
        for (int i = 0; i < items.length; i++) {
            totalCurrentWeight += items[i].getWeight();
        }
        return totalCurrentWeight;
    }

    /**
     * @return the currentTotalOccupiedSurface
     */
    public double getCurrentTotalOccupiedSurface() {
        // TODO implementare
        double totalCurrentOccupiedSurface = 0;
        for (int i = 0; i < items.length; i++) {
            totalCurrentOccupiedSurface += items[i].getOccupiedSurface();
        }
        return totalCurrentOccupiedSurface;
    }

    /**
     * @return the maxLength
     */
    public double getMaxLength() {
        return maxLength;
    }

    /**
     * @return the maxWidth
     */
    public double getMaxWidth() {
        return maxWidth;
    }

    /**
     * @return the maxOccupableSurface
     */
    public double getMaxOccupableSurface() {
        return maxOccupableSurface;
    }

    /**
     * @return the maxTotalWeight
     */
    public double getMaxTotalWeight() {
        return maxTotalWeight;
    }

    // TODO inserire eventuali metodi accessori privati per fini di
    // implementazione

}
