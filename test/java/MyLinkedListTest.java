import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyLinkedList.LSEOrdenada;
import Estructuras.Interfaces.Iterador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    // === ListaSE ===

    @Test
    void addInicio() {
        ListaSE<String> lista = new ListaSE<>();
        lista.addInicio("a");
        lista.addInicio("b");
        assertEquals("b", lista.get("b"));
        assertEquals("a", lista.get("a"));
    }

    @Test
    void addAlFinal() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        lista.add("b");
        assertEquals("a", lista.get("a"));
        assertEquals("b", lista.get("b"));
    }

    @Test
    void getExistente() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        assertEquals("a", lista.get("a"));
    }

    @Test
    void getNoExistente() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        assertNull(lista.get("b"));
    }

    @Test
    void getListaVacia() {
        ListaSE<String> lista = new ListaSE<>();
        assertNull(lista.get("a"));
    }

    @Test
    void delPrimero() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        lista.add("b");
        assertEquals("a", lista.del("a"));
        assertEquals("b", lista.get("b"));
        assertEquals(1, lista.getSize());
    }

    @Test
    void delMedio() {
        ListaSE<String> lista = new ListaSE<>();
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
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        lista.add("b");
        assertEquals("b", lista.del("b"));
        assertEquals("a", lista.get("a"));
        assertEquals(1, lista.getSize());
    }

    @Test
    void delUnicoElemento() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        assertEquals("a", lista.del("a"));
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.getSize());
    }

    @Test
    void delNoExistente() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        assertNull(lista.del("b"));
        assertEquals(1, lista.getSize());
    }

    @Test
    void delListaVacia() {
        ListaSE<String> lista = new ListaSE<>();
        assertNull(lista.del("a"));
    }

    @Test
    void clear() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        lista.add("b");
        lista.clear();
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.getSize());
    }

    @Test
    void isEmptyRecienCreada() {
        ListaSE<String> lista = new ListaSE<>();
        assertTrue(lista.isEmpty());
    }

    @Test
    void getSize() {
        ListaSE<String> lista = new ListaSE<>();
        assertEquals(0, lista.getSize());
        lista.add("a");
        assertEquals(1, lista.getSize());
        lista.add("b");
        assertEquals(2, lista.getSize());
    }

    @Test
    void iterador() {
        ListaSE<String> lista = new ListaSE<>();
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
        ListaSE<String> lista = new ListaSE<>();
        Iterador<String> it = lista.getIterador();
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void toStringVacia() {
        ListaSE<String> lista = new ListaSE<>();
        assertEquals("[]", lista.toString());
    }

    @Test
    void toStringConElementos() {
        ListaSE<String> lista = new ListaSE<>();
        lista.add("a");
        lista.add("b");
        assertEquals("[a, b]", lista.toString());
    }

    // === LSEOrdenada ===

    @Test
    void ordenadaAddMantieneOrden() {
        LSEOrdenada<Integer> lista = new LSEOrdenada<>();
        lista.add(3);
        lista.add(1);
        lista.add(2);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
    }

    @Test
    void ordenadaAddRepetidos() {
        LSEOrdenada<Integer> lista = new LSEOrdenada<>();
        lista.add(1);
        lista.add(1);
        Iterador<Integer> it = lista.getIterador();
        assertEquals(1, it.next());
        assertEquals(1, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void ordenadaGetSize() {
        LSEOrdenada<Integer> lista = new LSEOrdenada<>();
        assertEquals(0, lista.getSize());
        lista.add(3);
        assertEquals(1, lista.getSize());
        lista.add(1);
        assertEquals(2, lista.getSize());
    }
}
