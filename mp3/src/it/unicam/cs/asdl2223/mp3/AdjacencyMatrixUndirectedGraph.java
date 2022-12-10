/**
 * 
 */
package it.unicam.cs.asdl2223.mp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO completare gli import necessari

// ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * 
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * 
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 * 
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase di inserimento.
 * 
 * @author Luca Tesei (template) **INSERIRE NOME, COGNOME ED EMAIL
 *         xxxx@studenti.unicam.it DELLO STUDENTE** (implementazione)
 *
 * 
 */
public class AdjacencyMatrixUndirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    /*
     * Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
     * matrice di adiacenza
     */
    protected Map<GraphNode<L>, Integer> nodesIndex;

    /*
     * Matrice di adiacenza, gli elementi sono null o oggetti della classe
     * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
     * dimensione gradualmente ad ogni inserimento di un nuovo nodo e di
     * ridimensionarsi se un nodo viene cancellato.
     */
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixUndirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() {
        // TODO implementare
        return -1;
    }

    @Override
    public int edgeCount() {
        // TODO implementare
        return -1;
    }

    @Override
    public void clear() {
        // TODO implementare
    }

    @Override
    public boolean isDirected() {
        // TODO implementare
        return false;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(GraphNode<L> node) {
        // TODO implementare
        return false;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(L label) {
        // TODO implementare
        return false;
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(GraphNode<L> node) {
        // TODO implementare
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(L label) {
        // TODO implementare
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) {
        // TODO implementare
    }

    @Override
    public GraphNode<L> getNode(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphNode<L> getNode(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphNode<L> getNode(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public int getNodeIndexOf(GraphNode<L> node) {
        // TODO implementare
        return -1;
    }

    @Override
    public int getNodeIndexOf(L label) {
        // TODO implementare
        return -1;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        // TODO implementare
        return null;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(GraphNode<L> node1, GraphNode<L> node2,
            double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(L label1, L label2) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(L label1, L label2, double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(int i, int j) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public void removeEdge(GraphEdge<L> edge) {
        // TODO implementare
    }

    @Override
    public void removeEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
    }

    @Override
    public void removeEdge(L label1, L label2) {
        // TODO implementare
    }

    @Override
    public void removeEdge(int i, int j) {
        // TODO implementare
    }

    @Override
    public GraphEdge<L> getEdge(GraphEdge<L> edge) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(int i, int j) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(L label) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(int i) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(L label) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // TODO implementare
        return null;
    }
}
