import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe singoletto che fornisce lo schema generico di visita Breadth-First di
 * un grafo rappresentato da un oggetto di tipo Graph<L>.
 *
 * @param <L> le etichette dei nodi del grafo
 * @author Template: Luca Tesei, Implementazione: collettiva
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
        //TODO implementare
        // NOTA: chiamare il metodo visitNode quando un nodo passa da grigio a nero
        if (g == null || source == null) {
            throw new NullPointerException();
        }
        if (!g.containsNode(source)) {
            throw new IllegalArgumentException();
        }
        /*
         * Inizializza il grafo e chiama la recBFS sui nodi in un ordine
         * qualsiasi per calcolare la "foresta" BFS
         */
        for (GraphNode<L> n : g.getNodes()) {
            n.setColor(GraphNode.COLOR_WHITE);
            n.setIntegerDistance(Integer.MAX_VALUE);
            n.setPrevious(null);
        }
        /*
         * Inizializza il nodo sorgente
         */
        source.setColor(GraphNode.COLOR_GREY);
        source.setIntegerDistance(0);
        source.setPrevious(null);
        /*
         * Coda dei nodi da visitare
         */
        Queue<GraphNode<L>> q = new LinkedList<>();
        q.add(source);
        /*
         * Visita in ampiezza
         */
        while (!q.isEmpty()) {
            /*
             * Estrae il primo nodo dalla coda
             */
            GraphNode<L> u = q.remove();
            /*
             * Visita tutti i nodi adiacenti a u
             */
            for (GraphNode<L> v : g.getAdjacentNodesOf(u)) {
                /*
                 * Se il nodo v non è stato visitato lo aggiunge alla coda
                 */
                if (v.getColor() == GraphNode.COLOR_WHITE) {
                    v.setColor(GraphNode.COLOR_GREY);
                    /*
                     * La distanza del nodo v è la distanza del nodo u più 1
                     */
                    v.setIntegerDistance(u.getIntegerDistance() + 1);
                    v.setPrevious(u);
                    q.add(v);
                }
            }
            u.setColor(GraphNode.COLOR_BLACK);
            visitNode(u);
        }
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
