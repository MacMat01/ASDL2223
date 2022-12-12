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


    // Indice per tenere conto del numero di archi presenti nel grafo
    private int edgeCount;

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

        // L'indice edgeCount viene aggiornato ogni volta che viene aggiunto un arco
        return this.edgeCount;
    }

    @Override
    public void clear() {
        // TODO implementare

        // Svuoto la matrice di adiacenza
        this.matrix.clear();

        // Svuoto la mappa dei nodi
        this.nodesIndex.clear();

        // Resetto l'indice degli archi
        this.edgeCount = 0;
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
        for (int i = 0; i < nodesIndex.size(); i++) {
            this.matrix.add(new ArrayList<>());
            for (int j = 0; j < nodesIndex.size(); j++) {
                this.matrix.get(i).add(null);
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

        // recupero l'indice del nodo da cancellare
        int index = this.nodesIndex.get(node);

        // rimuovo il nodo dalla mappa
        this.nodesIndex.remove(node);

        // decremento gli indici dei nodi successivi
        updateNodesIndex(index);
    }

    private void updateNodesIndex(int index) {
        // aggiorno gli indici dei nodi
        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(node) > index) {
                this.nodesIndex.put(node, this.nodesIndex.get(node) - 1);
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

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) {
        // TODO implementare

        // se l'indice è fuori dall'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("L'indice è negativo o maggiore della dimensione della matrice");
        }

        // creo un nuovo nodo
        GraphNode<L> node = null;

        // cerco il nodo con l'indice i
        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(n) == i) {
                node = n;
                break;
            }
        }

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

        // creo un nuovo nodo
        GraphNode<L> node = new GraphNode<>(label);

        // ritorno il nodo
        return getNode(node);
    }

    @Override
    public GraphNode<L> getNode(int i) {
        // TODO implementare

        // se l'indice è fuori dall'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("L'indice è negativo o maggiore della dimensione della matrice");
        }

        // creo un nuovo nodo
        GraphNode<L> node = null;

        // cerco il nodo con l'indice i
        for (GraphNode<L> n : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(n) == i) {
                node = n;
                break;
            }
        }

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
        GraphNode<L> node1 = edge.getNode1();
        GraphNode<L> node2 = edge.getNode2();
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei nodi dell'arco non esiste in questo grafo");
        }

        // se l'arco è già presente ritorno false
        if (getEdge(edge) != null) {
            return false;
        }

        // aggiungo l'arco
        int node1Index = this.nodesIndex.get(edge.getNode1());
        int node2Index = this.nodesIndex.get(edge.getNode2());
        this.matrix.get(node1Index).set(node2Index, edge);
        this.matrix.get(node2Index).set(node1Index, edge);
        this.edgeCount++;

        // ritorno true
        return true;
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
        return addEdge(edge);
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
        return addEdge(edge);
    }

    @Override
    public boolean addEdge(L label1, L label2) {
        // TODO implementare

        // se uno dei label è null lancio NullPointerException
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

        // costruisco i nodi
        GraphNode<L> node1 = new GraphNode<>(label1);
        GraphNode<L> node2 = new GraphNode<>(label2);

        // se uno dei label non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei label non esiste in questo grafo");
        }

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);

        // ritorno il risultato di addEdge
        return addEdge(edge);
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
        return addEdge(edge);
    }

    @Override
    public boolean addEdge(int i, int j) {
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

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);

        // ritorno il risultato di addEdge
        return addEdge(edge);
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) {
        // TODO implementare

        // se i o j sono fuori dai limiti dell'intervallo lancio IndexOutOfBoundsException
        if (i < 0 || i > this.nodeCount() - 1 || j < 0 || j > this.nodeCount() - 1) {
            throw new IndexOutOfBoundsException("i o j sono fuori dai limiti dell'intervallo");
        }

        // se il peso è negativo lancio IllegalArgumentException
        if (weight < 0) {
            throw new IllegalArgumentException("Il peso è negativo");
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

        // creo un nuovo arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false, weight);

        // ritorno il risultato di addEdge
        return addEdge(edge);
    }

    @Override
    public void removeEdge(GraphEdge<L> edge) {
        // se l'arco è null lancio NullPointerException
        if (edge == null) {
            throw new NullPointerException("L'arco è nullo");
        }

        // creo i nodi
        GraphNode<L> node1 = edge.getNode1();
        GraphNode<L> node2 = edge.getNode2();

        // se l'arco non esiste in questo grafo lancio IllegalArgumentException
        if (getEdge(node1, node2) == null) {
            throw new IllegalArgumentException("L'arco non esiste in questo grafo");
        }

        // recupero gli indici dei nodi nella matrice di adiacenza
        int index1 = this.nodesIndex.get(node1);
        int index2 = this.nodesIndex.get(node2);

        // rimuovo l'arco dalle liste degli archi dei nodi
        this.matrix.get(index1).remove(edge);
        this.matrix.get(index2).remove(edge);
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

    @Override
    public void removeEdge(L label1, L label2) {
        // TODO implementare

        // se uno dei label è null lancio NullPointerException
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

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

        // creo i nodi
        GraphNode<L> node1 = edge.getNode1();
        GraphNode<L> node2 = edge.getNode2();

        // se uno dei nodi non esiste in questo grafo lancio IllegalArgumentException
        if (!this.nodesIndex.containsKey(node1) || !this.nodesIndex.containsKey(node2)) {
            throw new IllegalArgumentException("Uno dei nodi non esiste in questo grafo");
        }

        // recupero gli indici dei nodi nella matrice di adiacenza
        int index1 = this.nodesIndex.get(node1);
        int index2 = this.nodesIndex.get(node2);

        // se l'arco non esiste in questo grafo ritorno null
        if (!this.matrix.get(index1).contains(edge) || !this.matrix.get(index2).contains(edge)) {
            return null;
        }

        // ritorno l'arco
        return this.matrix.get(index1).get(index2);
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

        // creo l'arco
        GraphEdge<L> edge = new GraphEdge<>(node1, node2, false);

        // ritorno l'arco
        return getEdge(edge);
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) {
        // TODO implementare

        // se uno dei label è null lancio NullPointerException
        if (label1 == null || label2 == null) {
            throw new NullPointerException("Uno dei label è nullo");
        }

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

        // ritorno l'arco
        return getEdge(node1, node2);
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

        // recupero l'indice del nodo nella matrice di adiacenza
        int index = this.nodesIndex.get(node);

        // scorro la riga della matrice di adiacenza
        for (int i = 0; i < this.nodeCount(); i++) {
            // se l'arco esiste aggiungo il nodo adiacente al set
            if (this.matrix.get(index).get(i) != null) {
                adjacentNodes.add(this.matrix.get(index).get(i).getNode2());
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

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
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
        for (int i = 0; i < this.nodeCount(); i++) {
            // se l'arco esiste lo aggiungo al set
            if (this.matrix.get(index).get(i) != null) {
                edges.add(this.matrix.get(index).get(i));
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
