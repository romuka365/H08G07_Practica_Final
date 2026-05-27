import Estructuras.MyTree.ArbolGeneral;
import Estructuras.MyTree.NodoGeneral;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyTreeTest {

    private <T extends Comparable<T>> T get(ListaSE<T> lista, int index) {
        Iterador<T> it = lista.getIterador();
        int i = 0;
        while (it.hasNext()) {
            T dato = it.next();
            if (i == index) return dato;
            i++;
        }
        return null;
    }

    @Test
    void constructorCreaRaiz() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Accion");
        assertEquals("Accion", arbol.getRaiz().getDato());
    }

    @Test
    void addChildYGetDato() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        NodoGeneral<String> hijo = arbol.addChild(arbol.getRaiz(), "Hijo1");
        assertNotNull(hijo);
        assertEquals("Hijo1", hijo.getDato());
    }

    @Test
    void addChildActualizaPadre() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        NodoGeneral<String> hijo = arbol.addChild(arbol.getRaiz(), "Hijo");
        assertSame(arbol.getRaiz(), hijo.getPadre());
    }

    @Test
    void addChildNullPadre() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        assertNull(arbol.addChild(null, "Hijo"));
    }

    @Test
    void removeChildExistente() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        arbol.addChild(arbol.getRaiz(), "Hijo1");
        arbol.addChild(arbol.getRaiz(), "Hijo2");
        assertTrue(arbol.removeChild(arbol.getRaiz(), "Hijo1"));
    }

    @Test
    void removeChildInexistente() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        arbol.addChild(arbol.getRaiz(), "Hijo1");
        assertFalse(arbol.removeChild(arbol.getRaiz(), "Inexistente"));
    }

    @Test
    void removeChildNullPadre() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        assertFalse(arbol.removeChild(null, "Algo"));
    }

    @Test
    void buscarExistente() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Accion");
        NodoGeneral<String> mov = arbol.addChild(arbol.getRaiz(), "Movimiento");
        arbol.addChild(mov, "Mover arriba");
        NodoGeneral<String> encontrado = arbol.buscar("Mover arriba");
        assertNotNull(encontrado);
        assertEquals("Mover arriba", encontrado.getDato());
    }

    @Test
    void buscarInexistente() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Accion");
        assertNull(arbol.buscar("NoExiste"));
    }

    @Test
    void buscarRaiz() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Accion");
        assertEquals("Accion", arbol.buscar("Accion").getDato());
    }

    @Test
    void getPreorden() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        NodoGeneral<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(b, "D");
        ListaSE<String> pre = arbol.getPreorden();
        assertEquals(4, pre.getSize());
        assertEquals("A", get(pre, 0));
        assertEquals("B", get(pre, 1));
        assertEquals("D", get(pre, 2));
        assertEquals("C", get(pre, 3));
    }

    @Test
    void getPostorden() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        NodoGeneral<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(b, "D");
        ListaSE<String> pos = arbol.getPostorden();
        assertEquals(4, pos.getSize());
        assertEquals("D", get(pos, 0));
        assertEquals("B", get(pos, 1));
        assertEquals("C", get(pos, 2));
        assertEquals("A", get(pos, 3));
    }

    @Test
    void getPorNiveles() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        NodoGeneral<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(b, "D");
        ListaSE<String> niveles = arbol.getPorNiveles();
        assertEquals(4, niveles.getSize());
        assertEquals("A", get(niveles, 0));
        assertEquals("B", get(niveles, 1));
        assertEquals("C", get(niveles, 2));
        assertEquals("D", get(niveles, 3));
    }

    @Test
    void getAlturaSoloRaiz() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        assertEquals(1, arbol.getAltura());
    }

    @Test
    void getAlturaVariosNiveles() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        NodoGeneral<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(b, "C");
        assertEquals(3, arbol.getAltura());
    }

    @Test
    void getGradoSinHijos() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Raiz");
        assertEquals(0, arbol.getGrado());
    }

    @Test
    void getGradoConHijos() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(arbol.getRaiz(), "D");
        assertEquals(3, arbol.getGrado());
    }

    @Test
    void getNumNodos() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("A");
        NodoGeneral<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(b, "D");
        assertEquals(4, arbol.getNumNodos());
    }

    @Test
    void arbolAccionesJuego() {
        ArbolGeneral<String> arbol = new ArbolGeneral<>("Accion");
        NodoGeneral<String> mov = arbol.addChild(arbol.getRaiz(), "Movimiento");
        arbol.addChild(mov, "Mover arriba");
        arbol.addChild(mov, "Mover abajo");
        arbol.addChild(mov, "Mover izquierda");
        arbol.addChild(mov, "Mover derecha");
        NodoGeneral<String> inter = arbol.addChild(arbol.getRaiz(), "Interaccion");
        arbol.addChild(inter, "Recoger");
        arbol.addChild(inter, "Usar");
        arbol.addChild(inter, "Abrir");
        NodoGeneral<String> comb = arbol.addChild(arbol.getRaiz(), "Combate");
        arbol.addChild(comb, "Atacar");

        assertEquals(12, arbol.getNumNodos());
        assertEquals(4, arbol.getGrado());
        assertEquals(3, arbol.getAltura());
        assertNotNull(arbol.buscar("Mover izquierda"));
        assertNotNull(arbol.buscar("Accion"));
        assertNull(arbol.buscar("Saltar"));
    }
}
