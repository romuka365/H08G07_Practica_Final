import MyMatrix.MyMatrix;
import MyMatrix.Coordenada;
import MyLinkedList.ListaSE;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyMatrixTest {

    // === MyMatrix ===

    @Test
    void constructorDimensiones() {
        MyMatrix<String> m = new MyMatrix<>(3, 4);
        assertEquals(3, m.getFilas());
        assertEquals(4, m.getColumnas());
    }

    @Test
    void constructorDimensionesInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> new MyMatrix<>(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new MyMatrix<>(5, -1));
    }

    @Test
    void constructorValorInicial() {
        MyMatrix<String> m = new MyMatrix<>(2, 3, "X");
        assertEquals("X", m.get(0, 0));
        assertEquals("X", m.get(1, 2));
    }

    @Test
    void setYGet() {
        MyMatrix<Integer> m = new MyMatrix<>(3, 3);
        m.set(1, 2, 42);
        assertEquals(42, (int) m.get(1, 2));
    }

    @Test
    void getPosicionInvalida() {
        MyMatrix<String> m = new MyMatrix<>(2, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(5, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(-1, 0));
    }

    @Test
    void setPosicionInvalida() {
        MyMatrix<String> m = new MyMatrix<>(2, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> m.set(0, 5, "X"));
    }

    @Test
    void posicionValida() {
        MyMatrix<String> m = new MyMatrix<>(3, 4);
        assertTrue(m.posicionValida(0, 0));
        assertTrue(m.posicionValida(2, 3));
        assertFalse(m.posicionValida(-1, 0));
        assertFalse(m.posicionValida(3, 0));
        assertFalse(m.posicionValida(0, 4));
    }

    @Test
    void getVecinosCentro() {
        MyMatrix<String> m = new MyMatrix<>(5, 5);
        ListaSE<Coordenada> vecinos = m.getVecinos(2, 2);
        assertEquals(4, vecinos.getSize());
        assertNotNull(vecinos.get(new Coordenada(1, 2)));
        assertNotNull(vecinos.get(new Coordenada(3, 2)));
        assertNotNull(vecinos.get(new Coordenada(2, 1)));
        assertNotNull(vecinos.get(new Coordenada(2, 3)));
    }

    @Test
    void getVecinosEsquina() {
        MyMatrix<String> m = new MyMatrix<>(3, 3);
        ListaSE<Coordenada> vecinos = m.getVecinos(0, 0);
        assertEquals(2, vecinos.getSize());
        assertNotNull(vecinos.get(new Coordenada(1, 0)));
        assertNotNull(vecinos.get(new Coordenada(0, 1)));
    }

    @Test
    void getVecinosBorde() {
        MyMatrix<String> m = new MyMatrix<>(3, 3);
        ListaSE<Coordenada> vecinos = m.getVecinos(1, 0);
        assertEquals(3, vecinos.getSize());
        assertNotNull(vecinos.get(new Coordenada(0, 0)));
        assertNotNull(vecinos.get(new Coordenada(2, 0)));
        assertNotNull(vecinos.get(new Coordenada(1, 1)));
    }

    @Test
    void toStringMatriz() {
        MyMatrix<String> m = new MyMatrix<>(2, 2, ".");
        m.set(0, 0, "A");
        m.set(1, 1, "B");
        String esperado = "A .\n. B";
        assertEquals(esperado, m.toString());
    }

    // === Coordenada ===

    @Test
    void coordenadaGet() {
        Coordenada c = new Coordenada(3, 7);
        assertEquals(3, c.getFila());
        assertEquals(7, c.getColumna());
    }

    @Test
    void coordenadaEquals() {
        Coordenada a = new Coordenada(1, 2);
        Coordenada b = new Coordenada(1, 2);
        Coordenada c = new Coordenada(2, 1);
        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void coordenadaCompareTo() {
        Coordenada a = new Coordenada(1, 2);
        Coordenada b = new Coordenada(1, 3);
        Coordenada c = new Coordenada(2, 1);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertTrue(a.compareTo(c) < 0);
        assertEquals(0, a.compareTo(new Coordenada(1, 2)));
    }

    @Test
    void coordenadaToString() {
        Coordenada c = new Coordenada(3, 5);
        assertEquals("(3, 5)", c.toString());
    }
}
