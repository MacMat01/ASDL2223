/**
 *
 */

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * orientato. Per la rappresentazione viene usata una variante della
 * rappresentazione a liste di adiacenza. A differenza della rappresentazione
 * standard si usano strutture dati più efficienti per quanto riguarda la
 * complessità in tempo della ricerca se un nodo è presente (pseudocostante, con
 * tabella hash) e se un arco è presente (pseudocostante, con tabella hash). Lo
 * spazio occupato per la rappresentazione risultà tuttavia più grande di quello
 * che servirebbe con la rappresentazione standard.
 * <p>
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è il set dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi uscenti dal nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presenta basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 * <p>
 * Questa classe non supporta le operazioni di rimozione di nodi e archi e le
 * operazioni indicizzate di ricerca di nodi e archi.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class MapAdjacentListDirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi uscenti. Nel caso in cui un nodo non
     * abbia archi uscenti è associato con un insieme vuoto.
     */
    private final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListDirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    @Override
    public int nodeCount() {
        if (this.adjacentLists.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (GraphNode<L> node : adjacentLists.keySet()) {
            count++;
        }
        return count;
    }

    @Override
    public int edgeCount() {
        if (this.adjacentLists.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (GraphNode<L> node : adjacentLists.keySet()) {
            count += adjacentLists.get(node).size();
        }
        return count;
    }

    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi orientati
        return true;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.adjacentLists.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (this.adjacentLists.containsKey(node)) {
            return false;
        }
        this.adjacentLists.put(node, new HashSet<GraphEdge<L>>());
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Tentativo di rimuovere un nodo null");
        throw new UnsupportedOperationException("Rimozione dei nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        return this.adjacentLists.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null) {
            throw new NullPointerException();
        }
        /*
         * Per ogni nodo del grafo controlla se l'etichetta del nodo corrisponde
         * a quella cercata. Se si, restituisce il nodo corrente.
         */
        for (GraphNode<L> node : this.adjacentLists.keySet()) {
            if (node.getLabel().equals(label)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null) throw new NullPointerException("Tentativo di ricercare un nodo con etichetta null");
        throw new UnsupportedOperationException("Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException("Ricerca dei nodi con indice non supportata");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        /*
         * Per ogni arco uscente dal nodo, aggiunge il nodo di destinazione
         * nell'insieme da restituire.
         */
        Set<GraphNode<L>> adjacentNodes = new HashSet<GraphNode<L>>();
        for (GraphEdge<L> edge : this.adjacentLists.get(node)) {
            adjacentNodes.add(edge.getNode2());
        }
        return adjacentNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        /*
         * Per ogni nodo del grafo controlla se il nodo corrente è presente
         * nell'insieme degli archi uscenti del nodo corrente. Se si, aggiunge
         * il nodo corrente all'insieme dei nodi predecessori.
         */
        Set<GraphNode<L>> predecessorNodes = new HashSet<GraphNode<L>>();
        for (GraphNode<L> n : this.adjacentLists.keySet()) {
            for (GraphEdge<L> edge : this.adjacentLists.get(n)) {
                if (edge.getNode2().equals(node)) {
                    predecessorNodes.add(n);
                }
            }
        }
        return predecessorNodes;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        /*
         * Per ogni nodo del grafo, aggiunge tutti gli archi uscenti del nodo
         * nell'insieme da restituire.
         */
        Set<GraphEdge<L>> edges = new HashSet<GraphEdge<L>>();
        for (GraphNode<L> node : this.adjacentLists.keySet()) {
            edges.addAll(this.adjacentLists.get(node));
        }
        return edges;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(edge.getNode1())) {
            throw new IllegalArgumentException();
        }
        if (!this.adjacentLists.containsKey(edge.getNode2())) {
            throw new IllegalArgumentException();
        }
        if (!edge.isDirected()) {
            throw new IllegalArgumentException();
        }
        /*
         * Se l'arco è già presente, restituisce false.
         */
        if (this.adjacentLists.get(edge.getNode1()).contains(edge)) {
            return false;
        }
        this.adjacentLists.get(edge.getNode1()).add(edge);
        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException("Rimozione degli archi non supportata");
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(edge.getNode1()) || !this.adjacentLists.containsKey(edge.getNode2())) {
            throw new IllegalArgumentException();
        }
        return this.adjacentLists.get(edge.getNode1()).contains(edge);
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        return this.adjacentLists.get(node);
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (!this.adjacentLists.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        /*
         * Per ogni nodo del grafo controlla se il nodo corrente è presente
         * nell'insieme degli archi uscenti del nodo corrente. Se si, aggiunge
         * l'arco corrente all'insieme degli archi entranti del nodo.
         */
        Set<GraphEdge<L>> ingoingEdges = new HashSet<GraphEdge<L>>();
        for (GraphNode<L> n : this.adjacentLists.keySet()) {
            for (GraphEdge<L> edge : this.adjacentLists.get(n)) {
                if (edge.getNode2().equals(node)) {
                    ingoingEdges.add(edge);
                }
            }
        }
        return ingoingEdges;
    }
}
