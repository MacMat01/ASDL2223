package it.unicam.cs.asdl2223.mp3;

//TODO completare gli import necessari

//ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

import java.util.ArrayList;
import java.util.List;

/**
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * <p>
 * L'algoritmo richiede l'uso di una coda di min priorità tra i nodi che può
 * essere realizzata con una semplice ArrayList (non c'è bisogno di ottimizzare
 * le operazioni d'inserimento, di estrazione del minimo, o di decremento della
 * priorità).
 * <p>
 * Si possono usare i colori dei nodi per registrare la scoperta e la visita
 * effettuata dei nodi.
 *
 * @param <L> tipo delle etichette dei nodi del grafo
 * @author Luca Tesei (template) **Matteo Machella
 * matteo.machella@studenti.unicam.it** (implementazione)
 */
public class PrimMSP<L> {

    // TODO inserire le variabili istanza che si ritengono necessarie

    /*
     * In particolare: si deve usare una coda con priorità che può semplicemente
     * essere realizzata con una List<GraphNode<L>> e si deve mantenere un
     * insieme dei nodi già visitati
     */

    // Lista dei nodi del grafo
    private List<GraphNode<L>> coda;

    // Lista dei nodi visitati
    private List<GraphNode<L>> visitati;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        // TODO implementare

        // Inizializza la coda di priorità con una coda vuota e la lista dei nodi visitati
        this.coda = new ArrayList<GraphNode<L>>();
        this.visitati = new ArrayList<GraphNode<L>>();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non
     * negativi. Dopo l'esecuzione del metodo nei nodi del grafo il campo
     * previous deve contenere un puntatore a un nodo in accordo all'albero di
     * copertura minimo calcolato, la cui radice è il nodo sorgente passato.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @param s Il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *          dell'albero di copertura minimo. Tale nodo sarà la radice
     *          dell'albero di copertura trovato
     * @throws NullPointerException     se il grafo g o il nodo sorgente s sono nulli
     * @throws IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throws IllegalArgumentException se il grafo g è orientato, non pesato o
     *                                  con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        // TODO implementare

        // controllo che il grafo o il nodo sorgente non siano null
        if (g == null || s == null) {
            throw new NullPointerException("Grafo o nodo sorgente nulli");
        }

        // controllo se il grafo è orientato
        if (g.isDirected()) {
            throw new IllegalArgumentException("Il grafo non deve essere orientato");
        }

        // controllo se il grafo ha archi
        if (g.getEdges().isEmpty()) {
            throw new IllegalArgumentException("Il grafo non ha archi");
        }

        // controllo se gli archi del grafo sono pesati
        for (GraphEdge<L> edge : g.getEdges()) {
            if (!edge.hasWeight() || edge.getWeight() < 0) {
                throw new IllegalArgumentException("Il grafo deve essere pesato e con pesi positivi");
            }
        }

        // per ogni nodo del grafo, setto il campo previous a null
        for (GraphNode<L> node : g.getNodes()) {

            // imposto la priorità iniziale a infinito
            node.setFloatingPointDistance(Integer.MAX_VALUE);

            // setto il campo previous a null
            node.setPrevious(null);
        }

        // setto la priorità del nodo sorgente a 0
        s.setFloatingPointDistance(0);

        // aggiungo il nodo sorgente alla coda
        this.coda = new ArrayList<>(g.getNodes());

        // finché la coda non è vuota
        while (!coda.isEmpty()) {

            // estraggo il nodo con chiave minima dalla coda
            GraphNode<L> u = extractMin();

            // aggiungo il nodo estratto alla lista dei nodi visitati
            visitati.add(u);

            // per ogni nodo adiacente a u
            for (GraphNode<L> v : g.getAdjacentNodesOf(u)) {
                if (coda.contains(v) && g.getEdge(u, v).getWeight() < v.getFloatingPointDistance()) {
                    // se il nodo è nella coda e la sua chiave è maggiore del peso dell'arco tra u e il nodo adiacente
                    // setto il campo previous del nodo adiacente a u
                    v.setPrevious(u);
                    // imposto la posizione 0 della coda con il nodo adiacente
                    v.setFloatingPointDistance(g.getEdge(u, v).getWeight());
                }
            }
        }
    }

    private GraphNode<L> extractMin() {

        // inizializzo il nodo con chiave minima
        GraphNode<L> min = coda.get(0);

        // scorro la coda
        for (GraphNode<L> node : coda) {
            // se il nodo ha una chiave minore di min
            if (node.getFloatingPointDistance() < min.getFloatingPointDistance()) {
                // setto min al nodo
                min = node;
            }
        }

        // rimuovo il nodo con chiave minima dalla coda
        coda.remove(min);

        // ritorno il nodo con chiave minima
        return min;
    }
}


