package persistence;

import model.*;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;
import Estructuras.MyMatrix.BFSMatriz;
import Estructuras.MyMatrix.Coordenada;
import Estructuras.MyTree.MyTree;
import Estructuras.MyTree.Node;

public class JsonMapper {

    // ==================== Holder para datos de sala ====================

    public static class RoomDataHolder {
        public Room room;
        public BFSMatriz<Cell> bfs;
        public ListaSE<Enemy> enemigos;
        public Coordenada salida;
        public Door puerta;
        public String nombre;

        public boolean estaBloqueada(int f, int c) {
            return bfs != null && bfs.estaBloqueada(f, c);
        }

        public boolean isPuertaBloqueada() {
            return puerta != null && puerta.getLocked();
        }

        public void setPuertaBloqueada(boolean locked) {
            if (puerta != null) puerta.setLocked(locked);
        }
    }

    // ==================== DTOs para Gson ====================

    public static class GameSaveDTO {
        public int version;
        public PlayerDTO Player;
        public int habitacionActual;
        public int turno;
        public int pasosUsados;
        public boolean victoria;
        public boolean gameOver;
        public RoomSaveDTO[] Room;
        public HistorialNodeDTO historial;
    }

    public static class PlayerDTO {
        public boolean death;
        public int maxHealth;
        public int currentHealth;
        public int def;
        public int atk;
        public int money;
        public int mov;
        public int fila;
        public int columna;
        public ItemSaveDTO[] inventory;
    }

    public static class RoomSaveDTO {
        public String name;
        public int filas;
        public int columnas;
        public String[][] datos;
        public EnemySaveDTO[] enemigos;
        public boolean puertaBloqueada;
    }

    public static class EnemySaveDTO {
        public boolean dead;
        public int currentHealth;
        public int maxHealth;
        public int def;
        public int atk;
        public int fila;
        public int columna;
    }

    public static class ItemSaveDTO {
        public String type;
        public int heal;
        public boolean used;
        public String placement;
        public int atkBuff;
        public int defBuff;
        public int movBuff;
        public boolean equipped;
    }

    public static class HistorialNodeDTO {
        public String dato;
        public HistorialNodeDTO[] hijos;
    }

    // ==================== Serializacion: modelo -> DTO ====================

    public static GameSaveDTO toGameSave(Player jugador,
                                          int habitacionActual,
                                          int turno, int pasosUsados,
                                          boolean victoria, boolean gameOver,
                                          RoomDataHolder[] salas,
                                          int maxId,
                                          MyTree<String> historial) {
        GameSaveDTO dto = new GameSaveDTO();
        dto.version = 1;
        dto.Player = toPlayerDTO(jugador);
        dto.habitacionActual = habitacionActual;
        dto.turno = turno;
        dto.pasosUsados = pasosUsados;
        dto.victoria = victoria;
        dto.gameOver = gameOver;
        dto.Room = new RoomSaveDTO[maxId];
        for (int id = 1; id <= maxId; id++) {
            boolean esActual = (id == habitacionActual);
            Coordenada posJug = esActual ? jugador.getPosicion() : null;
            dto.Room[id - 1] = toRoomDTO(salas[id - 1], posJug, esActual);
        }
        dto.historial = toHistorialDTO(historial.getRaiz());
        return dto;
    }

    private static PlayerDTO toPlayerDTO(Player p) {
        PlayerDTO dto = new PlayerDTO();
        dto.death = p.getDeath();
        dto.maxHealth = p.getMaxHealth();
        dto.currentHealth = p.getCurrentHealth();
        dto.def = p.getDef();
        dto.atk = p.getAtk();
        dto.money = p.getMoney();
        dto.mov = p.getMov();
        dto.fila = p.getPosicion().getFila();
        dto.columna = p.getPosicion().getColumna();

        int tam = p.getInventory().getSize();
        dto.inventory = new ItemSaveDTO[tam];
        Iterador<Item> it = p.getInventory().getIterador();
        int i = 0;
        while (it.hasNext()) {
            dto.inventory[i++] = toItemDTO(it.next());
        }
        return dto;
    }

