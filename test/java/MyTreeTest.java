import Estructuras.MyTree.ArbolBinarioDeBusqueda;
import Estructuras.MyTree.ArbolBinarioDeBusquedaEnteros;
import Estructuras.MyLinkedList.ListaSE;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyTreeTest {

    // === ArbolBinarioDeBusqueda ===

    @Test
    void addYGetRaiz() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        assertEquals(5, (int) arbol.getRaiz().getDato());
    }

    @Test
    void isEmptyRecienCreado() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertTrue(arbol.isEmpty());
    }

    @Test
    void isEmptyTrasAdd() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        assertFalse(arbol.isEmpty());
    }

    @Test
    void addNoDuplicados() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(5);
        ListaSE<Integer> inOrder = arbol.getListaOrdenCentral();
        assertEquals(1, inOrder.getSize());
    }

    @Test
    void getAlturaVacio() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertEquals(0, arbol.getAltura());
    }

    @Test
    void getAlturaUnNodo() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        assertEquals(1, arbol.getAltura());
    }

    @Test
    void getAlturaVarios() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        assertEquals(3, arbol.getAltura());
    }

    @Test
    void getAlturaDesbalanceado() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(1);
        arbol.add(2);
        arbol.add(3);
        assertEquals(3, arbol.getAltura());
    }

    @Test
    void getGradoVacio() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertEquals(0, arbol.getGrado());
    }

    @Test
    void getGradoUnNodo() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        assertEquals(0, arbol.getGrado());
    }

    @Test
    void getGradoCompleto() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        assertEquals(2, arbol.getGrado());
    }

    @Test
    void ordenCentral() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        arbol.add(6);
        arbol.add(8);
        ListaSE<Integer> lista = arbol.getListaOrdenCentral();
        int[] esperado = {2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < esperado.length; i++) {
            assertEquals(esperado[i], (int) lista.get(esperado[i]));
        }
    }

    @Test
    void preOrden() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        ListaSE<Integer> lista = arbol.getListaPreOrden();
        int[] esperado = {5, 3, 2, 4, 7};
        for (int v : esperado) {
            assertNotNull(lista.get(v));
        }
    }

    @Test
    void postOrden() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        ListaSE<Integer> lista = arbol.getListaPostOrden();
        int[] esperado = {2, 4, 3, 7, 5};
        for (int v : esperado) {
            assertNotNull(lista.get(v));
        }
    }

    @Test
    void getListaDatosNivel() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        ListaSE<Integer> nivel2 = arbol.getListaDatosNivel(2);
        assertNotNull(nivel2.get(3));
        assertNotNull(nivel2.get(7));
    }

    @Test
    void getListaDatosNivelInexistente() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        ListaSE<Integer> nivel2 = arbol.getListaDatosNivel(2);
        assertTrue(nivel2.isEmpty());
    }

    @Test
    void isArbolHomogeneoVacio() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertTrue(arbol.isArbolHomogeneo());
    }

    @Test
    void isArbolHomogeneoTrue() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        assertFalse(arbol.isArbolHomogeneo());
    }

    @Test
    void isArbolCompletoVacio() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertTrue(arbol.isArbolCompleto());
    }

    @Test
    void isArbolCompletoPerfecto() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        arbol.add(6);
        arbol.add(8);
        assertTrue(arbol.isArbolCompleto());
    }

    @Test
    void isArbolCasiCompletoVacio() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertTrue(arbol.isArbolCasiCompleto());
    }

    @Test
    void isArbolCasiCompletoTrue() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        arbol.add(6);
        assertTrue(arbol.isArbolCasiCompleto());
    }

    @Test
    void getCaminoExistente() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        ListaSE<Integer> camino = arbol.getCamino(2);
        assertNotNull(camino.get(5));
        assertNotNull(camino.get(3));
        assertNotNull(camino.get(2));
    }

    @Test
    void getCaminoNoExistente() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        ListaSE<Integer> camino = arbol.getCamino(10);
        assertTrue(camino.isEmpty());
    }

    @Test
    void getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        ArbolBinarioDeBusqueda<Integer> sub = arbol.getSubArbolIzquierda();
        assertEquals(3, (int) sub.getRaiz().getDato());
    }

    @Test
    void getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        ArbolBinarioDeBusqueda<Integer> sub = arbol.getSubArbolDerecha();
        assertEquals(7, (int) sub.getRaiz().getDato());
    }

    // === ArbolBinarioDeBusquedaEnteros ===

    @Test
    void getSumaVacio() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        assertEquals(0, arbol.getSuma());
    }

    @Test
    void getSuma() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        assertEquals(15, arbol.getSuma());
    }

    @Test
    void getSumaUnNodo() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        arbol.add(10);
        assertEquals(10, arbol.getSuma());
    }

    @Test
    void getSubArbolIzquierdaEnteros() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        ArbolBinarioDeBusquedaEnteros sub = arbol.getSubArbolIzquierda();
        assertEquals(3, (int) sub.getRaiz().getDato());
    }

    @Test
    void getSubArbolDerechaEnteros() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        arbol.add(5);
        arbol.add(3);
        arbol.add(7);
        ArbolBinarioDeBusquedaEnteros sub = arbol.getSubArbolDerecha();
        assertEquals(7, (int) sub.getRaiz().getDato());
    }
}
