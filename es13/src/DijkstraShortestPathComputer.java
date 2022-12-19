import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe che implementa l'algoritmo di Dijkstra per il calcolo dei cammini
 * minimi da una sorgente singola. L'algoritmo usa una coda con priorità
 * inefficiente (implementata con una List) che per estrarre il minimo impiega
 * O(n).
 *
 * @param <L> le etichette dei nodi del grafo
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class DijkstraShortestPathComputer<L> implements SingleSourceShortestPathComputer<L> {

    // il grafo su cui opera questo oggetto
    private final Graph<L> grafo;
    // ultima sorgente su cui sono stati calcolati i cammini minimi
    private GraphNode<L> lastSource;
    // flag che indica se i cammini minimi sono stati calcolati almeno una volta
    private boolean isComputed = false;

    /*
     * Contiene i nodi ancora da analizzare, la coda con priorità viene gestita
     * tramite lista e l'elemento minimo viene cercato e rimosso con costo O(n)
     */
    private List<GraphNode<L>> queue;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * diretto e pesato privo di pesi negativi.
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}
     * @throws IllegalArgumentException se il grafo passato contiene almeno
     *                                  un peso negativo
     */
    public DijkstraShortestPathComputer(Graph<L> graph) {
        this.grafo = graph;

        // controllo se il grafo è nullo
        if (graph == null) {
            throw new NullPointerException("Il grafo passato è nullo");
        }

        // controllo se almeno un peso è negativo
        for (GraphEdge<L> edges : graph.getEdges()) {
            if (edges.getWeight() < 0) {
                throw new IllegalArgumentException("Il grafo passato contiene almeno un peso negativo");
            }
        }

        // inizializzo la coda con priorità
        queue = new ArrayList<>();
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {

        // controllo se la sorgente è nullo
        if (sourceNode == null) {
            throw new NullPointerException("La sorgente è nullo");
        }

        // controllo se la sorgente è già stata utilizzata
        if (lastSource != null && lastSource.equals(sourceNode)) {
            return;
        }

        // inizializzo il grafo
        for (GraphNode<L> node : grafo.getNodes()) {
            node.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            node.setPrevious(null);
        }

        // inizializzo la coda con priorità
        queue = new ArrayList<>();

        // inizializzo la sorgente
        sourceNode.setFloatingPointDistance(0);
        queue.add(sourceNode);

        // inizializzo la lista dei nodi visitati
        List<GraphNode<L>> visited = new ArrayList<>();

        // ciclo finchè la coda non è vuota
        while (!queue.isEmpty()) {
            GraphNode<L> u = extractMin();
            visited.add(u);
            for (GraphEdge<L> edge : grafo.getEdgesOf(u)) {
                GraphNode<L> v = edge.getNode1().equals(u) ? edge.getNode2() : edge.getNode1();
                if (!visited.contains(v)) {
                    double alt = u.getFloatingPointDistance() + edge.getWeight();
                    if (alt < v.getFloatingPointDistance()) {
                        v.setFloatingPointDistance(alt);
                        v.setPrevious(u);
                        queue.add(v);
                    }
                }
            }
        }

        // setto la sorgente
        isComputed = true;
        lastSource = sourceNode;
    }

    private GraphNode<L> extractMin() {
        // TODO implementare
        GraphNode<L> min = queue.get(0);
        for (GraphNode<L> node : queue) {
            if (node.getFloatingPointDistance() < min.getFloatingPointDistance()) {
                min = node;
            }
        }
        queue.remove(min);
        return min;
    }

    @Override
    public boolean isComputed() {
        return this.isComputed;
    }

    @Override
    public GraphNode<L> getLastSource() {
        if (!this.isComputed)
            throw new IllegalStateException("Richiesta last source, ma non " + "sono mai stati calcolati i cammini minimi");
        return this.lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.grafo;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {

        // controllo se il nodo è nullo
        if (targetNode == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // controllo se il nodo è presente nel grafo
        if (!grafo.getNodes().contains(targetNode)) {
            throw new IllegalArgumentException("Il nodo non è presente nel grafo");
        }

        // controllo se i cammini minimi sono stati calcolati
        if (!isComputed) {
            throw new IllegalStateException("I cammini minimi non sono stati calcolati");
        }

        // controllo se il nodo è raggiungibile dalla sorgente
        if (targetNode.getFloatingPointDistance() == Double.POSITIVE_INFINITY) {
            return null;
        }

        // creo la lista dei nodi
        List<GraphEdge<L>> path = new ArrayList<>();

        // creo il nodo corrente
        GraphNode<L> current = targetNode;

        // ciclo finchè il nodo corrente non è la sorgente
        while (!current.equals(lastSource)) {
            GraphNode<L> previous = current.getPrevious();
            for (GraphEdge<L> edge : grafo.getEdgesOf(current)) {
                if (edge.getNode1().equals(previous) || edge.getNode2().equals(previous)) {
                    path.add(edge);
                    break;
                }
            }
            current = previous;
        }

        // inverto la lista
        Collections.reverse(path);

        return path;
    }
}