    private static ItemSaveDTO toItemDTO(Item item) {
        ItemSaveDTO dto = new ItemSaveDTO();
        if (item instanceof Potion) {
            Potion p = (Potion) item;
            dto.type = "Potion";
            dto.heal = p.getHeal();
            dto.used = p.getUsed();
        } else if (item instanceof Weapon) {
            Weapon w = (Weapon) item;
            dto.type = "Weapon";
            dto.placement = w.getPlacement();
            dto.atkBuff = w.getAtkBuff();
            dto.equipped = w.getEquipped();
        } else if (item instanceof Shield) {
            Shield s = (Shield) item;
            dto.type = "Shield";
            dto.placement = s.getPlacement();
            dto.defBuff = s.getDefBuff();
            dto.equipped = s.getEquipped();
        } else if (item instanceof Boots) {
            Boots b = (Boots) item;
            dto.type = "Boots";
            dto.placement = b.getPlacement();
            dto.movBuff = b.getMovBuff();
            dto.equipped = b.getEquipped();
        } else if (item instanceof Key) {
            dto.type = "Key";
        }
        return dto;
    }

    private static RoomSaveDTO toRoomDTO(RoomDataHolder sala,
                                          Coordenada posJugador,
                                          boolean esActual) {
        RoomSaveDTO dto = new RoomSaveDTO();
        dto.name = sala.nombre;
        dto.filas = sala.room.getFilas();
        dto.columnas = sala.room.getColumnas();
        dto.datos = buildGrid(sala, posJugador, esActual);
        dto.enemigos = toEnemyArray(sala.enemigos);
        dto.puertaBloqueada = sala.isPuertaBloqueada();
        return dto;
    }

