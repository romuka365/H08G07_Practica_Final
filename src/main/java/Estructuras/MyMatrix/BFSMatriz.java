package Estructuras.MyMatrix;

import Estructuras.MyQueue.Cola;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;

public class BFSMatriz<T> {
    //Atributos:
    private MyMatrix<T> matriz;
    private boolean[][] bloqueado;

    //Constructor:
    public BFSMatriz(MyMatrix<T> matriz) {
        //Si no hay matriz, no se puede calcular el BFS, por lo que salta la excepción.
        if (matriz == null) {
            throw new IllegalArgumentException("No existe la matriz");
        }
        this.matriz = matriz;
        int f = matriz.getFilas();
        int c = matriz.getColumnas();
        this.bloqueado = new boolean[f][c]; //Matriz paralela a la que tengamos que nos dice si una casilla está bloqueada (true) o no (false)
    }

    //Setter:
    public void setCeldaBloqueada(int fila, int columna, boolean bloquear) {
        //Si la casilla elegida no es válida, salta la excepción.
        if (!matriz.posicionValida(fila, columna)) {
            throw new IndexOutOfBoundsException("Posición fuera de la matriz");
        }
        //Si la casilla elegida es válida se bloquea (Pasa de false a true) o se desbloquea (Pasa de true a false)
        if (matriz.posicionValida(fila, columna)) {
            bloqueado[fila][columna] = bloquear;
        }
    }

    //Otros métodos:
    public boolean estaBloqueada(int fila, int columna) {
        if (!matriz.posicionValida(fila, columna)) return true; //Si la casilla está fuera del rango de la matriz, la contamos como bloqueda.
        return bloqueado[fila][columna]; //True si está bloqueada, false si no.
    }

    public ListaSE<Coordenada> casillasAlcanzables(Coordenada inicio, int maxMovimiento) {
        //Si el máximo de movimientos es negativo, salta está excepción.
        if (maxMovimiento < 0) {
            throw new IllegalArgumentException("El movimiento máximo no puede ser negativo");
        }

        ListaSE<Coordenada> resultado = new ListaSE<>(); //Lista de casillas alcanzables.
        int filas = matriz.getFilas();
        int columnas = matriz.getColumnas();
        boolean[][] visitado = new boolean[filas][columnas]; //Si una casilla ha sido ya visitada true, si no false.
        int[][] distancia = new int[filas][columnas]; //Distancia es la cantidad de movimientos realizados desde la casilla inicial hasta este momento.

        //Casilla inicial.
        if (inicio == null) { //Si la casilla inicial es null, salta la excepción.
            throw new IllegalArgumentException("La coordenada inicial no puede ser null");
        }
        int fi = inicio.getFila();
        int ci = inicio.getColumna();
        //Si la posición inicial no es válida o está bloqueada, devuelve una lista vacía (No ha habido movimientos).
        if (!matriz.posicionValida(fi, ci) || estaBloqueada(fi, ci)) {
            return resultado;
        }

        visitado[fi][ci] = true; //Casilla inicial ya visitada.
        distancia[fi][ci] = 0; //Movimientos realizados desde la casilla inicial = 0.
        Cola<Coordenada> cola = new Cola<>();
        cola.encolar(inicio);

        //Si hay 0 o más movimientos, la casilla inicial es alcanzable.
        if (maxMovimiento >= 0) {
            resultado.add(inicio);
        }

        //While sigue hasta que se vacíe la cola por completo.
        while (cola.getSize() > 0) {
            Coordenada actual = cola.desencolar(); //Saca el primer elemento de la cola.
            int d = distancia[actual.getFila()][actual.getColumna()]; //Actualiza los movimientos realizados desde la casilla inicial a la actual.

            ListaSE<Coordenada> vecinos = matriz.getVecinos(actual.getFila(), actual.getColumna()); //Lista de vecinos de la casilla actual que hay que revisar.

            //Iterador se encargará de revisar todos los vecinos de la lista actual.
            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();

                //Posición del vecino.
                int vf = v.getFila();
                int vc = v.getColumna();

                //Si el vecino no ha sido visitado, no es una casilla bloqueada y la distancia no supera los movimientos máximos permitidos entra en el if.
                if (!visitado[vf][vc] && !bloqueado[vf][vc] && d + 1 <= maxMovimiento) {
                    visitado[vf][vc] = true; //Marca al vecino como visitado.
                    distancia[vf][vc] = d + 1; //Suma un movimiento a la distancia.
                    cola.encolar(v); //Añade el vecino a la cola.
                    resultado.add(v); //Añade el vecino a la lista de casillas alcanzables.
                }
            }
        }

