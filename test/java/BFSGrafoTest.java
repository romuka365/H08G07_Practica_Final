import Estructuras.MyGraph.Grafo;
import Estructuras.MyGraph.Vertice;
import Estructuras.MyGraph.BFSGrafo;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BFSGrafoTest {

    private int contar(ListaSE<Vertice> lista) {
        int n = 0;
        Iterador<Vertice> it = lista.getIterador();
        while (it.hasNext()) { it.next(); n++; }
        return n;
    }

    private String nombresEnOrden(ListaSE<Vertice> lista) {
        String s = "";
        Iterador<Vertice> it = lista.getIterador();
        while (it.hasNext()) {
            s += it.next().getNombre();
        }
        return s;
    }

    @Test
    void getCaminoSimple() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addArista("room", "A", "room", "B", "conecta");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "B");
        assertEquals(2, contar(r));
        assertEquals("AB", nombresEnOrden(r));
    }

    @Test
    void getCaminoVariosPasos() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addVertice("room", "C");
        g.addArista("room", "A", "room", "B", "conecta");
        g.addArista("room", "B", "room", "C", "conecta");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "C");
        assertEquals(3, contar(r));
        assertEquals("ABC", nombresEnOrden(r));
    }

    @Test
    void getCaminoOrigenIgualDestino() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "A");
        assertEquals(1, contar(r));
        assertEquals("A", nombresEnOrden(r));
    }

    @Test
    void getCaminoDestinoInalcanzable() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addVertice("room", "C");
        g.addArista("room", "A", "room", "B", "conecta");
        // C no tiene conexiones
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "C");
        assertEquals(0, contar(r));
    }

    @Test
    void getCaminoOrigenInexistente() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "Z", "room", "A");
        assertEquals(0, contar(r));
    }

    @Test
    void getCaminoDestinoInexistente() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "Z");
        assertEquals(0, contar(r));
    }

    @Test
    void buscarSalidaEncuentraSalida() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addVertice("exit", "Salida1");
        g.addArista("room", "A", "room", "B", "conecta");
        g.addArista("room", "B", "exit", "Salida1", "conecta");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.buscarSalida("room", "A", "exit");
        assertTrue(contar(r) >= 2);
        // Debe terminar en un vértice de tipo exit
        Vertice ultimo = null;
        Iterador<Vertice> it = r.getIterador();
        while (it.hasNext()) ultimo = it.next();
        assertEquals("exit", ultimo.getTipo());
    }

    @Test
    void buscarSalidaSinSalida() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addArista("room", "A", "room", "B", "conecta");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.buscarSalida("room", "A", "exit");
        assertEquals(0, contar(r));
    }

    @Test
    void buscarSalidaOrigenInexistente() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.buscarSalida("room", "Z", "exit");
        assertEquals(0, contar(r));
    }

    @Test
    void buscarSalidaOrigenYaEsSalida() {
        Grafo g = new Grafo();
        g.addVertice("exit", "Salida1");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.buscarSalida("exit", "Salida1", "exit");
        assertEquals(1, contar(r));
    }

    @Test
    void constructorGrafoNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new BFSGrafo(null));
    }

    @Test
    void getCaminoCaminoLargo() {
        Grafo g = new Grafo();
        g.addVertice("room", "A");
        g.addVertice("room", "B");
        g.addVertice("room", "C");
        g.addVertice("room", "D");
        g.addArista("room", "A", "room", "B", "conecta");
        g.addArista("room", "B", "room", "C", "conecta");
        g.addArista("room", "C", "room", "D", "conecta");
        BFSGrafo bfs = new BFSGrafo(g);
        ListaSE<Vertice> r = bfs.getCamino("room", "A", "room", "D");
        assertEquals("ABCD", nombresEnOrden(r));
    }
}
