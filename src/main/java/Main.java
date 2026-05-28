import Estructuras.MyGraph.BFSGrafo;
import Estructuras.MyGraph.Grafo;
import Estructuras.MyGraph.Vertice;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.BFSMatriz;
import Estructuras.MyMatrix.Coordenada;
import Estructuras.MyMatrix.MyMatrix;
import Estructuras.MyTree.MyTree;
import Estructuras.MyTree.Node;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Crear habitación como matriz
        MyMatrix<Integer> habitacion = new MyMatrix<>(5, 5, 0);
        BFSMatriz<Integer> bfsMatriz = new BFSMatriz<>(habitacion);

        // Bloqueos de ejemplo
        bfsMatriz.setCeldaBloqueada(1, 1, true);
        bfsMatriz.setCeldaBloqueada(1, 2, true);

        Coordenada jugador = new Coordenada(0, 0);

        // 2. Crear árbol de acciones
        MyTree<String> acciones = new MyTree<>("Accion");
        Node<String> movimiento = acciones.addChild(acciones.getRaiz(), "Movimiento");
        acciones.addChild(movimiento, "Mover arriba");
        acciones.addChild(movimiento, "Mover abajo");
        acciones.addChild(movimiento, "Mover izquierda");
        acciones.addChild(movimiento, "Mover derecha");

        boolean jugando = true;

        while (jugando) {
            System.out.println("\nJugador en: " + jugador);
            System.out.println("Elige acción:");
            System.out.println("1. Mover arriba");
            System.out.println("2. Mover abajo");
            System.out.println("3. Mover izquierda");
            System.out.println("4. Mover derecha");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();

            String accionElegida = null;
            int nuevaFila = jugador.getFila();
            int nuevaColumna = jugador.getColumna();

            if (opcion == 1) {
                accionElegida = "Mover arriba";
                nuevaFila--;
            } else if (opcion == 2) {
                accionElegida = "Mover abajo";
                nuevaFila++;
            } else if (opcion == 3) {
                accionElegida = "Mover izquierda";
                nuevaColumna--;
            } else if (opcion == 4) {
                accionElegida = "Mover derecha";
                nuevaColumna++;
            } else if (opcion == 0) {
                jugando = false;
                continue;
            }

            if (accionElegida != null && acciones.buscar(accionElegida) != null) {
                if (habitacion.posicionValida(nuevaFila, nuevaColumna)
                        && !bfsMatriz.estaBloqueada(nuevaFila, nuevaColumna)) {

                    jugador = new Coordenada(nuevaFila, nuevaColumna);
                    System.out.println("Acción realizada: " + accionElegida);

                } else {
                    System.out.println("No puedes moverte ahí.");
                }
            } else {
                System.out.println("Acción no válida.");
            }
        }

        System.out.println("Fin del juego.");


        Grafo grafo = new Grafo();

        grafo.addVertice("Habitacion", "H1");
        grafo.addVertice("Habitacion", "H2");
        grafo.addVertice("Salida", "H3");

        grafo.addArista("Habitacion", "H1", "Habitacion", "H2", "puerta");
        grafo.addArista("Habitacion", "H2", "Salida", "H3", "puerta");

        BFSGrafo bfsGrafo = new BFSGrafo(grafo);
        ListaSE<Vertice> caminoSalida = bfsGrafo.buscarSalida("Habitacion", "H1", "Salida");

        System.out.println("Camino hacia la salida calculado con BFSGrafo.");

        String habitacionActual = "H1";

        Coordenada puertaH1 = new Coordenada(4, 4);

        if (habitacionActual.equals("H1") && jugador.equals(puertaH1)) {
            ListaSE<Vertice> camino = bfsGrafo.getCamino("Habitacion", "H1", "Habitacion", "H2");

            if (camino.getSize() > 0) {
                habitacionActual = "H2";
                jugador = new Coordenada(0, 0);
                System.out.println("Has pasado a H2");
            }
        }
    }
}