package model;

class BootsTest {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE BOOTS ---\n");

        Boots botasDefecto = new Boots();

        if (botasDefecto.getPlacement().equals("feet")) {
            System.out.println("Prueba 1 (Placement por defecto): PASADA");
        } else {
            System.out.println("Prueba 1 (Placement por defecto): FALLADA. Se esperaba 'feet' pero se obtuvo: " + botasDefecto.getPlacement());
        }

        if (botasDefecto.getMovBuff() == 1) {
            System.out.println("Prueba 2 (MovBuff por defecto): PASADA");
        } else {
            System.out.println("Prueba 2 (MovBuff por defecto): FALLADA. Se esperaba 1 pero se obtuvo: " + botasDefecto.getMovBuff());
        }


        Boots botasPersonalizadas = new Boots("feet", 5, true, true);

        if (botasPersonalizadas.getPlacement().equals("feet")) {
            System.out.println("Prueba 3 (Placement personalizado): PASADA");
        } else {
            System.out.println("Prueba 3 (Placement personalizado): FALLADA");
        }

        if (botasPersonalizadas.getMovBuff() == 5) {
            System.out.println("Prueba 4 (MovBuff personalizado): PASADA");
        } else {
            System.out.println("Prueba 4 (MovBuff personalizado): FALLADA. Se esperaba 5 pero se obtuvo: " + botasPersonalizadas.getMovBuff());
        }


        if (botasDefecto.toString().equals("Boots")) {
            System.out.println("Prueba 5 (Método toString): PASADA");
        } else {
            System.out.println("Prueba 5 (Método toString): FALLADA. Se esperaba 'Boots' pero se obtuvo: " + botasDefecto.toString());
        }


        if (botasDefecto.compareTo(botasPersonalizadas) == 0) {
            System.out.println("Prueba 6 (Método compareTo): PASADA");
        } else {
            System.out.println("Prueba 6 (Método compareTo): FALLADA. Se esperaba 0.");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }

}