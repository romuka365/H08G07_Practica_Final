package persistence;

import model.Player;
import Estructuras.MyTree.MyTree;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {

    public static void save(File archivo, Player jugador, int habitacionActual,
                             int turno, int pasosUsados, boolean victoria,
                             boolean gameOver,
                             JsonMapper.RoomDataHolder[] salas,
                             int maxId, MyTree<String> historial) {

        JsonMapper.GameSaveDTO dto = JsonMapper.toGameSave(
            jugador, habitacionActual, turno, pasosUsados,
            victoria, gameOver, salas, maxId, historial);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fw = new FileWriter(archivo)) {
            gson.toJson(dto, fw);
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}
