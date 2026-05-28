package persistence;

import model.Player;
import Estructuras.MyTree.MyTree;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameLoader {

    public static JsonMapper.GameSaveDTO load(File archivo) {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(archivo)) {
            return gson.fromJson(fr, JsonMapper.GameSaveDTO.class);
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }

    public static void loadAndApply(File archivo, Player jugador,
                                     JsonMapper.RoomDataHolder[] salas,
                                     int maxId, MyTree<String> historial) {
        JsonMapper.GameSaveDTO dto = load(archivo);
        if (dto == null) return;
        JsonMapper.fromGameSave(dto, salas, maxId, historial);
        if (dto.Player != null) {
            JsonMapper.applyPlayerDTO(dto.Player, jugador);
        }
    }
}
