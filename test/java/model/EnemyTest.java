package model;


class EnemyTest {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE ENEMY ---\n");

        Enemy enemigoEstandar = new Enemy();

        if (!enemigoEstandar.isDead()) {
            System.out.println("Prueba 1 (Estado inicial 'dead'): PASADA");
        } else {
            System.out.println("Prueba 1 (Estado inicial 'dead'): FALLADA");
        }

        if (enemigoEstandar.getMaxHealth() == 5 && enemigoEstandar.getCurrentHealth() == 5) {
            System.out.println("Prueba 2 (Salud inicial estándar): PASADA");
        } else {
            System.out.println("Prueba 2 (Salud inicial estándar): FALLADA");
        }

        if (enemigoEstandar.getDef() == 2 && enemigoEstandar.getAtk() == 3 && enemigoEstandar.getMov() == 0) {
            System.out.println("Prueba 3 (Atributos base estándar): PASADA");
        } else {
            System.out.println("Prueba 3 (Atributos base estándar): FALLADA");
        }


        Enemy enemigoPersonalizado = new Enemy(false, 10, 4, 6);

        if (enemigoPersonalizado.getMaxHealth() == 10 && enemigoPersonalizado.getCurrentHealth() == 10) {
            System.out.println("Prueba 4 (Salud inicial personalizada): PASADA");
        } else {
            System.out.println("Prueba 4 (Salud inicial personalizada): FALLADA");
        }

        if (enemigoPersonalizado.getDef() == 4 && enemigoPersonalizado.getAtk() == 6) {
            System.out.println("Prueba 5 (Atributos personalizados): PASADA");
        } else {
            System.out.println("Prueba 5 (Atributos personalizados): FALLADA");
        }

        if (enemigoEstandar.toString().equals("Enemy")) {
            System.out.println("Prueba 6 (Método toString): PASADA");
        } else {
            System.out.println("Prueba 6 (Método toString): FALLADA");
        }

        System.out.println("\nAtacando al enemigo personalizado hasta derrotarlo...");
        int intentos = 0;

        while (!enemigoPersonalizado.isDead() && intentos < 20) {
            enemigoPersonalizado.receiveAtk(50); // Ataque fuerte de 50 puntos
            intentos++;
        }


        if (enemigoPersonalizado.isDead() && enemigoPersonalizado.getCurrentHealth() <= 0) {
            System.out.println("Prueba 7 (Método receiveAtk e isDead): PASADA. Enemigo derrotado con éxito.");
        } else {
            System.out.println("Prueba 7 (Método receiveAtk e isDead): FALLADA. Vida actual: " + enemigoPersonalizado.getCurrentHealth());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }

}