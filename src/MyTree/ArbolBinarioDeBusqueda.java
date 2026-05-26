package MyTree;

import MyQueue.Cola;
import MyLinkedList.ListaSE;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    //Atributos:
    private Nodo<T> raiz;

    //Constructor:
    public ArbolBinarioDeBusqueda() {
        this.raiz = null;
    }

    //GetRaiz:
    public Nodo<T> getRaiz(){
        return raiz;
    }

    //SetRaiz:
    public void setRaiz(Nodo<T> newRaiz){
        this.raiz = newRaiz;
    }

    //Add:
    public void add(T dato) {
        raiz = addRec(raiz, dato); //Empezamos a buscar por la raiz y vamos bajando.
    }

    private Nodo<T> addRec(Nodo<T> actual, T dato) { //Lo marcamos como private, ya que solo lo queremos para que lo pueda utilizar add.
        if (actual == null) {
            return new Nodo<>(dato); //Creamos el nodo donde corresponda.
        }

        int comparador = dato.compareTo(actual.getDato());
        //Si el comparador es menor a 0 ==> el dato es menor al nodo en el que se encuentra (Baja por la izquierda).
        //Si el comparador es mayor a 0 ==> el dato es mayor al nodo en el que se encuentra (Baja por la derecha).

        if (comparador < 0){
            actual.setHijoIz(addRec(actual.getHijoIz(), dato)); //Bajamos al hijo izquierdo.
        }
        else if (comparador > 0){
            actual.setHijoDr(addRec(actual.getHijoDr(), dato)); //Bajamos al hijo derecho.
        }
        //Si se diera que comparador = 0 es que el dato es igual a un nodo ya existente, y por lo tanto no se añade.

        return actual;
    }

    //GetSubArbolIzquierda y GetSubArbolDerecha:
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda(){
        ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<T>(); //Creamos un nuevo arbol para el subarbol izquierdo.

        if (raiz != null) { //Si el árbol no está vacío, la raíz del subarbol será el hijo izquierdo de la raiz del árbol original.
            subArbol.raiz = raiz.getHijoIz();
        }
        else {
            subArbol.raiz = null; //Si el árbol original está vacío no hay subárbol.
        }

        return subArbol; //Devuelve el subárbol izquierdo.
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha(){
        ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>(); //Creamos un nuevo arbol para el subarbol derecho.

        if (raiz != null) { //Si el árbol no está vacío, la raíz del subarbol será el hijo derecho de la raiz del árbol original.
            subArbol.raiz = raiz.getHijoDr();
        }
        else {
            subArbol.raiz = null; //Si el árbol original está vacío no hay subárbol.
        }

        return subArbol; //Devuelve el subárbol izquierdo.
    }

    //GetAltura y GetGrado:
    public int getAltura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo<T> actual){ //Metodo privado auxiliar para la altura.
        if (actual == null){
            return 0; //Si se da este caso, es que el árbol está vacío o el nodo actual no tiene hijo.
        }

        int alturaIz = alturaRec(actual.getHijoIz()); //Calculamos la altura del arbol por la izquierda.
        int alturaDr = alturaRec(actual.getHijoDr()); //Calculamos la altura del arbol por la derecha.

        return 1 + Math.max(alturaIz, alturaDr); //Devuelve la mayor de las alturas (Entre la izquierda y derecha) y se le suma el nodo raiz
    }

    public int getGrado() {
        return gradoRec(raiz);
    }

    private int gradoRec(Nodo<T> actual){ //Metodo privado auxiliar para obtener el grado.
        if (actual == null){
            return 0; //Si se da este caso, es que el árbol está vacío.
        }

        int hijos = 0; //Inicializamos la cantidad de hijos del nodo actual como 0 (Y su valor variará entre 0, 1 y 2).
        if (actual.getHijoIz() != null){ //Si tiene hijo por la izquierda aumenta en 1 el nº de hijos.
            hijos++;
        }
        if (actual.getHijoDr() != null){ //Si tiene hijo por la derecha aumenta en 1 el nº de hijos.
            hijos++;
        }

        //Calculamos recursivamente el grado de los subnodos:
        int gradoIz = gradoRec(actual.getHijoIz()); // Calculamos el grado de los nodos del subárbol izquierdo.
        int gradoDr = gradoRec(actual.getHijoDr()); // Calculamos el grado de los nodos del subárbol derecho.

        return Math.max(hijos, Math.max(gradoIz, gradoDr)); //Devuelve el grado maximo entre el nodo raiz y el grado mayor entre el subárbol izquierdo y derecho.
    }

    //Datos por nivel:
    public ListaSE<T> getListaDatosNivel(int nivel){
        ListaSE<T> lista = new ListaSE<>(); //Creamos una lista simplemente enlazada.
        datosNivelRec(raiz, nivel, lista); //Empezamos por la raíz.
        return lista; //Devolvemos la lista con los nodos que esten en el nivel n;
    }

    private void datosNivelRec(Nodo<T> actual, int nivel, ListaSE<T> lista){ //Metodo privado auxiliar para obtener los datos para n nivel.
        if (actual == null){
            return; //Si no hay nodo no hacemos nada.
        }

        if (nivel == 1){
            lista.add(actual.getDato()); //Si nivel == 1 es que nos encontramos en el nivel deseado (Ya que empezamos siempre a contar desde la raiz).
        }
        else{
            //Como no hemos llegado al nivel actual bajamos uno (por ambos lados):
            datosNivelRec(actual.getHijoIz(), nivel - 1, lista); //Bajamos y repetimos el proceso por la izquierda.
            datosNivelRec(actual.getHijoDr(), nivel - 1, lista); //Bajamos y repetimos el proceso por la derecha.
        }
    }

    //Homogeneidad:
    public boolean isArbolHomogeneo() {
        int grado = getGrado(); //Este será el grado que deben de tener todos los nodos del árbol.
        return isArbolHomogeneoRec(raiz, grado);
    }

    private boolean isArbolHomogeneoRec(Nodo<T> actual, int gradoEsperado){ //GradoEsperado es el grado que en teoria deberian tener todos los nodos del árbol.
        if (actual == null){
            return true; //Si el árbol está vacío es homogeneo (Ya que todos los nodos [ninguno] tienen grado 0)
        }

        int hijos = 0; //Inicializamos la cantidad de hijos del nodo actual como 0 (Y su valor variará entre 0, 1 y 2).
        if (actual.getHijoIz() != null){ //Si tiene hijo por la izquierda aumenta en 1 el nº de hijos.
            hijos++;
        }
        if (actual.getHijoDr() != null){ //Si tiene hijo por la derecha aumenta en 1 el nº de hijos.
            hijos++;
        }

        if (hijos != gradoEsperado){
            return false; //Si la cantidad de hijos de algun nodo no coincide con el grado del árbol, entonces no es homogéneo.
        }

        //Comprobamos todos los nodos tanto del subárbol izquierdo como del derecho.
        return isArbolHomogeneoRec(actual.getHijoIz(), gradoEsperado) && isArbolHomogeneoRec(actual.getHijoDr(), gradoEsperado);
    }

    //Árbol comleto y casi completo:
    public boolean isArbolCompleto(){
        int altura = getAltura(); //Está es la altura a la que deben de estar todas las hojas.
        return isArbolCompletoRec(raiz, 1, altura);
    }

    private boolean isArbolCompletoRec(Nodo<T> actual, int nivelActual, int alturaEsperada){ //AlturaEsperada es la altura que en teoria deberian tener todos los nodos del árbol.
        if (actual == null){
            return true; //Un árbol vacío es completo ya que todos sus hijos (ninguno) tienen altura 0.
        }

        boolean esHoja = (actual.getHijoIz() == null && actual.getHijoDr() == null); //Si no tiene hijos es un nodo hoja.

        if (esHoja) {
            return nivelActual == alturaEsperada; //Comprueba si la el nodo hoja se encuentra en el nivel esperado.
        }

        if (actual.getHijoIz() == null || actual.getHijoDr() == null) {
            return false;  // Un nodo interno debe tener 2 hijos
        }

        //Si no es hoja seguimos bajando por ambos subárboles:
        return isArbolCompletoRec(actual.getHijoIz(), nivelActual + 1, alturaEsperada)
                && isArbolCompletoRec(actual.getHijoDr(), nivelActual + 1, alturaEsperada);
    }

    public boolean isArbolCasiCompleto() {
        if (raiz == null) {
            return true; //Un árbol vacío es casi completo, ya que todas las hojas (ninguna) están en el mismo nivel y no hay huecos entre ellas.
        }

        Cola<Nodo<T>> cola = new Cola<Nodo<T>>(); //Creamos una cola.
        cola.encolar(raiz);

        boolean hayHueco = false;

        while (!cola.isEmpty()) { //Mientras la cola no este vacía sacamos elementos (FIFO)
            Nodo<T> actual = cola.desencolar();

            if (actual == null) {
                //Si encontramos un hueco, marcamos que ya no pueden aparecer más nodos después.
                hayHueco = true;
            }
            else { //Si hay un hueco entre los nodos hojas de nivel mas bajo, el árbol ya no es casi completo.
                if (hayHueco) {
                    return false;
                }

                //Recorremos ambos sub hijos aunque sean null (para detectar huecos)
                cola.encolar(actual.getHijoIz());
                cola.encolar(actual.getHijoDr());
            }
        }

        return true;
    }

    //GetCamino:
    public ListaSE<T> getCamino(T dato) {
        ListaSE<T> lista = new ListaSE<>(); //Creamos una lista para el camino.
        getCaminoRec(raiz, dato, lista);
        return lista;
    }

    private boolean getCaminoRec(Nodo<T> actual, T dato, ListaSE<T> camino) { //True --> Pertenece al caminno, False --> no pertenece.
        if (actual == null) {
            return false; //Si el nodo no existe es que no hay camino en esa direccion.
        }

        camino.add(actual.getDato()); //Añadimos el nodo actual a la lista del camino. (Sea o no parte del camino)

        int comparador = dato.compareTo(actual.getDato());

        if (comparador == 0) {
            return true;
        }

        // Si el dato es menor → solo puede estar en la izquierda
        else if (comparador < 0) {
            if (getCaminoRec(actual.getHijoIz(), dato, camino)) {
                return true;
            }
        }

        // Si el dato es mayor → solo puede estar en la derecha
        else {
            if (getCaminoRec(actual.getHijoDr(), dato, camino)) {
                return true;
            }
        }

        // Si no está en este camino, lo quitamos
        camino.del(actual.getDato());
        return false;
    }


    //Órdenes:
    public ListaSE<T> getListaOrdenCentral(){
        ListaSE<T> lista = new ListaSE<>(); //Creamos una lista simplemente enlazada.
        ordenCentralRec(raiz, lista); //Empezamos por la raiz.
        return lista; //Devolvemos la lista con el Orden Central.
    }

    private void ordenCentralRec(Nodo<T> actual, ListaSE<T> lista){ //Metodo privado auxiliar para el orden central
        if (actual == null){ //Si el nodo actual significa que hemos acabado, y paramos.
            return;
        }

        //Proceso recursivo.
        ordenCentralRec(actual.getHijoIz(), lista);
        lista.add(actual.getDato()); //Añadimos el dato actual a la lista.
        ordenCentralRec(actual.getHijoDr(), lista);
    }

    public ListaSE<T> getListaPreOrden(){
        ListaSE<T> lista = new ListaSE<>(); //Creamos una lista simplemente enlazada.
        preOrdenRec(raiz, lista); //Empezamos por la raiz.
        return lista; //Devolvemos la lista con el PreOrden.
    }

    private void preOrdenRec(Nodo<T> actual, ListaSE<T> lista){ //Metodo privado auxiliar para el preorden
        if (actual == null){ //Si el nodo actual significa que hemos acabado, y paramos.
            return;
        }

        //Proceso recursivo.
        lista.add(actual.getDato()); //Añadimos el dato actual a la lista.
        preOrdenRec(actual.getHijoIz(), lista);
        preOrdenRec(actual.getHijoDr(), lista);
    }

    public ListaSE<T> getListaPostOrden(){
        ListaSE<T> lista = new ListaSE<>(); //Creamos una lista simplemente enlazada.
        postOrdenRec(raiz, lista); //Empezamos por la raiz.
        return lista; //Devolvemos la lista con el PostOrden.
    }

    private void postOrdenRec(Nodo<T> actual, ListaSE<T> lista){ //Metodo privado auxiliar para el postorden
        if (actual == null){ //Si el nodo actual significa que hemos acabado, y paramos.
            return;
        }

        //Proceso recursivo.
        postOrdenRec(actual.getHijoIz(), lista);
        postOrdenRec(actual.getHijoDr(), lista);
        lista.add(actual.getDato()); //Añadimos el dato actual a la lista.
    }

    public boolean isEmpty(){
        return raiz == null; //Si no hay raiz, es que el árbol está vacío.
    }

}