    private static String[][] buildGrid(RoomDataHolder sala,
                                         Coordenada posJugador,
                                         boolean esActual) {
        int f = sala.room.getFilas();
        int c = sala.room.getColumnas();
        String[][] g = new String[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                g[i][j] = cellType(i, j, sala, posJugador, esActual);
            }
        }
        return g;
    }

    private static String cellType(int fila, int col, RoomDataHolder sala,
                                    Coordenada posJugador, boolean esActual) {
        if (sala.estaBloqueada(fila, col)) return "Wall";
        Coordenada p = new Coordenada(fila, col);
        if (sala.salida != null && sala.salida.equals(p)) return "Exit";
        if (esActual && posJugador != null && posJugador.equals(p)) return "Player";

        if (enemigoVivo(sala.enemigos, p)) return "Enemy";

        Object obj = sala.room.getCell(fila, col).getObjeto();
        if (obj == null) return "Empty";
        if (obj instanceof Potion) return "Potion";
        if (obj instanceof Weapon) return "Weapon";
        if (obj instanceof Shield) return "Shield";
        if (obj instanceof Boots) return "Boots";
        if (obj instanceof Key) return "Key";
        if (obj instanceof Money) return "Money";
        return "Empty";
    }

    private static boolean enemigoVivo(ListaSE<Enemy> enemigos, Coordenada p) {
        if (enemigos == null) return false;
        Iterador<Enemy> it = enemigos.getIterador();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (e.getPosicion().equals(p) && !e.isDead()) return true;
        }
        return false;
    }

    private static EnemySaveDTO[] toEnemyArray(ListaSE<Enemy> enemigos) {
        if (enemigos == null || enemigos.getSize() == 0) return new EnemySaveDTO[0];
        EnemySaveDTO[] arr = new EnemySaveDTO[enemigos.getSize()];
        Iterador<Enemy> it = enemigos.getIterador();
        int i = 0;
        while (it.hasNext()) {
            arr[i++] = toEnemyDTO(it.next());
        }
        return arr;
    }

    private static EnemySaveDTO toEnemyDTO(Enemy e) {
        EnemySaveDTO dto = new EnemySaveDTO();
        dto.dead = e.isDead();
        dto.currentHealth = e.getCurrentHealth();
        dto.maxHealth = e.getMaxHealth();
        dto.def = e.getDef();
        dto.atk = e.getAtk();
        dto.fila = e.getPosicion().getFila();
        dto.columna = e.getPosicion().getColumna();
        return dto;
    }

    private static HistorialNodeDTO toHistorialDTO(Node<String> node) {
        HistorialNodeDTO dto = new HistorialNodeDTO();
        dto.dato = node.getDato();
        int nh = node.getHijos().getSize();
        dto.hijos = new HistorialNodeDTO[nh];
        Iterador<Node<String>> it = node.getHijos().getIterador();
        int i = 0;
        while (it.hasNext()) {
            dto.hijos[i++] = toHistorialDTO(it.next());
        }
        return dto;
    }

    // ==================== Deserializacion: DTO -> modelo ====================

    public static void fromGameSave(GameSaveDTO dto,
                                     RoomDataHolder[] salas,
                                     int maxId,
                                     MyTree<String> historial) {
        if (dto.Room != null) {
            for (RoomSaveDTO r : dto.Room) {
                if (r != null) fromRoomDTO(r, salas, maxId);
            }
        }
        if (dto.historial != null) fromHistorialDTO(dto.historial, historial);
    }

    public static void applyPlayerDTO(PlayerDTO dto, Player p) {
        if (dto == null) return;
        p.setCurrentHealth(dto.currentHealth);
        p.setDef(dto.def);
        p.setAtk(dto.atk);
        p.setMoney(dto.money);
        p.setMov(dto.mov);
        p.setDeath(dto.death);
        p.setPosicion(new Coordenada(dto.fila, dto.columna));
        if (dto.inventory != null) {
            for (ItemSaveDTO idto : dto.inventory) {
                if (idto == null) continue;
                Item item = createItem(idto);
                if (item != null) {
                    item.setIsInInventory(true);
                    p.getInventory().add(item);
                    if (idto.equipped) {
                        if (item instanceof Weapon) p.equipWeapon((Weapon) item);
                        else if (item instanceof Shield) p.equipShield((Shield) item);
                        else if (item instanceof Boots) p.equipBoots((Boots) item);
                    }
                }
            }
        }
    }

    private static Item createItem(ItemSaveDTO dto) {
        if (dto == null || dto.type == null) return null;
        switch (dto.type) {
            case "Potion":
                return new Potion(dto.heal, dto.used, true, null);
            case "Weapon": {
                String p = dto.placement != null ? dto.placement : "hand";
                return new Weapon(p, dto.atkBuff, dto.equipped, true, null);
            }
            case "Shield": {
                String p = dto.placement != null ? dto.placement : "hand";
                return new Shield(p, dto.defBuff, dto.equipped, true, null);
            }
            case "Boots": {
                String p = dto.placement != null ? dto.placement : "feet";
                return new Boots(p, dto.movBuff, dto.equipped, true, null);
            }
            case "Key":
                return new Key(null, true, null);
            default:
                return null;
        }
    }

    private static void fromRoomDTO(RoomSaveDTO dto,
                                     RoomDataHolder[] salas, int maxId) {
        for (int id = 1; id <= maxId; id++) {
            RoomDataHolder h = salas[id - 1];
            if (h != null && h.nombre != null && h.nombre.equals(dto.name)) {
                limpiaCeldasVacias(dto.datos, h);
                actualizaEnemigos(dto.enemigos, h);
                h.setPuertaBloqueada(dto.puertaBloqueada);
                break;
            }
        }
    }

    private static void limpiaCeldasVacias(String[][] datos, RoomDataHolder sala) {
        if (datos == null) return;
        int maxF = Math.min(datos.length, sala.room.getFilas());
        for (int f = 0; f < maxF; f++) {
            if (datos[f] == null) continue;
            int maxC = Math.min(datos[f].length, sala.room.getColumnas());
            for (int c = 0; c < maxC; c++) {
                if ("Empty".equals(datos[f][c])) {
                    sala.room.getCell(f, c).setObjeto(null);
                    sala.room.getCell(f, c).setOccupied(false);
                }
            }
        }
    }

    private static void actualizaEnemigos(EnemySaveDTO[] enemigos,
                                           RoomDataHolder sala) {
        if (enemigos == null || sala.enemigos == null) return;
        int i = 0;
        Iterador<Enemy> it = sala.enemigos.getIterador();
        while (it.hasNext() && i < enemigos.length) {
            Enemy target = it.next();
            EnemySaveDTO e = enemigos[i++];
            if (e == null) continue;
            target.setCurrentHealth(e.currentHealth);
            target.setDead(e.dead);
            target.setPosicion(new Coordenada(e.fila, e.columna));
        }
    }

    private static void fromHistorialDTO(HistorialNodeDTO dto,
                                          MyTree<String> tree) {
        rebuildTree(tree.getRaiz(), dto);
    }

    private static void rebuildTree(Node<String> parent, HistorialNodeDTO dto) {
        if (dto.hijos == null) return;
        for (HistorialNodeDTO h : dto.hijos) {
            if (h == null || h.dato == null) continue;
            Node<String> child = new Node<>(h.dato);
            child.setPadre(parent);
            parent.getHijos().add(child);
            rebuildTree(child, h);
        }
    }
}
