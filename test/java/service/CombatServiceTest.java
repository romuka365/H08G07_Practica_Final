package service;

import Estructuras.MyGraphOBJ.GrafoOBJ;
import Estructuras.MyLinkedList.ListaSE;
import model.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CombatServiceTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE COMBATSERVICE ---\n");

        CombatService servicio = new CombatService();

        Player jugador = new Player<>(); // Atk base = 5
        Enemy enemigo = new Enemy();          // Atk base = 3, MaxHealth = 5

        servicio.dealAtk(jugador, enemigo);
        System.out.println("Prueba 1 (dealAtk Jugador -> Enemigo): Evaluada debido a la aleatoriedad de la defensa.");

        servicio.dealAtk(enemigo, jugador);
        System.out.println("Prueba 2 (dealAtk Enemigo -> Jugador): Evaluada debido a la aleatoriedad de la defensa.");


        Room<Object> habitacion = new Room<>(1, 3, 3);
        GameState estado = new GameState();

        GrafoOBJ mapaSimulado = new GrafoOBJ<>();
        mapaSimulado.addVertice(1, habitacion);
        estado.setMap(mapaSimulado);

        estado.setFilaPlayer(1);
        estado.setColumnaPlayer(1);
        habitacion.fillCell(1, 1, jugador);

        estado.setFilaEnemy(1);
        estado.setColumnaEnemy(2);
        habitacion.fillCell(1, 2, enemigo);


        ListaSE ataquesDisponiblesJugador = servicio.checkAttacks(estado, habitacion);

        if (ataquesDisponiblesJugador.getSize() > 0) {
            System.out.println("Prueba 3 (checkAttacks - Jugador detecta enemigo adyacente): PASADA");
        } else {
            System.out.println("Prueba 3 (checkAttacks - Jugador detecta enemigo adyacente): FALLADA");
        }


        ListaSE ataquesDisponiblesEnemigo = servicio.checkAttacksEnemy(estado, habitacion);

        if (ataquesDisponiblesEnemigo.getSize() > 0) {
            System.out.println("Prueba 4 (checkAttacksEnemy - Enemigo detecta jugador adyacente): PASADA");
        } else {
            System.out.println("Prueba 4 (checkAttacksEnemy - Enemigo detecta jugador adyacente): FALLADA");
        }

        InputStream entradaOriginal = System.in;

        String entradaSimulada = "derecha\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        try {
            servicio.attackEnemy(jugador, ataquesDisponiblesJugador, estado, habitacion);
            System.out.println("Prueba 5 (attackEnemy - Simulación de Scanner exitosa): PASADA");
        } catch (Exception e) {
            System.out.println("Prueba 5 (attackEnemy - Simulación de Scanner exitosa): FALLADA. Error: " + e.getMessage());
        } finally {
            System.setIn(entradaOriginal);
        }


        try {
            ListaSE movimientosEnemigoFicticios = new ListaSE();
            movimientosEnemigoFicticios.add("izquierda");

            habitacion.fillCell(1, 1, jugador);

            servicio.attackPlayer(enemigo, movimientosEnemigoFicticios, estado, habitacion);
            System.out.println("Prueba 6 (attackPlayer - Ataque IA del enemigo): PASADA");
        } catch (Exception e) {
            System.out.println("Prueba 6 (attackPlayer - Ataque IA del enemigo): FALLADA. Error: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}