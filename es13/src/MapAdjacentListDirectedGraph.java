/**
 *
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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
 * @author Luca Tesei
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
        return this.adjacentLists.size();
    }

    @Override
    public int edgeCount() {
        int result = 0;
        // Prendo tutti i nodi e conto il numero di archi per ognuno
        Set<GraphNode<L>> nodes = this.adjacentLists.keySet();
        for (GraphNode<L> n : nodes)
            result += this.adjacentLists.get(n).size();
        return result;
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
        if (node == null) throw new NullPointerException("Tentativo di aggiungere un nodo null");
        if (this.adjacentLists.containsKey(node))
            // il nodo è già presente
            return false;
        // inserisco il nodo associando un insieme vuoto di archi uscenti
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
        if (node == null) throw new NullPointerException("Tentativo di cercare un nodo null");
        return this.adjacentLists.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null) throw new NullPointerException("Tentativo di cercare un nodo con etichetta null");
        Set<GraphNode<L>> nodes = this.adjacentLists.keySet();
        for (GraphNode<L> n : nodes)
            if (n.getLabel().equals(label)) return n;
        // non esiste nessun nodo con etichetta uguale a label
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
        if (node == null) throw new NullPointerException("Tentativo di ottenere i nodi adiacenti di un nodo null");
        if (!this.containsNode(node))
            throw new IllegalArgumentException("Tentativo di ottenere i nodi adiacenti di un nodo non esistente");
        // Trovo gli archi adiacenti uscenti
        Set<GraphEdge<L>> edges = this.adjacentLists.get(node);
        // Inizializzo il risultato
        Set<GraphNode<L>> result = new HashSet<GraphNode<L>>();
        // Aggiungo al risultato i nodi collegati dagli archi adiacenti
        for (GraphEdge<L> e : edges)
            result.add(e.getNode2());
        return result;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Tentativo di ottenere i nodi predecessori di un nodo null");
        // controllo se il nodo esiste
        if (!this.adjacentLists.containsKey(node))
            throw new IllegalArgumentException("Richiesta dei predecessori di un nodo non esistente");
        // creo l'insieme risultato
        Set<GraphNode<L>> result = new HashSet<GraphNode<L>>();
        // cerco tutti gli archi che entrano in node e inserisco i nodi sorgente
        // nel risultato
        Set<GraphEdge<L>> inEdges = this.getIngoingEdgesOf(node);
        for (GraphEdge<L> e : inEdges)
            result.add(e.getNode1());
        return result;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // Scorro tutti i nodi e aggiungo nell'insieme risultato i relativi
        // archi
        Set<GraphEdge<L>> result = new HashSet<GraphEdge<L>>();
        Set<GraphNode<L>> nodes = this.adjacentLists.keySet();
        for (GraphNode<L> n : nodes)
            result.addAll(this.adjacentLists.get(n));
        return result;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Tentativo di inserire arco nullo");
        if (!edge.isDirected())
            throw new IllegalArgumentException("Inserimento di un arco non orientato in un grafo orientato");
        // Controllo se i nodi esistono
        if (!this.adjacentLists.containsKey(edge.getNode1()) || !this.adjacentLists.containsKey(edge.getNode2()))
            throw new IllegalArgumentException("Inserimento di un arco con almeno uno dei due nodi collegati non esistente");
        // Inserisco l'arco nella lista di adiacenza del nodo sorgente
        return this.adjacentLists.get(edge.getNode1()).add(edge);
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException("Rimozione degli archi non supportata");
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Tentativo di cercare un arco nullo");
        // Controllo se i nodi esistono
        if (!this.adjacentLists.containsKey(edge.getNode1()) || !this.adjacentLists.containsKey(edge.getNode2()))
            throw new IllegalArgumentException("Ricerca di un arco con almeno uno dei due nodi collegati non esistente");
        // Cerco l'arco nella lista di adiacenza del nodo sorgente
        return this.adjacentLists.get(edge.getNode1()).contains(edge);
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Tentativo di ottenere gli archi uscenti da un nodo null");
        Set<GraphEdge<L>> edges = this.adjacentLists.get(node);
        if (edges == null) throw new IllegalArgumentException("Richiesta degli archi uscenti di un nodo non esistente");
        return edges;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Tentativo di ottenere gli archi entranti in un nodo null");
        // controllo se il nodo esiste
        if (!this.adjacentLists.containsKey(node))
            throw new IllegalArgumentException("Richiesta degli archi entranti di un nodo non esistente");
        // creo l'insieme risultato
        Set<GraphEdge<L>> result = new HashSet<GraphEdge<L>>();
        // cerco fra tutti gli archi quelli che entrano in node e li inserisco
        // nel risultato
        Set<GraphEdge<L>> allEdges = this.getEdges();
        for (GraphEdge<L> e : allEdges)
            if (e.getNode2().equals(node)) result.add(e);
        return result;
    }

}
