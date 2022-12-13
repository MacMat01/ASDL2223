import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Template: Luca Tesei
 */
class DijkstraShortestPathComputerTest {
    @Test
    final void testGetShortestPathTo1() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nt = new GraphNode<String>("t");
        g.addNode(nt);
        GraphEdge<String> est = new GraphEdge<String>(ns, nt, true, 10);
        g.addEdge(est);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> esy = new GraphEdge<String>(ns, ny, true, 5);
        g.addEdge(esy);
        GraphEdge<String> ety = new GraphEdge<String>(nt, ny, true, 2);
        g.addEdge(ety);
        GraphEdge<String> eyt = new GraphEdge<String>(ny, nt, true, 3);
        g.addEdge(eyt);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> etx = new GraphEdge<String>(nt, nx, true, 1);
        g.addEdge(etx);
        GraphEdge<String> eyx = new GraphEdge<String>(ny, nx, true, 9);
        g.addEdge(eyx);
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode(nz);
        GraphEdge<String> exz = new GraphEdge<String>(nx, nz, true, 4.0);
        g.addEdge(exz);
        GraphEdge<String> ezx = new GraphEdge<String>(nz, nx, true, 6.0);
        g.addEdge(ezx);
        GraphEdge<String> eyz = new GraphEdge<String>(ny, nz, true, 2.0);
        g.addEdge(eyz);
        GraphEdge<String> ezs = new GraphEdge<String>(nz, ns, true, 7.0);
        g.addEdge(ezs);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);
        List<GraphEdge<String>> pathTest = new ArrayList<GraphEdge<String>>();
        assertTrue(c.getShortestPathTo(nsTest).equals(pathTest));
        System.out.println(c.printPath(c.getShortestPathTo(nsTest)));

        GraphNode<String> nyTest = new GraphNode<String>("y");
        GraphEdge<String> esyTest = new GraphEdge<String>(nsTest, nyTest, true, 5);
        pathTest.add(esyTest);
        assertTrue(c.getShortestPathTo(nyTest).equals(pathTest));
        System.out.println(c.printPath(c.getShortestPathTo(nyTest)));

        GraphNode<String> ntTest = new GraphNode<String>("t");
        GraphEdge<String> eytTest = new GraphEdge<String>(nyTest, ntTest, true, 3.0);
        pathTest.add(eytTest);
        assertTrue(c.getShortestPathTo(ntTest).equals(pathTest));
        System.out.println(c.printPath(c.getShortestPathTo(ntTest)));

        // aggiunge il nuovo pezzo fino a x
        GraphNode<String> nxTest = new GraphNode<String>("x");
        GraphEdge<String> etxTest = new GraphEdge<String>(ntTest, nxTest, true, 1.0);
        pathTest.add(etxTest);
        assertTrue(c.getShortestPathTo(nxTest).equals(pathTest));
        System.out.println(c.printPath(c.getShortestPathTo(nxTest)));

        // rimuove gli ultimi due pezzi del path
        pathTest.remove(pathTest.size() - 1);
        pathTest.remove(pathTest.size() - 1);
        // aggiunge il nuovo pezzo fino a z
        GraphNode<String> nzTest = new GraphNode<String>("z");
        GraphEdge<String> eyzTest = new GraphEdge<String>(nyTest, nzTest, true, 2.0);
        pathTest.add(eyzTest);
        assertTrue(c.getShortestPathTo(nzTest).equals(pathTest));
        System.out.println(c.printPath(c.getShortestPathTo(nzTest)));
    }

    @Test
    public void testIsComputed() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<>(ny, ns, true, 7.03);
        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        assertFalse(c.isComputed());
        c.computeShortestPathsFrom(ns);
        assertTrue(c.isComputed());
    }

    @Test
    public void testLastSource() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<>(ny, ns, true, 7.03);

        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);

        assertThrows(IllegalStateException.class, () -> {
            c.getLastSource();
        });
        c.computeShortestPathsFrom(ns);
        assertEquals(ns, c.getLastSource());
        c.computeShortestPathsFrom(nx);
        assertEquals(nx, c.getLastSource());
    }

}
