import Estructuras.MyMatrix.MyMatrix;
import Estructuras.MyMatrix.Coordenada;
import Estructuras.MyMatrix.BFSMatriz;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BFSMatrizTest {

    private int contar(ListaSE<Coordenada> lista) {
        int n = 0;
        Iterador<Coordenada> it = lista.getIterador();
        while (it.hasNext()) { it.next(); n++; }
        return n;
    }

    private boolean contiene(ListaSE<Coordenada> lista, Coordenada c) {
        Iterador<Coordenada> it = lista.getIterador();
        while (it.hasNext()) {
            if (it.next().equals(c)) return true;
        }
        return false;
    }

    @Test
    void distanciaCero() {
        MyMatrix<String> m = new MyMatrix<>(5, 5, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(2, 2), 0);
        assertEquals(1, contar(r));
        assertTrue(contiene(r, new Coordenada(2, 2)));
    }

    @Test
    void distanciaUno() {
        MyMatrix<String> m = new MyMatrix<>(5, 5, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(2, 2), 1);
        assertEquals(5, contar(r));
    }

    @Test
    void distanciaDosCentro() {
        MyMatrix<String> m = new MyMatrix<>(5, 5, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(2, 2), 2);
        // Forma de diamante: 1 (dist 0) + 4 (dist 1) + 8 (dist 2) = 13
        assertEquals(13, contar(r));
    }

    @Test
    void distanciaTresCentro() {
        MyMatrix<String> m = new MyMatrix<>(7, 7, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(3, 3), 3);
        // Diamante radio 3: 1 + 4 + 8 + 12 = 25
        assertEquals(25, contar(r));
    }

    @Test
    void matrizCompletaAlcanzable() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        // Desde (1,1) con distancia suficiente llegamos a toda la matriz 3x3
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(1, 1), 5);
        assertEquals(9, contar(r));
    }

    @Test
    void obstaculosBloqueanPaso() {
        MyMatrix<String> m = new MyMatrix<>(5, 5, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        // Bloquear (2,2) para que (1,2) y (2,1) no puedan pasar al otro lado
        bfs.setCeldaBloqueada(2, 2, true);
        // Desde (0,0) con distancia 4
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(0, 0), 4);
        // (2,2) es obstáculo, no debe estar en resultado
        assertFalse(contiene(r, new Coordenada(2, 2)));
    }

    @Test
    void sinAlcanzablesPorPosicionInvalida() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(10, 10), 5);
        assertEquals(0, contar(r));
    }

    @Test
    void sinAlcanzablesPorInicioBloqueado() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        bfs.setCeldaBloqueada(1, 1, true);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(1, 1), 5);
        assertEquals(0, contar(r));
    }

    @Test
    void distanciaUnoDesdeEsquina() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(0, 0), 1);
        assertEquals(3, contar(r)); // (0,0), (0,1), (1,0)
    }

    @Test
    void obstaculosAislanSeccion() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        // Bloquear toda la fila 1 para aislar fila 2
        bfs.setCeldaBloqueada(1, 0, true);
        bfs.setCeldaBloqueada(1, 1, true);
        bfs.setCeldaBloqueada(1, 2, true);
        ListaSE<Coordenada> r = bfs.casillasAlcanzables(new Coordenada(0, 0), 10);
        // Solo alcanzables: (0,0), (0,1), (0,2)
        assertEquals(3, contar(r));
        assertFalse(contiene(r, new Coordenada(2, 0)));
        assertFalse(contiene(r, new Coordenada(2, 1)));
        assertFalse(contiene(r, new Coordenada(2, 2)));
    }

    @Test
    void celdaBloqueadaMarcadaComoObstaculo() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        assertFalse(bfs.estaBloqueada(1, 1));
        bfs.setCeldaBloqueada(1, 1, true);
        assertTrue(bfs.estaBloqueada(1, 1));
    }

    @Test
    void celdaBloqueadaFueraRangoLanzaExcepcion() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        assertThrows(IndexOutOfBoundsException.class, () -> bfs.setCeldaBloqueada(10, 10, true));
    }

    @Test
    void estaBloqueadaFueraRango() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        assertTrue(bfs.estaBloqueada(10, 10));
    }

    @Test
    void constructorMatrizNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new BFSMatriz<>(null));
    }

    @Test
    void maxMovimientoNegativoLanzaExcepcion() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        assertThrows(IllegalArgumentException.class,
            () -> bfs.casillasAlcanzables(new Coordenada(0, 0), -1));
    }

    @Test
    void inicioNullLanzaExcepcion() {
        MyMatrix<String> m = new MyMatrix<>(3, 3, ".");
        BFSMatriz<String> bfs = new BFSMatriz<>(m);
        assertThrows(IllegalArgumentException.class,
            () -> bfs.casillasAlcanzables(null, 5));
    }
}
