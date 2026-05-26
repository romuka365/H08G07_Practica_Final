import Estructuras.MyCircularList.ListaCircular;
import Estructuras.MyCircularList.LCOrdenada;
import Estructuras.Interfaces.Iterador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyCircularListTest {

    // === ListaCircular ===

    @Test
    void addInicio() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.addInicio("a");
        lista.addInicio("b");
        assertEquals("b", lista.get("b"));
        assertEquals("a", lista.get("a"));
    }

    @Test
    void addAlFinal() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        assertEquals("a", lista.get("a"));
        assertEquals("b", lista.get("b"));
    }

    @Test
    void getExistente() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        assertEquals("a", lista.get("a"));
    }

    @Test
    void getNoExistente() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        assertNull(lista.get("b"));
    }

    @Test
    void getListaVacia() {
        ListaCircular<String> lista = new ListaCircular<>();
        assertNull(lista.get("a"));
    }

    @Test
    void delPrimero() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");
        assertEquals("a", lista.del("a"));
        assertEquals("b", lista.get("b"));
        assertEquals("c", lista.get("c"));
        assertEquals(2, lista.getSize());
    }

    @Test
    void delMedio() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");
        assertEquals("b", lista.del("b"));
        assertEquals("a", lista.get("a"));
        assertEquals("c", lista.get("c"));
        assertEquals(2, lista.getSize());
    }

    @Test
    void delUltimo() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");
        assertEquals("c", lista.del("c"));
        assertEquals("a", lista.get("a"));
        assertEquals("b", lista.get("b"));
        assertEquals(2, lista.getSize());
    }

    @Test
    void delUnicoElemento() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        assertEquals("a", lista.del("a"));
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.getSize());
    }

    @Test
    void delNoExistente() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        assertNull(lista.del("b"));
        assertEquals(1, lista.getSize());
    }

    @Test
    void delListaVacia() {
        ListaCircular<String> lista = new ListaCircular<>();
        assertNull(lista.del("a"));
    }

    @Test
    void clear() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.clear();
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.getSize());
    }

    @Test
    void isEmptyRecienCreada() {
        ListaCircular<String> lista = new ListaCircular<>();
        assertTrue(lista.isEmpty());
    }

    @Test
    void getSize() {
        ListaCircular<String> lista = new ListaCircular<>();
        assertEquals(0, lista.getSize());
        lista.add("a");
        assertEquals(1, lista.getSize());
        lista.add("b");
        assertEquals(2, lista.getSize());
    }

    @Test
    void iterador() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");
        Iterador<String> it = lista.getIterador();
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        assertEquals("c", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void iteradorListaVacia() {
        ListaCircular<String> lista = new ListaCircular<>();
        Iterador<String> it = lista.getIterador();
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    // === LCOrdenada ===

    @Test
    void ordenadaAddMantieneOrden() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        lista.add(3);
        lista.add(1);
        lista.add(2);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
    }

    @Test
    void ordenadaAddAlInicio() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        lista.add(2);
        lista.add(3);
        lista.add(1);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
    }

    @Test
    void ordenadaAddAlFinal() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
    }

    @Test
    void ordenadaAddRepetidos() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        lista.add(1);
        lista.add(1);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(1, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void ordenadaUnElemento() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        lista.add(5);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void ordenadaGetSize() {
        LCOrdenada<Integer> lista = new LCOrdenada<>();
        assertEquals(0, lista.getSize());
        lista.add(3);
        assertEquals(1, lista.getSize());
        lista.add(1);
        assertEquals(2, lista.getSize());
    }

    @Test
    void integridadCircular() {
        ListaCircular<String> lista = new ListaCircular<>();
        lista.add("a");
        lista.add("b");
        lista.del("a");
        lista.del("b");
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.getSize());
    }
}
