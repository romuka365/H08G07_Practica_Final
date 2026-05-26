package MyTree;

import MyLinkedList.ElementoSE;
import MyLinkedList.ListaSE;

public class MainArbolBinarioDeBusquedaAleatorio {
    public static void main(String[] args) {

        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();

        System.out.println("===== INSERTANDO DATOS =====");
        int[] datos = {5, 2, 8, 1, 3, 7, 9, 6};
        for (int d : datos) {
            arbol.add(d);
            System.out.println("Insertado: " + d);
        }

        System.out.println("\n===== RECORRIDOS =====");
        System.out.println("Orden Central (inOrden): " + arbol.getListaOrdenCentral());
        System.out.println("PreOrden: " + arbol.getListaPreOrden());
        System.out.println("PostOrden: " + arbol.getListaPostOrden());

        System.out.println("\n===== ALTURA Y GRADO =====");
        System.out.println("Altura del árbol: " + arbol.getAltura());
        System.out.println("Grado del árbol: " + arbol.getGrado());

        System.out.println("\n===== SUBÁRBOLES =====");
        System.out.println("Subárbol izquierdo (inOrden): " +
                arbol.getSubArbolIzquierda().getListaOrdenCentral());
        System.out.println("Subárbol derecho (inOrden): " +
                arbol.getSubArbolDerecha().getListaOrdenCentral());

        System.out.println("\n===== DATOS POR NIVEL =====");
        for (int nivel = 1; nivel <= arbol.getAltura(); nivel++) {
            System.out.println("Nivel " + nivel + ": " + arbol.getListaDatosNivel(nivel));
        }

        System.out.println("\n===== CAMINO HASTA UN DATO =====");
        int buscado = 6;
        System.out.println("Camino hasta " + buscado + ": " + arbol.getCamino(buscado));

        System.out.println("\n===== PROPIEDADES DEL ÁRBOL =====");
        System.out.println("¿Es homogéneo?: " + arbol.isArbolHomogeneo());
        System.out.println("¿Es completo?: " + arbol.isArbolCompleto());
        System.out.println("¿Es casi completo?: " + arbol.isArbolCasiCompleto());

        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");


        ArbolBinarioDeBusquedaEnteros arbolSuma = new ArbolBinarioDeBusquedaEnteros();

        // 1. Insertar números de 0 a 128 en orden
        for (int i = 0; i <= 128; i++) {
            arbolSuma.add(i);
        }

        // 2. Calcular suma con getSuma()
        int suma = arbolSuma.getSuma();
        System.out.println("Suma total del árbol = " + suma);

        // 3. Verificar suma con los 3 recorridos
        int sumaIn = sumarLista(arbolSuma.getListaOrdenCentral());
        int sumaPre = sumarLista(arbolSuma.getListaPreOrden());
        int sumaPost = sumarLista(arbolSuma.getListaPostOrden());

        System.out.println("Suma en inOrden = " + sumaIn);
        System.out.println("Suma en preOrden = " + sumaPre);
        System.out.println("Suma en postOrden = " + sumaPost);

        // 4. Verificar suma con subárboles
        int sumaIz = arbolSuma.getSubArbolIzquierda().getSuma();
        int sumaDr = arbolSuma.getSubArbolDerecha().getSuma();

        System.out.println("Suma izquierda + derecha = " + (sumaIz + sumaDr));

        // 5. Altura del árbol
        System.out.println("Altura del árbol = " + arbolSuma.getAltura());

        // 6. Camino hasta 110
        ListaSE<Integer> caminoArbol = arbolSuma.getCamino(110);
        System.out.println("Longitud del camino = " + (caminoArbol.getSize() - 1));
        System.out.print("Camino hasta 110 = ");
        imprimirLista(caminoArbol);
        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");

        ArbolBinarioDeBusquedaEnteros arbolSumaAleatoria = new ArbolBinarioDeBusquedaEnteros();

        // 1. Crear array con 0..128
        int[] numeros = new int[129];
        for (int i = 0; i <= 128; i++) {
            numeros[i] = i;
        }

        // 2. Mezclar aleatoriamente (Fisher–Yates)
        java.util.Random r = new java.util.Random();
        for (int i = numeros.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }

        // 3. Insertar en el ABB
        for (int n : numeros) {
            arbolSumaAleatoria.add(n);
        }

        // 2. Calcular suma con getSuma()
        int sumaAleatoria = arbolSumaAleatoria.getSuma();
        System.out.println("Suma total del árbol = " + sumaAleatoria);

        // 3. Verificar suma con los 3 recorridos
        int sumaIn2 = sumarLista(arbolSumaAleatoria.getListaOrdenCentral());
        int sumaPre2 = sumarLista(arbolSumaAleatoria.getListaPreOrden());
        int sumaPost2 = sumarLista(arbolSumaAleatoria.getListaPostOrden());

        System.out.println("Suma en inOrden = " + sumaIn2);
        System.out.println("Suma en preOrden = " + sumaPre2);
        System.out.println("Suma en postOrden = " + sumaPost2);

        // 4. Verificar suma con subárboles
        int sumaIz2 = arbolSumaAleatoria.getSubArbolIzquierda().getSuma();
        int sumaDr2 = arbolSumaAleatoria.getSubArbolDerecha().getSuma();

        System.out.println("Suma izquierda + derecha = " + (sumaIz2 + sumaDr2));

        // 5. Altura del árbol
        System.out.println("Altura del árbol = " + arbolSumaAleatoria.getAltura());

        // 6. Camino hasta 110
        ListaSE<Integer> caminoArbolAleatorio = arbolSumaAleatoria.getCamino(110);
        System.out.println("Longitud del camino = " + (caminoArbolAleatorio.getSize() - 1));
        System.out.print("Camino hasta 110 = ");
        imprimirLista(caminoArbolAleatorio);
        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");

        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL ALEATORIO =====");
    }

    // Función auxiliar para sumar una ListaSE
    private static int sumarLista(ListaSE<Integer> lista) {
        int suma = 0;
        for (int i = 0; i < lista.getSize(); i++) {
            suma += lista.get(i);
        }
        return suma;
    }

    public static void imprimirLista(ListaSE<Integer> lista) {
        System.out.print("[");
        ElementoSE<Integer> puntero = lista.getPrimero();
        while (puntero != null) {
            System.out.print(puntero.getDato());
            puntero = puntero.getSiguiente();
            if (puntero != null) System.out.print(", ");
        }
        System.out.println("]");
    }
}
