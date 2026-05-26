import Estructuras.MyStack.Pila;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void pushYTop() {
        Pila<String> pila = new Pila<>();
        pila.push("a");
        assertEquals("a", pila.top());
        pila.push("b");
        assertEquals("b", pila.top());
    }

    @Test
    void pop() {
        Pila<String> pila = new Pila<>();
        pila.push("a");
        pila.push("b");
        assertEquals("b", pila.pop());
        assertEquals("a", pila.pop());
    }

    @Test
    void popPilaVacia() {
        Pila<String> pila = new Pila<>();
        assertNull(pila.pop());
    }

    @Test
    void topPilaVacia() {
        Pila<String> pila = new Pila<>();
        assertNull(pila.top());
    }

    @Test
    void vacioTrasPopTodo() {
        Pila<String> pila = new Pila<>();
        pila.push("a");
        pila.push("b");
        pila.pop();
        pila.pop();
        assertTrue(pila.isEmpty());
        assertEquals(0, pila.getSize());
    }

    @Test
    void clear() {
        Pila<String> pila = new Pila<>();
        pila.push("a");
        pila.push("b");
        pila.clear();
        assertTrue(pila.isEmpty());
        assertEquals(0, pila.getSize());
    }

    @Test
    void isEmptyRecienCreada() {
        Pila<String> pila = new Pila<>();
        assertTrue(pila.isEmpty());
    }

    @Test
    void getSize() {
        Pila<String> pila = new Pila<>();
        assertEquals(0, pila.getSize());
        pila.push("a");
        assertEquals(1, pila.getSize());
        pila.push("b");
        assertEquals(2, pila.getSize());
    }

    @Test
    void lIFOOrden() {
        Pila<Integer> pila = new Pila<>();
        pila.push(1);
        pila.push(2);
        pila.push(3);
        assertEquals(3, pila.pop());
        assertEquals(2, pila.pop());
        assertEquals(1, pila.pop());
        assertTrue(pila.isEmpty());
    }
}
