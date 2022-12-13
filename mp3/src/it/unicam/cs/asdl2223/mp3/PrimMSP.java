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

        // Inizializza la coda di priorità con una coda vuota
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
            throw new IllegalArgumentException("Grafo non valido");
        }
    }

}
