import MyQueue.Cola;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    void encolarYFrente() {
        Cola<String> cola = new Cola<>();
        cola.encolar("a");
        assertEquals("a", cola.frente());
        cola.encolar("b");
        assertEquals("a", cola.frente());
    }

    @Test
    void desencolar() {
        Cola<String> cola = new Cola<>();
        cola.encolar("a");
        cola.encolar("b");
        assertEquals("a", cola.desencolar());
        assertEquals("b", cola.desencolar());
    }

    @Test
    void desencolarColaVacia() {
        Cola<String> cola = new Cola<>();
        assertNull(cola.desencolar());
    }

    @Test
    void frenteColaVacia() {
        Cola<String> cola = new Cola<>();
        assertNull(cola.frente());
    }

    @Test
    void vacioTrasDesencolarTodo() {
        Cola<String> cola = new Cola<>();
        cola.encolar("a");
        cola.encolar("b");
        cola.desencolar();
        cola.desencolar();
        assertTrue(cola.isEmpty());
        assertEquals(0, cola.getSize());
    }

    @Test
    void clear() {
        Cola<String> cola = new Cola<>();
        cola.encolar("a");
        cola.encolar("b");
        cola.clear();
        assertTrue(cola.isEmpty());
        assertEquals(0, cola.getSize());
    }

    @Test
    void isEmptyRecienCreada() {
        Cola<String> cola = new Cola<>();
        assertTrue(cola.isEmpty());
    }

    @Test
    void getSize() {
        Cola<String> cola = new Cola<>();
        assertEquals(0, cola.getSize());
        cola.encolar("a");
        assertEquals(1, cola.getSize());
        cola.encolar("b");
        assertEquals(2, cola.getSize());
    }

    @Test
    void fifoOrden() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        assertEquals(1, cola.desencolar());
        assertEquals(2, cola.desencolar());
        assertEquals(3, cola.desencolar());
        assertTrue(cola.isEmpty());
    }

    @Test
    void encolarYDesencolarMultiple() {
        Cola<String> cola = new Cola<>();
        cola.encolar("a");
        cola.encolar("b");
        assertEquals("a", cola.desencolar());
        cola.encolar("c");
        assertEquals("b", cola.desencolar());
        assertEquals("c", cola.desencolar());
        assertTrue(cola.isEmpty());
    }
}
