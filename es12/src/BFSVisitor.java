import java.util.LinkedList;

/**
 * Classe singoletto che fornisce lo schema generico di visita Breadth-First di
 * un grafo rappresentato da un oggetto di tipo Graph<L>.
 *
 * @param <L> le etichette dei nodi del grafo
 * @author Luca Tesei
 */
public class BFSVisitor<L> {

    /**
     * Esegue la visita in ampiezza di un certo grafo a partire da un nodo
     * sorgente. Setta i valori seguenti valori associati ai nodi: distanza
     * intera, predecessore. La distanza indica il numero minimo di archi che si
     * devono percorrere dal nodo sorgente per raggiungere il nodo e il
     * predecessore rappresenta il padre del nodo in un albero di copertura del
     * grafo. Ogni volta che un nodo viene visitato viene eseguito il metodo
     * visitNode sul nodo. In questa classe il metodo non fa niente, basta
     * creare una sottoclasse e ridefinire il metodo per eseguire azioni
     * particolari.
     *
     * @param g      il grafo da visitare.
     * @param source il nodo sorgente.
     * @throws NullPointerException     se almeno un valore passato è null
     * @throws IllegalArgumentException se il nodo sorgente non appartiene
     *                                  al grafo dato
     */
    public void BFSVisit(Graph<L> g, GraphNode<L> source) {
        if (g == null) throw new NullPointerException("BFS ERROR: Grafo nullo");
        if (source == null) throw new NullPointerException("BFS ERROR: Sorgente nulla");
        if (!g.getNodes().contains(source))
            throw new IllegalArgumentException("BFS ERROR: La sorgente non è un nodo del grafo");

        // Inizializziamo il grafo
        for (GraphNode<L> n : g.getNodes()) {
            n.setColor(GraphNode.COLOR_WHITE);
            n.setIntegerDistance(-1);
            n.setPrevious(null);
        }
        // Scopro la sorgente
        source.setColor(GraphNode.COLOR_GREY);
        source.setIntegerDistance(0);
        source.setPrevious(null);
        // Come coda utilizzo una LinkedList: uso addLast per inserire in coda e
        // removeFirst per estrarre il primo elemento
        LinkedList<GraphNode<L>> queue = new LinkedList<GraphNode<L>>();
        // Inserisco in coda la sorgente
        queue.addLast(source);
        // Ciclo Principale
        while (!queue.isEmpty()) {
            GraphNode<L> nodoCorrente = queue.removeFirst();
            for (GraphNode<L> n : g.getAdjacentNodesOf(nodoCorrente)) {
                // Scopro tutti i nodi bianchi adiacenti al nodo corrente
                if (n.getColor() == GraphNode.COLOR_WHITE) {
                    // Faccio tutte le operazioni relative alla scoperta
                    n.setColor(GraphNode.COLOR_GREY);
                    n.setIntegerDistance(nodoCorrente.getIntegerDistance() + 1);
                    n.setPrevious(nodoCorrente);
                    queue.addLast(n);
                }
            }
            // Il nodo corrente diventa nero
            nodoCorrente.setColor(GraphNode.COLOR_BLACK);
            this.visitNode(nodoCorrente);
        }
        // la visita è finita
    }

    /**
     * Questo metodo, che di default non fa niente, viene chiamato su tutti i
     * nodi visitati durante la BFS quando i nodi passano da grigio a nero.
     * Ridefinire il metodo in una sottoclasse per effettuare azioni specifiche.
     *
     * @param n il nodo visitato
     */
    public void visitNode(GraphNode<L> n) {
        /*
         * In questa classe questo metodo non fa niente. Esso può essere
         * ridefinito in una sottoclasse per fare azioni particolari.
         */
    }

}
