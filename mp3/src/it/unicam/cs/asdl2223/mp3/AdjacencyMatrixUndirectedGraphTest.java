package it.unicam.cs.asdl2223.mp3;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe AdjacencyMatrixUndirectedGraph.
 *
 * @author Luca Tesei
 */
class AdjacencyMatrixUndirectedGraphTest {

    @Test
    final void testAdjacencyMatrixUndirectedGraph() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testNodeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<String>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<String>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testSize() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.size());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(1, g.size());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertEquals(2, g.size());
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        assertEquals(3, g.size());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        assertEquals(4, g.size());
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertEquals(5, g.size());
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        assertEquals(6, g.size());
        g.addEdge(new GraphEdge<String>(nx, nu, false, 2.05));
        assertEquals(6, g.size());
        g.clear();
        assertEquals(0, g.size());
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testClear() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertFalse(g.isDirected());
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class, () -> g.addNode((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertNull(g.getNode(ns));
        g.addNode(ns);
        assertNotNull(g.getNode(nsTest));
        String lu = "u";
        String luTest = "u";
        assertNull(g.getNode(luTest));
        g.addNode(lu);
        assertNotNull(g.getNode(luTest));
    }

    @Test
    final void testRemoveNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.removeNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class, () -> g.removeNode((String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(0));
        g.addNode("a");
        g.addNode("b");
        g.addNode(new GraphNode<String>("c"));
        g.addNode("d");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("a", "a");
        g.addEdge("b", "d");
        g.addEdge("a", "d");
        g.addEdge("c", "d");
        assertEquals(0, g.getNodeIndexOf("a"));
        assertEquals(1, g.getNodeIndexOf("b"));
        assertEquals(2, g.getNodeIndexOf("c"));
        assertEquals(3, g.getNodeIndexOf("d"));
        assertEquals(4, g.nodeCount());
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("e"));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(4));
        g.removeNode("b");
        assertEquals(0, g.getNodeIndexOf("a"));
        assertEquals(1, g.getNodeIndexOf("c"));
        assertEquals(2, g.getNodeIndexOf("d"));
        assertEquals(3, g.nodeCount());
        assertNull(g.getNode("b"));
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertNotNull(g.getEdge("a", "a"));
        assertNotNull(g.getEdge("a", "d"));
        assertNotNull(g.getEdge("c", "d"));
        assertNull(g.getEdge("c", "a"));
        assertNull(g.getEdge("d", "d"));
        assertNull(g.getEdge("c", "c"));
        g.removeNode(0);
        assertEquals(0, g.getNodeIndexOf("c"));
        assertEquals(1, g.getNodeIndexOf("d"));
        assertEquals(2, g.nodeCount());
        assertNull(g.getNode("a"));
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertNotNull(g.getEdge("c", "d"));
        assertNull(g.getEdge("d", "d"));
        assertNull(g.getEdge("c", "c"));
    }

    @Test
    final void testGetNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class, () -> g.getNode((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertNull(g.getNode(nsTest));
        g.addNode(ns);
        assertNotNull(g.getNode(nsTest));
        g.addNode("a");
        GraphNode<String> na = g.getNode("a");
        assertNotNull(na);
        na.setColor(GraphNode.COLOR_BLACK);
        assertEquals(g.getNode("a").getColor(), GraphNode.COLOR_BLACK);
        assertFalse(g.addNode("a"));
        assertEquals(g.getNode(na).getColor(), GraphNode.COLOR_BLACK);
        assertNull(g.getNode("b"));
    }

    @Test
    final void testGetNodeInt() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(1));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertEquals(nsTest, g.getNode(0));
        assertEquals(1, g.getNode(0).getColor());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(2));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertEquals(nuTest, g.getNode(1));
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeIndexOf((GraphNode<String>) null));
        assertThrows(NullPointerException.class, () -> g.getNodeIndexOf((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertEquals(0, g.getNodeIndexOf("s"));
        assertThrows(IllegalArgumentException.class, () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertEquals(1, g.getNodeIndexOf("u"));
        assertEquals(0, g.getNodeIndexOf("s"));
        g.addNode("x");
        assertEquals(2, g.getNodeIndexOf("x"));
        g.addEdge("s", "x");
        assertEquals(0, g.getNodeIndexOf("s"));
        g.removeNode(nu);
        assertThrows(IllegalArgumentException.class, () -> g.getNodeIndexOf("u"));
        assertEquals(0, g.getNodeIndexOf("s"));
        assertFalse(g.addNode("s"));
        assertFalse(g.addNode("x"));
        assertEquals(1, g.getNodeIndexOf("x"));
        g.removeNode("s");
        assertThrows(IllegalArgumentException.class, () -> g.getNodeIndexOf("s"));
        assertEquals(0, g.getNodeIndexOf("x"));
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<GraphNode<String>>();
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertEquals(nodes, testNodes);
        GraphNode<String> nuTestBis = new GraphNode<String>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertEquals(nodes, testNodes);
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(ns, nu, true)));
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertTrue(g.addEdge(esu));
        assertNotNull(g.getEdge(new GraphEdge<String>(ns, nu, false)));
        assertFalse(g.addEdge(new GraphEdge<String>(nu, ns, false, 6.0)));
        g.addNode("x");
        assertTrue(g.addEdge("x", "s"));
        assertNotNull(g.getEdge("s", "x"));
        assertNotNull(g.getEdge("x", "s"));
        g.addNode("t");
        assertTrue(g.addWeightedEdge("s", "t", 5.0));
        GraphEdge<String> est = g.getEdge("t", "s");
        assertNotNull(est);
        assertEquals(5, est.getWeight());
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        assertTrue(g.addWeightedEdge(nw, nu, 4.0));
        assertEquals(4, g.getEdge("u", "w").getWeight());
        assertFalse(g.addEdge("w", "u"));
    }

    @Test
    final void testRemoveEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.removeEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        assertThrows(NullPointerException.class, () -> g.removeEdge(null, ns));
        assertThrows(NullPointerException.class, () -> g.removeEdge(ns, null));
        g.addNode(ns);
        g.addNode("a");
        g.addEdge("s", "a");
        GraphNode<String> nt = new GraphNode<String>("t");
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge(ns, nt));
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge(nt, ns));
        g.addNode(nt);
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge(ns, nt));
        g.addEdge("t", "s");
        assertNotNull(g.getEdge("a", "s"));
        g.removeEdge("a", "s");
        assertNull(g.getEdge("a", "s"));
        assertNull(g.getEdge("s", "a"));
        GraphEdge<String> ets = new GraphEdge<String>(nt, ns, false);
        assertNotNull(g.getEdge(ets));
        g.removeEdge(ets);
        assertNull(g.getEdge(ets));
        g.addEdge("a", "t");
        int i = g.getNodeIndexOf("a");
        int j = g.getNodeIndexOf(nt);
        assertNotNull(g.getEdge(i, j));
        g.removeEdge(j, i);
        assertNull(g.getEdge(i, j));
    }

    @Test
    final void testGetEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getEdge(null));
        assertThrows(NullPointerException.class, () -> g.getEdge(null, (String) null));
        assertThrows(NullPointerException.class, () -> g.getEdge(null, (GraphNode<String>) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class, () -> g.getEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertNull(g.getEdge(new GraphEdge<String>(ns, nu, false)));
        g.addEdge(esu);
        assertNotNull(g.getEdge(new GraphEdge<String>(ns, nu, false)));
        g.addNode("a");
        g.addNode("b");
        g.addEdge("a", "s");
        g.addWeightedEdge("s", "b", 1);
        assertTrue(g.getEdge("s", "a").getNode1().getLabel().equals("s") || g.getEdge("s", "a").getNode1().getLabel().equals("a"));
        assertNotNull(g.getEdge(new GraphNode<String>("b"), new GraphNode<String>("s")));
        assertNull(g.getEdge("u", "b"));
        int is = g.getNodeIndexOf(ns);
        int ia = g.getNodeIndexOf("a");
        int ib = g.getNodeIndexOf("b");
        assertNotNull(g.getEdge(is, ia));
        assertNotNull(g.getEdge(is, ib));
        assertNull(g.getEdge(ib, ia));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 5));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getAdjacentNodesOf((GraphNode<String>) null));
        assertThrows(NullPointerException.class, () -> g.getAdjacentNodesOf((String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getAdjacentNodesOf(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertEquals(g.getAdjacentNodesOf(ns), adjNodes);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getAdjacentNodesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);
        assertEquals(g.getAdjacentNodesOf(nsTest), adjNodes);
        adjNodes.clear();
        adjNodes.add(nsTest);
        assertEquals(g.getAdjacentNodesOf(nxTest), adjNodes);
        assertEquals(g.getAdjacentNodesOf(nuTest), adjNodes);
        GraphNode<String> np = new GraphNode<String>("p");
        GraphNode<String> npTest = new GraphNode<String>("p");
        g.addNode(np);
        adjNodes.clear();
        assertEquals(g.getAdjacentNodesOf(npTest), adjNodes);
        g.addNode("q");
        g.addEdge("x", "u");
        g.addEdge("u", "q");
        g.addEdge("p", "u");
        adjNodes.add(nsTest);
        adjNodes.add(nxTest);
        adjNodes.add(new GraphNode<String>("q"));
        adjNodes.add(new GraphNode<String>("p"));
        assertEquals(g.getAdjacentNodesOf("u"), adjNodes);
        g.addNode("r");
        g.removeEdge("u", "p");
        g.removeEdge("u", "q");
        g.addEdge("r", "q");
        g.addEdge("p", "r");
        g.addEdge("r", "r");
        adjNodes.remove(nsTest);
        adjNodes.remove(nxTest);
        adjNodes.add(new GraphNode<String>("r"));
        int i = g.getNodeIndexOf("r");
        assertEquals(g.getAdjacentNodesOf(i), adjNodes);
        adjNodes.remove(new GraphNode<String>("r"));
        g.removeEdge("r", "r");
        assertEquals(g.getAdjacentNodesOf(i), adjNodes);
    }

    @Test
    final void testGetEdgesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(NullPointerException.class, () -> g.getEdgesOf((GraphNode<String>) null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getEdgesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertEquals(g.getEdgesOf(ns), edgesTest);
        edgesTest.clear();
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(nx, ns, false));
        assertEquals(g.getEdgesOf(nx), edgesTest);
        edgesTest.clear();
        assertEquals(g.getEdgesOf(nw), edgesTest);
        g.addWeightedEdge("x", "x", 8.9);
        edgesTest.add(esx);
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("x"), new GraphNode<String>("x"), false));
        assertEquals(g.getEdgesOf("x"), edgesTest);
        g.addEdge("y", "w");
        int j = g.getNodeIndexOf("y");
        edgesTest.clear();
        edgesTest.add(eys);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("w"), new GraphNode<String>("y"), false));
        assertEquals(g.getEdgesOf(j), edgesTest);
    }

    @Test
    final void testGetEdges() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertEquals(g.getEdges(), edgesTest);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphEdge<String> esuTest = new GraphEdge<String>(nu, ns, false);
        edgesTest.add(esuTest);
        assertEquals(g.getEdges(), edgesTest);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertEquals(g.getEdges(), edgesTest);
        g.clear();
        edgesTest.clear();
        assertEquals(g.getEdges(), edgesTest);
    }

    @Test
    final void testGetDegreeOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.getDegreeOf(ns));
        assertThrows(NullPointerException.class, () -> g.getDegreeOf((GraphNode<String>) null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getDegreeOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, false, 7.07);
        g.addEdge(euw);
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode(nz);
        GraphEdge<String> ezy = new GraphEdge<String>(nz, ny, false, 7.107);
        g.addEdge(ezy);
        assertEquals(3, g.getDegreeOf(ns));
        assertEquals(3, g.getDegreeOf(nu));
        assertEquals(3, g.getDegreeOf(nx));
        assertEquals(3, g.getDegreeOf(ny));
        assertEquals(1, g.getDegreeOf(nz));
        assertEquals(1, g.getDegreeOf(nw));
    }

}
