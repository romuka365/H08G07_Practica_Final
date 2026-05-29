package model;

class PlayerTest {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE PLAYER ---\n");

        Player jugador = new Player<>();


        if (!jugador.isDead() && jugador.getCurrentHealth() == 10 && jugador.getMaxHealth() == 10) {
            System.out.println("Prueba 1 (Estado de salud inicial): PASADA");
        } else {
            System.out.println("Prueba 1 (Estado de salud inicial): FALLADA");
        }

        if (jugador.getDef() == 3 && jugador.getAtk() == 5 && jugador.getMov() == 1 && jugador.getMoney() == 0) {
            System.out.println("Prueba 2 (Estadísticas base iniciales): PASADA");
        } else {
            System.out.println("Prueba 2 (Estadísticas base iniciales): FALLADA");
        }


        if (jugador.getInventory() != null && jugador.getEquipped() == null) {
            System.out.println("Prueba 3 (Inventario y Equipamiento inicial): PASADA");
        } else {
            System.out.println("Prueba 3 (Inventario y Equipamiento inicial): FALLADA");
        }



        jugador.takeDamage(4);
        if (jugador.getCurrentHealth() == 6) {
            System.out.println("Prueba 4 (takeDamage - Recibir daño): PASADA");
        } else {
            System.out.println("Prueba 4 (takeDamage - Recibir daño): FALLADA. Salud: " + jugador.getCurrentHealth());
        }


        jugador.heal(6);
        System.out.println("Prueba 5 (heal - Control de límites): Evaluada. Salud tras curación: " + jugador.getCurrentHealth());



        jugador.gainDef(2); // 3 + 2 = 5
        jugador.loseDef(10); // 5 - 10 = -5 -> Debería quedar en 0 por tu control de límites
        if (jugador.getDef() == 0) {
            System.out.println("Prueba 6 (Gestión de Defensa y límite cero): PASADA");
        } else {
            System.out.println("Prueba 6 (Gestión de Defensa y límite cero): FALLADA. Defensa: " + jugador.getDef());
        }

        jugador.gainAtk(5); // 5 + 5 = 10
        jugador.loseAtk(3); // 10 - 3 = 7
        if (jugador.getAtk() == 7) {
            System.out.println("Prueba 7 (Gestión de Ataque): PASADA");
        } else {
            System.out.println("Prueba 7 (Gestión de Ataque): FALLADA");
        }



        jugador.gainMov(3); // 1 + 3 = 4
        jugador.loseMov(5); // 4 - 5 = -1 -> Debería quedar en 1 por tu regla (mov <= 0 -> mov = 1)
        if (jugador.getMov() == 1) {
            System.out.println("Prueba 8 (Gestión de Movimiento y límite mínimo 1): PASADA");
        } else {
            System.out.println("Prueba 8 (Gestión de Movimiento y límite mínimo 1): FALLADA. Mov: " + jugador.getMov());
        }



        jugador.gainMoney(50);
        boolean transaccionNegada = jugador.spendMoney(100); // No debería dejarle gastar más de lo que tiene
        boolean transaccionAceptada = jugador.spendMoney(30); // Debería dejarle gastar y restar el dinero

        if (!transaccionNegada && transaccionAceptada && jugador.getMoney() == 20) {
            System.out.println("Prueba 9 (Gestión de Monedas y transacciones): PASADA");
        } else {
            System.out.println("Prueba 9 (Gestión de Monedas y transacciones): FALLADA. Dinero restante: " + jugador.getMoney());
        }



        System.out.println("\nAtacando al jugador repetidamente para probar receiveAtk e isDead...");
        int intentos = 0;
        while (!jugador.isDead() && intentos < 20) {
            jugador.receiveAtk(40); // Ataques masivos para vencer la aleatoriedad
            intentos++;
        }

        if (jugador.isDead()) {
            System.out.println("Prueba 10 (receiveAtk e isDead): PASADA. Jugador derrotado.");
        } else {
            System.out.println("Prueba 10 (receiveAtk e isDead): FALLADA. Salud restante: " + jugador.getCurrentHealth());
        }

        if (jugador.toString().equals("Player")) {
            System.out.println("Prueba 11 (Método toString): PASADA");
        } else {
            System.out.println("Prueba 11 (Método toString): FALLADA");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}