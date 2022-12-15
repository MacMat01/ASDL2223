/**
 *
 */
package it.unicam.cs.asdl2223.mp3;

import java.util.*;

// TODO completare gli import necessari

// ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * <p>
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * a ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * <p>
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa a ogni nodo l'indice assegnato in fase d'inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * <p>
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 * <p>
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase d'inserimento.
 *
 * @author Luca Tesei (template), Matteo Machella (implementazione)
 * matteo.machella@studenti.unicam.it
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
     * Matrice di adiacenza, gli elementi sono null od oggetti della classe
     * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
     * dimensione gradualmente a ogni inserimento di un nuovo nodo e di
     * ridimensionarsi se un nodo viene cancellato.
     */
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixUndirectedGraph() {
        this.matrix = new ArrayList<>();
        this.nodesIndex = new HashMap<>();
    }

    @Override
    public int nodeCount() {
        // TODO implementare

        return this.nodesIndex.size();
    }

    @Override
    public int edgeCount() {
        // TODO implementare

        return getEdges().size();
    }

    @Override
    public void clear() {
        // TODO implementare

        // Svuoto la matrice di adiacenza
        this.matrix.clear();

        // Svuoto la mappa dei nodi
        this.nodesIndex.clear();
    }

    @Override
    public boolean isDirected() {
        // TODO implementare

        // un grafo non orientato non ha archi orientati
        return false;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine d'inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null lancio NullPointerException
        if (node == null) {
            throw new NullPointerException("Il nodo non può essere null");
        }

        // se il nodo è già presente ritorno false
        if (this.nodesIndex.containsKey(node)) {
            return false;
        }

        // aggiungo il nodo alla mappa
        this.nodesIndex.put(node, this.nodesIndex.size());

        // richiamo il metodo initMatrix
        this.initMatrix();

        return true;
    }

    // Inizializzo la matrice con il giusto numero di righe e colonne
    private void initMatrix() {
        // TODO implementare (macmat)

        // aggiungo una nuova riga alla matrice
        this.matrix.add(new ArrayList<>());

        // aggiungo una colonna null per ogni nodo già presente
        for (ArrayList<GraphEdge<L>> graphEdges : this.matrix) {
            for (int j = graphEdges.size(); j < nodesIndex.size(); j++) {
                graphEdges.add(null);
            }
        }
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine d'inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(L label) {
        // TODO implementare

        // se il label è null lancio NullPointerException
        if (label == null) {
            throw new NullPointerException("Il label non può essere null");
        }

        // creo un nuovo nodo
        GraphNode<L> node = new GraphNode<>(label);

        // aggiungo il nodo alla mappa
        return addNode(node);
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null o non è presente lancio un'eccezione
        if (node == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node)) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // cancello la riga corrispondente al nodo
        int index = this.nodesIndex.get(node);

        // cancello il nodo dalla mappa
        this.nodesIndex.remove(node);
        matrix.remove(matrix.get(index));

        // cancello la colonna corrispondente al nodo
        for (int i = 0; i < this.nodesIndex.size() - 1; i++) {
            this.matrix.get(i).remove(index);
        }

        // aggiorno gli indici dei nodi successivi
        for (GraphNode<L> graphNode : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(graphNode) > index) {
                this.nodesIndex.put(graphNode, this.nodesIndex.get(graphNode) - 1);
            }
        }
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(L label) {
        // TODO implementare

        // se il label è null lancio un'eccezione
        if (label == null) {
            throw new NullPointerException("Il label è nullo");
        }

        // creo un nuovo nodo
        GraphNode<L> node = new GraphNode<>(label);

        // se il label non esiste in nessun nodo di questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node)) {
            throw new IllegalArgumentException("Il label non esiste in nessun nodo di questo grafo");
        }

        // rimuovo il nodo
        removeNode(node);
    }

    // Metodo di comodo per eliminare le linee duplicate
    private void indexCheck(int i) {
        // se l'indice è fuori dall'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("L'indice è negativo o maggiore della dimensione della matrice");
        }
    }

    // Metodo di comodo per eliminare le linee duplicate
    private GraphNode<L> searchNode(int i) {

        // controllo che l'indice sia valido
        indexCheck(i);

        // creo un nuovo nodo
        GraphNode<L> node = null;

        // cerco il nodo con l'indice i
        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(n) == i) {
                node = n;
                break;
            }
        }

        return node;
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) {
        // TODO implementare

        // richiamo il metodo di comodo per cercare il nodo
        GraphNode<L> node = searchNode(i);

        // se l'indice passato non corrisponde a nessun nodo lancio IllegalArgumentException
        if (node == null) {
            throw new IllegalArgumentException("L'indice non corrisponde a nessun nodo");
        }

        // rimuovo il nodo
        removeNode(node);
    }

    @Override
    public GraphNode<L> getNode(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null lancio NullPointerException
        if (node == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // se il nodo non è presente ritorno null
        if (!this.nodesIndex.containsKey(node)) {
            return null;
        }

        // recupero l'indice del nodo
        int index = this.nodesIndex.get(node);

        // creo un nuovo nodo
        GraphNode<L> n = null;

        // cerco il nodo con l'indice index
        for (GraphNode<L> node1 : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(node1) == index) {
                n = node1;
                break;
            }
        }

        // ritorno il nodo
        return n;
    }

    @Override
    public GraphNode<L> getNode(L label) {
        // TODO implementare

        // se il label è null lancio NullPointerException
        if (label == null) {
            throw new NullPointerException("Il label è nullo");
        }

        // cerco il nodo e lo restituisco
        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (node.getLabel().equals(label)) {
                return node;
            }
        }

        // ritorno null se il nodo in questo grafo non esiste
        return null;
    }

    @Override
    public GraphNode<L> getNode(int i) {
        // TODO implementare

        // richiamo il metodo di comodo per controllare l'indice
        GraphNode<L> node = searchNode(i);

        // se l'indice passato non corrisponde a nessun nodo lancio IllegalArgumentException
        if (node == null) {
            throw new IllegalArgumentException("L'indice non corrisponde a nessun nodo");
        }

        // ritorno il nodo
        return node;
    }

    @Override
    public int getNodeIndexOf(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null lancio NullPointerException
        if (node == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node)) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // ritorno l'indice del nodo
        return this.nodesIndex.get(node);
    }

    @Override
    public int getNodeIndexOf(L label) {
        // TODO implementare

        // se il label è null lancio NullPointerException
        if (label == null) {
            throw new NullPointerException("Il label è nullo");
        }

        // se un nodo con questo label non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(new GraphNode<>(label))) {
            throw new IllegalArgumentException("Un nodo con questo label non esiste in questo grafo");
        }

        // creo un nuovo nodo
        GraphNode<L> node = new GraphNode<>(label);

        // ritorno l'indice del nodo
        return getNodeIndexOf(node);
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        // TODO implementare

        // ritorno il set dei nodi
        return this.nodesIndex.keySet();
    }

    private boolean edgeAdder(GraphEdge<L> edge) {
        if (this.matrix.get(this.nodesIndex.get(edge.getNode1())).get(this.nodesIndex.get(edge.getNode2())) != null) {
            return false;
        }
        if (this.matrix.get(this.nodesIndex.get(edge.getNode2())).get(this.nodesIndex.get(edge.getNode1())) != null) {
            return false;
        }

        matrix.get(this.nodesIndex.get(edge.getNode1())).set(this.nodesIndex.get(edge.getNode2()), edge);
        return true;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        // TODO implementare

        // se l'arco è null o l'arco già esiste ritorno false
        if (edge == null) {
            throw new NullPointerException("L'arco è nullo");
        }

        // se l'arco è diretto lancio IllegalArgumentException
        if (edge.isDirected()) {
            throw new IllegalArgumentException("L'arco è diretto quando non dovrebbe esserlo");
        }

        // controllo se i nodi dell'arco esistono
        if (!this.nodesIndex.containsKey(edge.getNode1()) || !this.nodesIndex.containsKey(edge.getNode2())) {
            throw new IllegalArgumentException("Uno dei nodi dell'arco non esiste in questo grafo");
        }


        return edgeAdder(edge);
    }

    @Override
    public boolean addEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare

        // se uno dei nodi è null lancio NullPointerException
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Uno dei nodi è nullo");
        }

        // se uno dei nodi non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei nodi non esiste in questo grafo");
        }

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);
        return edgeAdder(edge);
    }

    @Override
    public boolean addWeightedEdge(GraphNode<L> node1, GraphNode<L> node2, double weight) {
        // TODO implementare

        // se uno dei nodi è null lancio NullPointerException
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Uno dei nodi è nullo");
        }

        // se uno dei nodi non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei nodi non esiste in questo grafo");
        }

        // se il peso è negativo lancio IllegalArgumentException
        if (weight < 0) {
            throw new IllegalArgumentException("Il peso è negativo");
        }

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false, weight);
        return edgeAdder(edge);
    }

    @Override
    public boolean addEdge(L label1, L label2) {
        // TODO implementare

        // se uno dei label è null lancio NullPointerException
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

        // se uno dei label non esiste in questo grafo lancio IllegalArgumentException
        if (getNode(label1) == null || getNode(label2) == null) {
            throw new IllegalArgumentException("Uno dei label non esiste in questo grafo");
        }

        // creo i nodi
        GraphNode<L> node1 = getNode(label1);
        GraphNode<L> node2 = getNode(label2);

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);

        // ritorno il risultato di addEdge
        return edgeAdder(edge);
    }

    @Override
    public boolean addWeightedEdge(L label1, L label2, double weight) {
        // TODO implementare

        // se uno dei label è null lancio NullPointerException
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

        // se il peso è negativo lancio IllegalArgumentException
        if (weight < 0) {
            throw new IllegalArgumentException("Il peso è negativo");
        }

        // costruisco i nodi
        GraphNode<L> node1 = new GraphNode<>(label1);
        GraphNode<L> node2 = new GraphNode<>(label2);

        // se uno dei label non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei label non esiste in questo grafo");
        }

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false, weight);

        // ritorno il risultato di addEdge
        return edgeAdder(edge);
    }

    // Se i o j sono fuori dai limiti dell'intervallo lancio IndexOutOfBoundsException
    private void checkIndex(int i, int j) {
        // TODO implementare (macmat)

        if (i < 0 || i > this.nodeCount() - 1 || j < 0 || j > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("i o j sono fuori dai limiti dell'intervallo");
        }
    }

    // Cerco i nodi utilizzando gli indici
    private Set<GraphNode<L>> searchNodes(int i, int j) {
        // TODO implementare (macmat)

        // creo un set di nodi
        Set<GraphNode<L>> nodes = new HashSet<>();

        // cerco i nodi
        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(node) == i) {
                nodes.add(node);
            }

            if (this.nodesIndex.get(node) == j) {
                nodes.add(node);
            }

            // se ho trovato entrambi i nodi esco dal ciclo
            if (nodes.size() == 2) {
                break;
            }
        }
        return nodes;
    }

    // Se almeno uno degli indici non corrisponde a nessun nodo lancio IndexOutOfBoundsException
    private void checkNodes(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare (macmat)

        if (node1 == null || node2 == null) {
            throw new IndexOutOfBoundsException("Almeno uno degli indici non corrisponde a nessun nodo");
        }
    }

    @Override
    public boolean addEdge(int i, int j) {
        // TODO implementare

        // controllo gli indici richiamando il metodo di comodo
        checkIndex(i, j);

        // richiamo il metodo di comodo per cercare i nodi
        Set<GraphNode<L>> nodes = searchNodes(i, j);

        // creo i nodi
        GraphNode<L> node1 = nodes.iterator().next();
        GraphNode<L> node2 = nodes.iterator().next();

        checkNodes(node1, node2);

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);

        // ritorno il risultato di addEdge
        return edgeAdder(edge);
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) {
        // TODO implementare

        // controllo gli indici richiamando il metodo di comodo
        checkIndex(i, j);

        // se il peso è negativo lancio IllegalArgumentException
        if (weight < 0) {
            throw new IllegalArgumentException("Il peso non può è negativo");
        }

        // richiamo il metodo di comodo per cercare i nodi
        Set<GraphNode<L>> nodes = searchNodes(i, j);

        // creo i nodi
        GraphNode<L> node1 = nodes.iterator().next();
        GraphNode<L> node2 = nodes.iterator().next();

        checkNodes(node1, node2);

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false, weight);

        // ritorno il risultato di addEdge
        return edgeAdder(edge);
    }

    @Override
    public void removeEdge(GraphEdge<L> edge) {
        // se l'arco è null lancio NullPointerException
        if (edge == null) {
            throw new NullPointerException("L'arco è nullo");
        }

        // se l'arco non esiste in questo grafo lancio IllegalArgumentException
        if (getEdge(edge.getNode1(), edge.getNode2()) == null) {
            throw new IllegalArgumentException("L'arco non esiste in questo grafo");
        }

        // recupero gli indici dei nodi nella matrice di adiacenza
        int index1 = this.nodesIndex.get(edge.getNode1());
        int index2 = this.nodesIndex.get(edge.getNode2());

        // rimuovo l'arco dalle liste degli archi dei nodi
        this.matrix.get(index1).set(index2, null);
        this.matrix.get(index2).set(index1, null);
    }

    @Override
    public void removeEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare

        // se uno dei nodi è null lancio NullPointerException
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Uno dei nodi è nullo");
        }

        // rimuovo l'arco
        removeEdge(new GraphEdge<>(node1, node2, false));
    }

    // Se i label sono nulli lancio NullPointerException
    private void checkLabels(L label1, L label2) {
        // TODO implementare (macmat)

        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }
    }

    @Override
    public void removeEdge(L label1, L label2) {
        // TODO implementare

        // Controllo che i label non siano nulli richiamando il metodo di comodo
        checkLabels(label1, label2);

        // creo i nodi
        GraphNode<L> node1 = null;
        GraphNode<L> node2 = null;

        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (node.getLabel().equals(label1)) {
                node1 = node;
            }
            if (node.getLabel().equals(label2)) {
                node2 = node;
            }

            // se ho trovato entrambi i nodi esco dal ciclo
            if (node1 != null && node2 != null) {
                break;
            }
        }

        // se uno dei nodi non esiste in questo grafo lancio IllegalArgumentException
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Uno dei nodi non esiste in questo grafo");
        }

        // rimuovo l'arco
        removeEdge(node1, node2);
    }

    @Override
    public void removeEdge(int i, int j) {
        // TODO implementare

        // controllo gli indici richiamando il metodo di comodo
        checkIndex(i, j);

        // creo i nodi
        GraphNode<L> node1 = null;
        GraphNode<L> node2 = null;

        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(node) == i) {
                node1 = node;
            }
            if (this.nodesIndex.get(node) == j) {
                node2 = node;
            }

            // se ho trovato entrambi i nodi esco dal ciclo
            if (node1 != null && node2 != null) {
                break;
            }
        }

        // se almeno uno degli indici non corrisponde a nessun nodo lancio IndexOutOfBoundsException
        if (node1 == null || node2 == null) {
            throw new IndexOutOfBoundsException("Almeno uno degli indici non corrisponde a nessun nodo");
        }

        // rimuovo l'arco
        removeEdge(node1, node2);
    }

    @Override
    public GraphEdge<L> getEdge(GraphEdge<L> edge) {
        // TODO implementare

        // se l'arco è null lancio NullPointerException
        if (edge == null) {
            throw new NullPointerException("L'arco è nullo");
        }

        // se almeno uno dei due nodi non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(edge.getNode1()) || !this.nodesIndex.containsKey(edge.getNode2())) {
            throw new IllegalArgumentException("Almeno uno dei due nodi non esiste in questo grafo");
        }

        // cerco l'arco nella matrice di adiacenza
        if (this.matrix.get(this.nodesIndex.get(edge.getNode1())).contains(edge)) {
            return edge;
        }

        // se l'arco non esiste in questo grafo ritorno null
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare

        // se uno dei nodi è null lancio NullPointerException
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Uno dei nodi è nullo");
        }

        // se uno dei nodi non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei nodi non esiste in questo grafo");
        }

        if (this.matrix.get(nodesIndex.get(node1)).get(nodesIndex.get(node2)) != null) {
            return this.matrix.get(nodesIndex.get(node1)).get(nodesIndex.get(node2));
        }

        if (this.matrix.get(nodesIndex.get(node2)).get(nodesIndex.get(node1)) != null) {
            return this.matrix.get(nodesIndex.get(node2)).get(nodesIndex.get(node1));
        }

        // cerco l'arco corrispondente ai due nodi
        return this.matrix.get(nodesIndex.get(node1)).get(nodesIndex.get(node2));
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) {
        // TODO implementare

        // controllo che il label siano nulli
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

        // se uno dei label non esiste in questo grafo lancio IllegalArgumentException
        if (getNode(label1) == null || getNode(label2) == null) {
            throw new IllegalArgumentException("Uno dei label non esiste in questo grafo");
        }

        // ritorno l'arco corrispondente ai due nodi
        int indexN1 = getNodeIndexOf(getNode(label1));
        int indexN2 = getNodeIndexOf(getNode(label2));

        if (matrix.get(indexN1).get(indexN2) != null) return matrix.get(indexN1).get(indexN2);
        if (matrix.get(indexN2).get(indexN1) != null) return matrix.get(indexN2).get(indexN1);

        // se non esiste ritorno null
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(int i, int j) {
        // TODO implementare

        // se i o j sono fuori dai limiti dell'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1 || j < 0 || j > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("i o j sono fuori dai limiti dell'intervallo");
        }

        // creo i nodi
        GraphNode<L> node1 = null;
        GraphNode<L> node2 = null;

        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(node) == i) {
                node1 = node;
            }
            if (this.nodesIndex.get(node) == j) {
                node2 = node;
            }

            // se ho trovato entrambi i nodi esco dal ciclo
            if (node1 != null && node2 != null) {
                break;
            }
        }

        // se almeno uno degli indici non corrisponde a nessun nodo lancio IndexOutOfBoundsException
        if (node1 == null || node2 == null) {
            throw new IndexOutOfBoundsException("Almeno uno degli indici non corrisponde a nessun nodo");
        }

        // ritorno l'arco
        return getEdge(node1, node2);
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null lancio NullPointerException
        if (node == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node)) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // creo il set di nodi adiacenti
        Set<GraphNode<L>> adjacentNodes = new HashSet<>();

        // scorro la riga corrispondente al nodo
        for (GraphEdge<L> edge : getEdgesOf(node)) {
            // se l'arco non è null aggiungo il nodo adiacente al set
            if (edge != null) {
                adjacentNodes.add(edge.getNode1().equals(node) ? edge.getNode2() : edge.getNode1());
            }
        }

        // scorro la colonna corrispondente al nodo
        for (ArrayList<GraphEdge<L>> edges : this.matrix) {
            // se l'arco non è null aggiungo il nodo adiacente al set
            if (edges.get(this.nodesIndex.get(node)) != null) {
                adjacentNodes.add(edges.get(this.nodesIndex.get(node)).getNode1().equals(node) ? edges.get(this.nodesIndex.get(node)).getNode2() : edges.get(this.nodesIndex.get(node)).getNode1());
            }
        }

        // ritorno il set di nodi adiacenti
        return adjacentNodes;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(L label) {
        // TODO implementare

        // se il label è null lancio NullPointerException
        if (label == null) {
            throw new NullPointerException("Il label è nullo");
        }

        // creo il nodo
        GraphNode<L> node = null;

        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (n.getLabel().equals(label)) {
                node = n;
                break;
            }
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (node == null) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // ritorno il set di nodi adiacenti
        return getAdjacentNodesOf(node);
    }

    // Uguale a checkIndex ma con un indice
    private void checkIndex(int index) {
        // TODO implementare (macmat)

        // se l'indice è fuori dai limiti dell'intervallo lancio IndexOutOfBoundsException
        if (index < 0 || index > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("L'indice è fuori dai limiti dell'intervallo");
        }
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
        // TODO implementare

        // Controllo che l'indice non sia fuori dai limiti dell'intervallo richiamando il metodo di comodo
        checkIndex(i);

        // creo il nodo
        GraphNode<L> node = null;

        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(n) == i) {
                node = n;
                break;
            }
        }

        // se il nodo non esiste in questo grafo lancio IndexOutOfBoundsException
        if (node == null) {
            throw new IndexOutOfBoundsException("i non corrisponde a nessun nodo");
        }

        // ritorno il set di nodi adiacenti
        return getAdjacentNodesOf(node);
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(L label) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(int i) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        // TODO implementare

        // se il nodo è null lancio NullPointerException
        if (node == null) {
            throw new NullPointerException("Il nodo è nullo");
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node)) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // creo il set di archi
        Set<GraphEdge<L>> edges = new HashSet<>();

        // recupero l'indice del nodo nella matrice di adiacenza
        int index = this.nodesIndex.get(node);

        // scorro la riga della matrice di adiacenza corrispondente al nodo
        for (GraphEdge<L> edge : this.matrix.get(index)) {
            // se l'arco esiste lo aggiungo al set
            if (edge != null) {
                edges.add(edge);
            }
        }

        // scorro la colonna della matrice di adiacenza corrispondente al nodo
        for (ArrayList<GraphEdge<L>> graphEdges : this.matrix) {
            // se l'arco esiste lo aggiungo al set
            if (graphEdges.get(index) != null) {
                edges.add(graphEdges.get(index));
            }
        }

        // ritorno il set di archi
        return edges;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(L label) {
        // TODO implementare

        // se il label è null lancio NullPointerException
        if (label == null) {
            throw new NullPointerException("Il label è nullo");
        }

        // creo il nodo
        GraphNode<L> node = null;

        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (n.getLabel().equals(label)) {
                node = n;
                break;
            }
        }

        // se il nodo non esiste in questo grafo lancio IllegalArgumentException
        if (node == null) {
            throw new IllegalArgumentException("Il nodo non esiste in questo grafo");
        }

        // ritorno il set di archi
        return getEdgesOf(node);
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(int i) {
        // TODO implementare

        // se i è fuori dai limiti dell'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("i è fuori dai limiti dell'intervallo");
        }

        // creo il nodo
        GraphNode<L> node = null;

        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(n) == i) {
                node = n;
                break;
            }
        }

        // se il nodo non esiste in questo grafo lancio IndexOutOfBoundsException
        if (node == null) {
            throw new IndexOutOfBoundsException("i non corrisponde a nessun nodo");
        }

        // ritorno il set di archi
        return getEdgesOf(node);
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(L label) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // TODO implementare

        // creo il set di archi
        Set<GraphEdge<L>> edges = new HashSet<>();

        // scorro la matrice di adiacenza
        for (int i = 0; i < this.nodeCount(); i++) {
            for (int j = 0; j < this.nodeCount(); j++) {
                // se l'arco esiste lo aggiungo al set
                if (this.matrix.get(i).get(j) != null) {
                    edges.add(this.matrix.get(i).get(j));
                }
            }
        }

        // ritorno il set di archi
        return edges;
    }
}