        return resultado; //Devuelve la lista de casillas alcanzables.
    }

        public int distanciaMinima(Coordenada origen, Coordenada destino) {
        if (origen == null || destino == null) return -1;
        int filas = matriz.getFilas();
        int cols = matriz.getColumnas();
        if (!matriz.posicionValida(origen.getFila(), origen.getColumna())) return -1;
        if (!matriz.posicionValida(destino.getFila(), destino.getColumna())) return -1;
        if (estaBloqueada(origen.getFila(), origen.getColumna())) return -1;
        if (estaBloqueada(destino.getFila(), destino.getColumna())) return -1;
        if (origen.equals(destino)) return 0;

        boolean[][] visitado = new boolean[filas][cols];
        int[][] dist = new int[filas][cols];
        Cola<Coordenada> cola = new Cola<>();
        visitado[origen.getFila()][origen.getColumna()] = true;
        cola.encolar(origen);

        while (cola.getSize() > 0) {
            Coordenada actual = cola.desencolar();
            ListaSE<Coordenada> vecinos = matriz.getVecinos(actual.getFila(), actual.getColumna());
            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();
                int vf = v.getFila(), vc = v.getColumna();
                if (!visitado[vf][vc] && !bloqueado[vf][vc]) {
                    visitado[vf][vc] = true;
                    dist[vf][vc] = dist[actual.getFila()][actual.getColumna()] + 1;
                    if (v.equals(destino)) return dist[vf][vc];
                    cola.encolar(v);
                }
            }
        }
        return -1;
    }

    public ListaSE<Coordenada> caminoMinimo(Coordenada origen, Coordenada destino) {
        ListaSE<Coordenada> resultado = new ListaSE<>();
        if (origen == null || destino == null) return resultado;
        int filas = matriz.getFilas();
        int cols = matriz.getColumnas();
        if (!matriz.posicionValida(origen.getFila(), origen.getColumna())) return resultado;
        if (!matriz.posicionValida(destino.getFila(), destino.getColumna())) return resultado;
        if (estaBloqueada(origen.getFila(), origen.getColumna())) return resultado;
        if (estaBloqueada(destino.getFila(), destino.getColumna())) return resultado;
        if (origen.equals(destino)) { resultado.add(origen); return resultado; }

        boolean[][] visitado = new boolean[filas][cols];
        Coordenada[][] padre = new Coordenada[filas][cols];
        Cola<Coordenada> cola = new Cola<>();
        visitado[origen.getFila()][origen.getColumna()] = true;
        cola.encolar(origen);
        boolean encontrado = false;

        while (cola.getSize() > 0 && !encontrado) {
            Coordenada actual = cola.desencolar();
            ListaSE<Coordenada> vecinos = matriz.getVecinos(actual.getFila(), actual.getColumna());
            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();
                int vf = v.getFila(), vc = v.getColumna();
                if (!visitado[vf][vc] && !bloqueado[vf][vc]) {
                    visitado[vf][vc] = true;
                    padre[vf][vc] = actual;
                    if (v.equals(destino)) { encontrado = true; break; }
                    cola.encolar(v);
                }
            }
        }

        if (!encontrado) return resultado;
        Coordenada p = destino;
        while (p != null) {
            resultado.addInicio(p);
            if (p.equals(origen)) break;
            p = padre[p.getFila()][p.getColumna()];
        }
        return resultado;
}
