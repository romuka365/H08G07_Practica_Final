import Estructuras.MyTree.MyTree;
import Estructuras.MyTree.Node;
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
        MyTree<String> arbol = new MyTree<>("Accion");
        assertEquals("Accion", arbol.getRaiz().getDato());
    }

    @Test
    void addChildYGetDato() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        Node<String> hijo = arbol.addChild(arbol.getRaiz(), "Hijo1");
        assertNotNull(hijo);
        assertEquals("Hijo1", hijo.getDato());
    }

    @Test
    void addChildActualizaPadre() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        Node<String> hijo = arbol.addChild(arbol.getRaiz(), "Hijo");
        assertSame(arbol.getRaiz(), hijo.getPadre());
    }

    @Test
    void addChildNullPadre() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        assertNull(arbol.addChild(null, "Hijo"));
    }

    @Test
    void removeChildExistente() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        arbol.addChild(arbol.getRaiz(), "Hijo1");
        arbol.addChild(arbol.getRaiz(), "Hijo2");
        assertTrue(arbol.removeChild(arbol.getRaiz(), "Hijo1"));
    }

    @Test
    void removeChildInexistente() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        arbol.addChild(arbol.getRaiz(), "Hijo1");
        assertFalse(arbol.removeChild(arbol.getRaiz(), "Inexistente"));
    }

    @Test
    void removeChildNullPadre() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        assertFalse(arbol.removeChild(null, "Algo"));
    }

    @Test
    void buscarExistente() {
        MyTree<String> arbol = new MyTree<>("Accion");
        Node<String> mov = arbol.addChild(arbol.getRaiz(), "Movimiento");
        arbol.addChild(mov, "Mover arriba");
        Node<String> encontrado = arbol.buscar("Mover arriba");
        assertNotNull(encontrado);
        assertEquals("Mover arriba", encontrado.getDato());
    }

    @Test
    void buscarInexistente() {
        MyTree<String> arbol = new MyTree<>("Accion");
        assertNull(arbol.buscar("NoExiste"));
    }

    @Test
    void buscarRaiz() {
        MyTree<String> arbol = new MyTree<>("Accion");
        assertEquals("Accion", arbol.buscar("Accion").getDato());
    }

    @Test
    void getPreorden() {
        MyTree<String> arbol = new MyTree<>("A");
        Node<String> b = arbol.addChild(arbol.getRaiz(), "B");
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
        MyTree<String> arbol = new MyTree<>("A");
        Node<String> b = arbol.addChild(arbol.getRaiz(), "B");
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
        MyTree<String> arbol = new MyTree<>("A");
        Node<String> b = arbol.addChild(arbol.getRaiz(), "B");
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
        MyTree<String> arbol = new MyTree<>("Raiz");
        assertEquals(1, arbol.getAltura());
    }

    @Test
    void getAlturaVariosNiveles() {
        MyTree<String> arbol = new MyTree<>("A");
        Node<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(b, "C");
        assertEquals(3, arbol.getAltura());
    }

    @Test
    void getGradoSinHijos() {
        MyTree<String> arbol = new MyTree<>("Raiz");
        assertEquals(0, arbol.getGrado());
    }

    @Test
    void getGradoConHijos() {
        MyTree<String> arbol = new MyTree<>("A");
        arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(arbol.getRaiz(), "D");
        assertEquals(3, arbol.getGrado());
    }

    @Test
    void getNumNodos() {
        MyTree<String> arbol = new MyTree<>("A");
        Node<String> b = arbol.addChild(arbol.getRaiz(), "B");
        arbol.addChild(arbol.getRaiz(), "C");
        arbol.addChild(b, "D");
        assertEquals(4, arbol.getNumNodos());
    }

    @Test
    void arbolAccionesJuego() {
        MyTree<String> arbol = new MyTree<>("Accion");
        Node<String> mov = arbol.addChild(arbol.getRaiz(), "Movimiento");
        arbol.addChild(mov, "Mover arriba");
        arbol.addChild(mov, "Mover abajo");
        arbol.addChild(mov, "Mover izquierda");
        arbol.addChild(mov, "Mover derecha");
        Node<String> inter = arbol.addChild(arbol.getRaiz(), "Interaccion");
        arbol.addChild(inter, "Recoger");
        arbol.addChild(inter, "Usar");
        arbol.addChild(inter, "Abrir");
        Node<String> comb = arbol.addChild(arbol.getRaiz(), "Combate");
        arbol.addChild(comb, "Atacar");

        assertEquals(12, arbol.getNumNodos());
        assertEquals(4, arbol.getGrado());
        assertEquals(3, arbol.getAltura());
        assertNotNull(arbol.buscar("Mover izquierda"));
        assertNotNull(arbol.buscar("Accion"));
        assertNull(arbol.buscar("Saltar"));
    }
}
