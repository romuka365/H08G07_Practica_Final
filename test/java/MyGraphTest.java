import Estructuras.MyGraph.Grafo;
import Estructuras.MyGraph.Vertice;
import Estructuras.MyGraph.Arista;
import Estructuras.MyGraph.CaminoMinimo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyGraphTest {

    // === Grafo ===

    @Test
    void addVertice() {
        Grafo g = new Grafo();
        g.addVertice("persona", "Alice");
        assertEquals(1, g.getNumVertices());
    }

    @Test
    void addVerticeDuplicado() {
        Grafo g = new Grafo();
        g.addVertice("persona", "Alice");
        g.addVertice("persona", "Alice");
        assertEquals(1, g.getNumVertices());
    }

    @Test
    void buscarIdVerticeExistente() {
        Grafo g = new Grafo();
        g.addVertice("persona", "Alice");
        int id = g.buscarIdVertice("persona", "Alice");
        assertTrue(id >= 0);
    }

    @Test
    void buscarIdVerticeNoExistente() {
        Grafo g = new Grafo();
        assertEquals(-1, g.buscarIdVertice("persona", "Alice"));
    }

    @Test
    void getVerticePorId() {
        Grafo g = new Grafo();
        g.addVertice("persona", "Alice");
        int id = g.buscarIdVertice("persona", "Alice");
        Vertice v = g.getVertice(id);
        assertNotNull(v);
        assertEquals("Alice", v.getNombre());
        assertEquals("persona", v.getTipo());
    }

    @Test
    void getVerticeIdInvalido() {
        Grafo g = new Grafo();
        assertNull(g.getVertice(999));
    }

    @Test
    void addArista() {
        Grafo g = new Grafo();
        g.addVertice("persona", "Alice");
        g.addVertice("ciudad", "Madrid");
        g.addArista("persona", "Alice", "ciudad", "Madrid", "vive_en");
        int idAlice = g.buscarIdVertice("persona", "Alice");
        Vertice v = g.getVertice(idAlice);
        assertNotNull(v);
    }

    @Test
    void getNumVerticesMultiple() {
        Grafo g = new Grafo();
        g.addVertice("a", "A");
        g.addVertice("b", "B");
        g.addVertice("c", "C");
        assertEquals(3, g.getNumVertices());
    }

    // === CaminoMinimo ===

    @Test
    void dijkstraCaminoSimple() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addArista("room", "A", "room", "B", "conecta");
        CaminoMinimo cm = new CaminoMinimo(g);
        cm.ejecutarDijkstra("room", "A");
        String camino = cm.obtenerCamino("room", "B");
        assertTrue(camino.contains("B"));
    }

    @Test
    void dijkstraSinCamino() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        CaminoMinimo cm = new CaminoMinimo(g);
        cm.ejecutarDijkstra("room", "A");
        String camino = cm.obtenerCamino("room", "B");
        assertTrue(camino.contains("No hay camino"));
    }

    @Test
    void dijkstraOrigenInexistente() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        CaminoMinimo cm = new CaminoMinimo(g);
        cm.ejecutarDijkstra("room", "Z");
        String camino = cm.obtenerCamino("room", "A");
        assertTrue(camino.contains("No hay camino"));
    }

    // === Vertice ===

    @Test
    void verticeGetNombreYTipo() {
        Vertice v = new Vertice("persona", "Alice");
        assertEquals("Alice", v.getNombre());
        assertEquals("persona", v.getTipo());
    }

    @Test
    void verticeIdInicialEsMenosUno() {
        Vertice v = new Vertice("x", "y");
        assertEquals(-1, v.getId());
    }

    // === Arista ===

    @Test
    void aristaGetIdDestino() {
        Arista a = new Arista(5, "conecta");
        assertEquals(5, a.getIdDestino());
        assertEquals("conecta", a.getPredicado());
    }
}
