package model;
import Estructuras.MyGraphOBJ.*;

public class GameStateTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE GAMESTATE ---\n");

        GrafoOBJ mapaJuego = new GrafoOBJ<>();

        Room habitacionInicio = new Room(1,2,2);
        Room habitacionMazmorra = new Room(2,2,2);

        mapaJuego.addVertice(101, habitacionInicio);
        mapaJuego.addVertice(102, habitacionMazmorra);

        GameState estadoJuego = new GameState();
        estadoJuego.setMap(mapaJuego);


        if (estadoJuego.getMap() == mapaJuego) {
            System.out.println("Prueba 1 (Asignación de Mapa): PASADA");
        } else {
            System.out.println("Prueba 1 (Asignación de Mapa): FALLADA");
        }


        Player jugadorSimulado = new Player<>();
        estadoJuego.setPlayer(jugadorSimulado);
        estadoJuego.setFilaPlayer(3);
        estadoJuego.setColumnaPlayer(5);

        if (estadoJuego.getPlayer() == jugadorSimulado) {
            System.out.println("Prueba 2 (Asignación de Jugador): PASADA");
        } else {
            System.out.println("Prueba 2 (Asignación de Jugador): FALLADA");
        }

        if (estadoJuego.getFilaPlayer() == 3 && estadoJuego.getColumnaPlayer() == 5) {
            System.out.println("Prueba 3 (Coordenadas XY del Jugador): PASADA");
        } else {
            System.out.println("Prueba 3 (Coordenadas XY del Jugador): FALLADA");
        }



        estadoJuego.setRoomPlayer(101);
        if (estadoJuego.getRoomPlayer() == habitacionInicio) {
            System.out.println("Prueba 4 (setRoomPlayer buscando en Grafo): PASADA");
        } else {
            System.out.println("Prueba 4 (setRoomPlayer buscando en Grafo): FALLADA");
        }



        Enemy enemigoSimulado = new Enemy();
        estadoJuego.setEnemy(enemigoSimulado);
        estadoJuego.setFilaEnemy(7);
        estadoJuego.setColumnaEnemy(2);

        if (estadoJuego.getEnemy() == enemigoSimulado) {
            System.out.println("Prueba 5 (Asignación de Enemigo): PASADA");
        } else {
            System.out.println("Prueba 5 (Asignación de Enemigo): FALLADA");
        }

        if (estadoJuego.getFilaEnemy() == 7 && estadoJuego.getColumnaEnemy() == 2) {
            System.out.println("Prueba 6 (Coordenadas XY del Enemigo): PASADA");
        } else {
            System.out.println("Prueba 6 (Coordenadas XY del Enemigo): FALLADA");
        }



        estadoJuego.setRoomEnemy(102);

        if (estadoJuego.getRoomEnemy() == habitacionMazmorra) {
            System.out.println("Prueba 7 (setRoomEnemy buscando en Grafo): PASADA");
        } else {
            System.out.println("Prueba 7 (setRoomEnemy buscando en Grafo): FALLADA");
        }


        estadoJuego.setTurnos(14);
        if (estadoJuego.getTurnos() == 14) {
            System.out.println("Prueba 8 (Contador de Turnos): PASADA");
        } else {
            System.out.println("Prueba 8 (Contador de Turnos): FALLADA. Turnos obtenidos: " + estadoJuego.getTurnos());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}