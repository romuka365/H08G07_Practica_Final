package javafx;

import model.*;
import Estructuras.MyMatrix.*;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.Iterador;
import Estructuras.MyGraph.Grafo;
import Estructuras.MyGraph.BFSGrafo;
import Estructuras.MyGraph.Vertice;
import Estructuras.MyTree.MyTree;
import Estructuras.MyTree.Node;
import persistence.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import model.Item;

public class MainFX extends Application {
    //Atributos:
    private Player jugador;
    private Grafo grafo;
    private BFSGrafo bfsGrafo;
    private RoomData[] rooms;
    private int habitacionActual;
    private int turno = 0;
    private int pasosUsados = 0;
    private boolean victoria = false;
    private boolean gameOver = false;
    private static final int FILAS = 5;
    private static final int COLUMNAS = 5;
    private static final int MAX_TURNOS = 75;
    private static final String[] ROOM_NAMES = {"H1", "H2", "H3", "H4"};

    private GridPane grid;
    private TextArea logArea;
    private Label turnoLabel;
    private Label posLabel;
    private Label vidaLabel;
    private Label atkLabel;
    private Label defLabel;
    private Label movLabel;
    private Label pasosLabel;
    private Label habInfo;
    private VBox leftPanel;
    private int distanciaSalida = -1;
    private int habitacionesRestantes = 0;
    private int pasosMinimosRestantes = 0;
    private boolean mostrarCamino = false;
    private ListaSE<Coordenada> caminoMostrado;
    private Label distLabel, habRestLabel, pasosMinLabel, oroLabel;
    private MyTree<String> historial;
    private Node<String> turnoActualNode;
    private Stage stage;

    private static class RoomData {
        Room room;
        BFSMatriz<Cell> bfs;
        ListaSE<Enemy> enemigos;
        Coordenada salida;
        Coordenada entrada;
        Door puerta;
        String nombre;
        RoomData(Room room, BFSMatriz<Cell> bfs, ListaSE<Enemy> enemigos,
                 Coordenada salida, Coordenada entrada, Door puerta, String nombre) {
            this.room = room;
            this.bfs = bfs;
            this.enemigos = enemigos;
            this.salida = salida;
            this.entrada = entrada;
            this.puerta = puerta;
            this.nombre = nombre;
        }
    }

    //Metodo que inicia el juego
    @Override
    public void start(Stage stage) {
        initGame();
        BorderPane root = buildUI();
        Scene scene = new Scene(root, 1000, 600);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: case W: movePlayer(-1, 0); break;
                case DOWN: case S: movePlayer(1, 0); break;
                case LEFT: case A: movePlayer(0, -1); break;
                case RIGHT: case D: movePlayer(0, 1); break;
                case DIGIT1: case NUMPAD1: useInventoryItem(0); break;
                case DIGIT2: case NUMPAD2: useInventoryItem(1); break;
                case DIGIT3: case NUMPAD3: useInventoryItem(2); break;
                case DIGIT4: case NUMPAD4: useInventoryItem(3); break;
                case DIGIT5: case NUMPAD5: useInventoryItem(4); break;
                case DIGIT6: case NUMPAD6: useInventoryItem(5); break;
                case DIGIT7: case NUMPAD7: useInventoryItem(6); break;
                case DIGIT8: case NUMPAD8: useInventoryItem(7); break;
                case DIGIT9: case NUMPAD9: useInventoryItem(8); break;
                case P: comprarCamino(); break;
                case G: if (e.isControlDown()) guardarPartida(); break;
                case O: if (e.isControlDown()) cargarPartida(); break;
            }
        });

        this.stage = stage;
        stage.setTitle("Juego - Demo");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    private void initGame() {
        jugador = new Player();

        grafo = new Grafo();
        for (String n : ROOM_NAMES) grafo.addVertice("habitacion", n);
        grafo.addArista("habitacion", "H1", "habitacion", "H2", "conecta");
        grafo.addArista("habitacion", "H2", "habitacion", "H3", "conecta");
        grafo.addArista("habitacion", "H3", "habitacion", "H4", "conecta");
        bfsGrafo = new BFSGrafo(grafo);

        int maxId = grafo.getMaxId();
        rooms = new RoomData[maxId + 1];
        for (int id = 1; id <= maxId; id++) {
            String name = grafo.getVertice(id).getNombre();
            Room room = new Room(FILAS, COLUMNAS);
            BFSMatriz<Cell> bfs = new BFSMatriz<>(room.getMatrix());
            rooms[id] = new RoomData(room, bfs, new ListaSE<>(),
                null, new Coordenada(0, 0), null, name);
        }

        int idH1 = grafo.buscarIdVertice("habitacion", "H1");
        int idH2 = grafo.buscarIdVertice("habitacion", "H2");
        int idH3 = grafo.buscarIdVertice("habitacion", "H3");
        int idH4 = grafo.buscarIdVertice("habitacion", "H4");

        habitacionActual = idH1;
        jugador.setPosicion(rooms[idH1].entrada);

        // H1
        //Paredes:
        rooms[idH1].salida = new Coordenada(4, 4);
        rooms[idH1].bfs.setCeldaBloqueada(0, 2, true);
        rooms[idH1].bfs.setCeldaBloqueada(1, 1, true);
        rooms[idH1].bfs.setCeldaBloqueada(2, 3, true);
        rooms[idH1].bfs.setCeldaBloqueada(3, 0, true);
        rooms[idH1].bfs.setCeldaBloqueada(4, 2, true);
        //Enemigos:
        rooms[idH1].enemigos.add(new Enemy(false, 5, 2, 3, new Coordenada(0, 4)));
        rooms[idH1].enemigos.add(new Enemy(false, 5, 2, 3, new Coordenada(4, 1)));
        //Pociones:
        Potion pocima = new Potion(5, false, false, null);
        rooms[idH1].room.getCell(3, 2).setObjeto(pocima);
        rooms[idH1].room.getCell(3, 2).setOccupied(true);

        //H2
        //Paredes:
        rooms[idH2].salida = new Coordenada(0, 4);
        rooms[idH2].bfs.setCeldaBloqueada(0, 1, true);
        rooms[idH2].bfs.setCeldaBloqueada(1, 1, true);
        rooms[idH2].bfs.setCeldaBloqueada(3, 1, true);
        rooms[idH2].bfs.setCeldaBloqueada(4, 1, true);
        //Ememigos:
        rooms[idH2].enemigos.add(new Enemy(false, 3, 2, 2, new Coordenada(3, 4)));
        rooms[idH2].enemigos.add(new Enemy(false, 3, 2, 2, new Coordenada(4, 3)));
        rooms[idH2].enemigos.add(new Enemy(false, 3, 2, 2, new Coordenada(2, 2)));

        //Escudos
        Shield escudo = new Shield("hand", 2, false, false, null);
        rooms[idH2].room.getCell(4, 0).setObjeto(escudo);
        rooms[idH2].room.getCell(4, 0).setOccupied(true);
        //Puerta:
        rooms[idH2].puerta = new Door(null, true, new Cell(0, 4));
        //Llave:
        Key llaveH1 = new Key(rooms[idH2].puerta, false, new Cell(2, 4));
        rooms[idH2].room.getCell(4, 4).setObjeto(llaveH1);
        rooms[idH2].room.getCell(4, 4).setOccupied(true);
        //Monedas:
        Money moneda2 = new Money();
        rooms[idH2].room.getCell(0, 3).setObjeto(moneda2);
        rooms[idH2].room.getCell(0, 3).setOccupied(true);

        // H3
        //Paredes:
        rooms[idH3].salida = new Coordenada(4, 0);
        rooms[idH3].bfs.setCeldaBloqueada(1, 0, true);
        rooms[idH3].bfs.setCeldaBloqueada(1, 1, true);
        rooms[idH3].bfs.setCeldaBloqueada(1, 2, true);
        rooms[idH3].bfs.setCeldaBloqueada(2, 0, true);
        rooms[idH3].bfs.setCeldaBloqueada(2, 1, true);
        rooms[idH3].bfs.setCeldaBloqueada(2, 2, true);
        rooms[idH3].bfs.setCeldaBloqueada(3, 0, true);
        rooms[idH3].bfs.setCeldaBloqueada(3, 1, true);
        rooms[idH3].bfs.setCeldaBloqueada(3, 2, true);
        rooms[idH3].bfs.setCeldaBloqueada(2, 4, true);
        //Enemigos:
        rooms[idH3].enemigos.add(new Enemy(false, 8, 3, 3, new Coordenada(2, 3)));
        //Pociones:
        Potion pocima2 = new Potion(5, false, false, null);
        rooms[idH3].room.getCell(4, 3).setObjeto(pocima2);
        rooms[idH3].room.getCell(4, 3).setOccupied(true);
        //Botas:
        Boots botas = new Boots("feet", 1, false, false, null);
        rooms[idH3].room.getCell(0, 1).setObjeto(botas);
        rooms[idH3].room.getCell(0, 1).setOccupied(true);
        //Dinero:
        Money moneda1 = new Money();
        rooms[idH3].room.getCell(0, 4).setObjeto(moneda1);
        rooms[idH3].room.getCell(0, 4).setOccupied(true);

        // H4
        //Paredes:
        rooms[idH4].salida = new Coordenada(4, 4);
        rooms[idH4].bfs.setCeldaBloqueada(4, 0, true);
        rooms[idH4].bfs.setCeldaBloqueada(3, 1, true);
        rooms[idH4].bfs.setCeldaBloqueada(1, 3, true);
        rooms[idH4].bfs.setCeldaBloqueada(0, 4, true);
        //Enemigos:
        rooms[idH4].enemigos.add(new Enemy(false, 15, 3, 4, new Coordenada(2, 2)));
        //Armas:
        Weapon espada = new Weapon("hand", 2, false, false, null);
        rooms[idH4].room.getCell(1, 1).setObjeto(espada);
        rooms[idH4].room.getCell(1, 1).setOccupied(true);

        //Historial de acciones:
        historial = new MyTree<>("Historial");
        turnoActualNode = null;
    }

    private BorderPane buildUI() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        grid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setStyle("-fx-padding: 20;");
        drawGrid();
        StackPane centerPane = new StackPane(grid);
        centerPane.setStyle("-fx-background-color: #1e1e1e;");
        root.setCenter(centerPane);

        VBox right = new VBox(10);
        right.setStyle("-fx-padding: 15; -fx-background-color: #3c3c3c;");
        right.setPrefWidth(220);

        Label mapTitle = new Label("MAPA");
        mapTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");

        turnoLabel = new Label("Turno: 0/" + MAX_TURNOS);
        turnoLabel.setStyle("-fx-text-fill: white;");

        Label rutaTitle = new Label("RUTA");
        rutaTitle.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");
        distLabel = new Label("Dist. salida: ?");
        distLabel.setStyle("-fx-text-fill: #f1c40f;");
        habRestLabel = new Label("Habs rest: ?");
        habRestLabel.setStyle("-fx-text-fill: #f1c40f;");
        pasosMinLabel = new Label("Pasos min: ?");
        pasosMinLabel.setStyle("-fx-text-fill: #f1c40f;");
        oroLabel = new Label("Oro: 0");
        oroLabel.setStyle("-fx-text-fill: #f1c40f;");

        Label habTitle = new Label("HABITACION");
        habTitle.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");
        habInfo = new Label("Sala inicial");
        habInfo.setStyle("-fx-text-fill: #888;");

        Label logTitle = new Label("Registro:");
        logTitle.setStyle("-fx-text-fill: white;");

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefWidth(200);
        logArea.setPrefHeight(300);
        logArea.setStyle("-fx-control-inner-background: #1a1a1a; -fx-text-fill: #ccc; -fx-font-family: monospace;");

        Button historialBtn = new Button("Historial");
        historialBtn.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-weight: bold;");
        historialBtn.setOnAction(e -> mostrarHistorial());
        historialBtn.setMaxWidth(Double.MAX_VALUE);

        Button guardarBtn = new Button("Guardar (Ctrl+G)");
        guardarBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");

        Button cargarBtn = new Button("Cargar (Ctrl+O)");
        cargarBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold;");
        cargarBtn.setOnAction(e -> cargarPartida());
        cargarBtn.setMaxWidth(Double.MAX_VALUE);

        right.getChildren().addAll(mapTitle, turnoLabel, rutaTitle, distLabel, habRestLabel, pasosMinLabel, oroLabel, habTitle, habInfo, logTitle, logArea, historialBtn, guardarBtn, cargarBtn);
        root.setRight(right);

        leftPanel = new VBox(10);
        leftPanel.setStyle("-fx-padding: 15; -fx-background-color: #3c3c3c;");
        leftPanel.setPrefWidth(200);
        root.setLeft(leftPanel);

        HBox bottom = new HBox(25);
        bottom.setStyle("-fx-padding: 10; -fx-background-color: #3c3c3c;");

        //Estadisticas:
        vidaLabel = new Label("Vida: " + jugador.getCurrentHealth() + "/" + jugador.getMaxHealth());
        vidaLabel.setStyle("-fx-text-fill: white;");

        atkLabel = new Label("Ataque: " + jugador.getAtk());
        atkLabel.setStyle("-fx-text-fill: white;");

        defLabel = new Label("Defensa: " + jugador.getDef());
        defLabel.setStyle("-fx-text-fill: white;");

        movLabel = new Label("Mov: " + jugador.getMov());
        movLabel.setStyle("-fx-text-fill: white;");

        pasosLabel = new Label("Pasos: 0/" + jugador.getMov());
        pasosLabel.setStyle("-fx-text-fill: white;");

        posLabel = new Label("Pos: " + jugador.getPosicion());
        posLabel.setStyle("-fx-text-fill: white;");

        bottom.getChildren().addAll(vidaLabel, atkLabel, defLabel, movLabel, pasosLabel, posLabel);
        root.setBottom(bottom);

        return root;
    }

    private void drawGrid() {
        grid.getChildren().clear();
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                Label cell = new Label();
                cell.setPrefSize(90, 90);
                cell.setStyle("-fx-border-color: #666; -fx-border-width: 1; -fx-alignment: center; -fx-font-size: 20; -fx-font-weight: bold;");

                Coordenada pos = new Coordenada(f, c);
                Coordenada salida = rooms[habitacionActual].salida;

                boolean enCamino = false;
                if (mostrarCamino && caminoMostrado != null && !pos.equals(jugador.getPosicion())) {
                    Iterador<Coordenada> it = caminoMostrado.getIterador();
                    while (it.hasNext()) {
                        if (it.next().equals(pos)) { enCamino = true; break; }
                    }
                }

                if (pos.equals(jugador.getPosicion())) {
                    cell.setText("P");
                    cell.setStyle(cell.getStyle() + "; -fx-background-color: #4a9eff; -fx-text-fill: white;");
                } else if (pos.equals(salida)) {
                    Door d = rooms[habitacionActual].puerta;
                    if (d != null && d.getLocked()) {
                        cell.setText("D");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #8B4513; -fx-text-fill: white;");
                    } else {
                        cell.setText("S");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #2ecc71; -fx-text-fill: white;");
                    }
                } else if (rooms[habitacionActual].bfs.estaBloqueada(f, c)) {
                    cell.setStyle(cell.getStyle() + "; -fx-background-color: #555;");
                } else if (tieneEnemigoVivo(pos)) {
                    cell.setText("E");
                    cell.setStyle(cell.getStyle() + "; -fx-background-color: #e74c3c; -fx-text-fill: white;");
                } else {
                    Object obj = rooms[habitacionActual].room.getCell(f, c).getObjeto();
                    if (obj instanceof Money) {
                        cell.setText("$");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #f1c40f; -fx-text-fill: yellow;");
                    } else if (obj instanceof Potion) {
                        cell.setText("H");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #27ae60; -fx-text-fill: white;");
                    } else if (obj instanceof Weapon) {
                        cell.setText("W");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #f39c12; -fx-text-fill: white;");
                    }
                    if (obj instanceof Potion) {
                        cell.setText("H");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #27ae60; -fx-text-fill: white;");
                    } else if (obj instanceof Weapon) {
                        cell.setText("W");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #f39c12; -fx-text-fill: white;");
                    } else if (obj instanceof Shield) {
                        cell.setText("SH");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #3498db; -fx-text-fill: white;");
                    } else if (obj instanceof Boots) {
                        cell.setText("B");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #e67e22; -fx-text-fill: white;");
                    } else if (obj instanceof Key) {
                        cell.setText("K");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #9b59b6; -fx-text-fill: white;");
                    } else if (enCamino) {
                        cell.setText("·");
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #f1c40f; -fx-text-fill: #333;");
                    } else {
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: #2a2a2a;");
                    }
                }
                grid.add(cell, c, f);
            }
        }
    }

    private boolean tieneEnemigoVivo(Coordenada pos) {
        Iterador<Enemy> it = rooms[habitacionActual].enemigos.getIterador();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (e.getPosicion().equals(pos) && !e.isDead()) {
                return true;
            }
        }
        return false;
    }

    private Enemy getEnemigoEn(Coordenada pos) {
        Iterador<Enemy> it = rooms[habitacionActual].enemigos.getIterador();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (e.getPosicion().equals(pos)) {
                return e;
            }
        }
        return null;
    }

    private void avanzarPaso() {
        pasosUsados++;
        if (pasosUsados >= jugador.getMov()) {
            turno++;
            pasosUsados = 0;
        }
    }

    private Item getItemEnInventario(int index) {
        if (index < 0) return null;
        Iterador<Item> it = jugador.getInventory().getIterador();
        int i = 0;
        while (it.hasNext()) {
            Item item = it.next();
            if (i == index) return item;
            i++;
        }
        return null;
    }

    private void movePlayer(int df, int dc) {
        if (jugador.isDead()) {
            logArea.appendText("Estas muerto, no puedes moverte\n");
            return;
        }
        if (victoria) {
            logArea.appendText("Ya has escapado!\n");
            return;
        }
        if (gameOver) {
            logArea.appendText("La partida ha terminado!\n");
            return;
        }

        Coordenada actual = jugador.getPosicion();
        int nf = actual.getFila() + df;
        int nc = actual.getColumna() + dc;

        if (!rooms[habitacionActual].room.getMatrix().posicionValida(nf, nc)) {
            logArea.appendText("No puedes salir del mapa\n");
            return;
        }

        if (rooms[habitacionActual].bfs.estaBloqueada(nf, nc)) {
            logArea.appendText("Hay una pared\n");
            return;
        }

        Coordenada nueva = new Coordenada(nf, nc);
        Enemy enemigo = getEnemigoEn(nueva);

        if (enemigo != null && !enemigo.isDead()) {
            enemigo.receiveAtk(jugador.getAtk());
            jugador.receiveAtk(enemigo.getAtk());
            logArea.appendText("Combate! Enemigo recibe danio. Vida enemiga: "
                + Math.max(0, enemigo.getCurrentHealth()) + "\n");
            registrarAccion("Combate contra enemigo en " + nueva);

            if (enemigo.isDead()) {
                logArea.appendText("Has derrotado al enemigo!\n");
                registrarAccion("Enemigo derrotado");
            }
            if (jugador.isDead()) {
                logArea.appendText("Has muerto...\n");
                registrarAccion("Has muerto...");
                avanzarPaso();
                actualizarPanelIzquierdo();
                updateUI();
                return;
            }
            avanzarPaso();
            moverEnemigos();
            actualizarPanelIzquierdo();
            updateUI();
            return;
        }

        jugador.setPosicion(nueva);
        registrarAccion("Movimiento a " + nueva);
        avanzarPaso();
        moverEnemigos();

        // Comprobar transicion de sala
        if (nueva.equals(rooms[habitacionActual].salida)) {
            Door d = rooms[habitacionActual].puerta;
            if (d != null && d.getLocked()) {
                logArea.appendText("La puerta esta cerrada con llave\n");
                jugador.setPosicion(actual);
                updateUI();
                return;
            }

            String name = grafo.getVertice(habitacionActual).getNombre();
            ListaSE<Vertice> path = bfsGrafo.getCamino("habitacion", name, "habitacion", "H4");
            if (path.isEmpty()) {
                logArea.appendText("No hay camino a la salida\n");
                jugador.setPosicion(actual);
                updateUI();
                return;
            }
            Iterador<Vertice> it = path.getIterador();
            it.next(); // skip current room
            if (it.hasNext()) {
                Vertice next = it.next();
                habitacionActual = next.getId();
                jugador.setPosicion(rooms[habitacionActual].entrada);
                logArea.appendText("Has pasado a " + rooms[habitacionActual].nombre + "\n");
                registrarAccion("Entraste a " + rooms[habitacionActual].nombre);
            } else {
                victoria = true;
                logArea.appendText("Has escapado!\n");
                registrarAccion("VICTORIA! Has escapado!");
            }
            actualizarPanelIzquierdo();
            updateUI();
            return;
        }

        Cell celda = rooms[habitacionActual].room.getCell(nf, nc);
        if (celda.getObjeto() != null) {
            Object obj = celda.getObjeto();
            if (obj instanceof Money) {
                Money m = (Money) obj;
                m.setIsInInventory(true);
                jugador.collectMoney(m);
                logArea.appendText("Has recogido 1 de oro!\n");
                registrarAccion("Recogiste 1 Oro");
                celda.setObjeto(null);
                celda.setOccupied(false);
            } else if (obj instanceof Item) {
                Item it = (Item) obj;
                it.setIsInInventory(true);
                jugador.addToInventory(it);
                logArea.appendText("Has recogido " + obj.getClass().getSimpleName() + "\n");
                registrarAccion("Recogiste " + obj.getClass().getSimpleName());
                celda.setObjeto(null);
                celda.setOccupied(false);
            }
        }

        actualizarPanelIzquierdo();
        updateUI();
    }

    private void moverEnemigos() {
        Iterador<Enemy> it = rooms[habitacionActual].enemigos.getIterador();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (e.isDead()) continue;

            Coordenada pos = e.getPosicion();
            int pf = pos.getFila(), pc = pos.getColumna();
            int jf = jugador.getPosicion().getFila(), jc = jugador.getPosicion().getColumna();
            int distActual = Math.abs(pf - jf) + Math.abs(pc - jc);

            int bestDf = 0, bestDc = 0, bestDist = distActual;
            int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
            for (int[] d : dirs) {
                int nf = pf + d[0], nc = pc + d[1];
                if (!rooms[habitacionActual].room.getMatrix().posicionValida(nf, nc)) continue;
                if (rooms[habitacionActual].bfs.estaBloqueada(nf, nc)) continue;
                boolean ocupado = false;
                Iterador<Enemy> it2 = rooms[habitacionActual].enemigos.getIterador();
                while (it2.hasNext()) {
                    Enemy otro = it2.next();
                    if (otro != e && !otro.isDead() && otro.getPosicion().equals(new Coordenada(nf, nc))) {
                        ocupado = true; break;
                    }
                }
                if (ocupado) continue;
                int dist = Math.abs(nf - jf) + Math.abs(nc - jc);
                if (dist < bestDist) { bestDist = dist; bestDf = d[0]; bestDc = d[1]; }
            }

            if (bestDist < distActual) {
                Coordenada nueva = new Coordenada(pf + bestDf, pc + bestDc);
                if (nueva.equals(jugador.getPosicion())) {
                    jugador.receiveAtk(e.getAtk());
                    logArea.appendText("Enemigo te ataca! Vida: " + jugador.getCurrentHealth() + "\n");
                    registrarAccion("Enemigo te ataco!");
                } else {
                    e.setPosicion(nueva);
                    registrarAccion("Enemigo se movio a " + nueva);
                }
            }
        }
    }

    private void calcularRutaOptima() {
        String name = grafo.getVertice(habitacionActual).getNombre();
        ListaSE<Vertice> path = bfsGrafo.getCamino("habitacion", name, "habitacion", "H4");

        Coordenada salidaActual = rooms[habitacionActual].salida;
        distanciaSalida = rooms[habitacionActual].bfs.distanciaMinima(jugador.getPosicion(), salidaActual);
        habitacionesRestantes = path.isEmpty() ? 0 : path.getSize() - 1;
        pasosMinimosRestantes = distanciaSalida;

        Iterador<Vertice> it = path.getIterador();
        it.next(); // skip current room
        while (it.hasNext()) {
            Vertice v = it.next();
            RoomData rd = rooms[v.getId()];
            int d = rd.bfs.distanciaMinima(rd.entrada, rd.salida);
            if (d >= 0) pasosMinimosRestantes += d;
        }
    }

    private void registrarAccion(String descripcion) {
        String turnoStr = "Turno " + turno;
        if (turnoActualNode == null || !turnoActualNode.getDato().equals(turnoStr)) {
            turnoActualNode = historial.addChild(historial.getRaiz(), turnoStr);
        }
        historial.addChild(turnoActualNode, descripcion);
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        treeToString(historial.getRaiz(), sb, 0);
        Stage stage = new Stage();
        TextArea ta = new TextArea(sb.toString());
        ta.setEditable(false);
        ta.setStyle("-fx-control-inner-background: #1a1a1a; -fx-text-fill: #ccc; -fx-font-family: monospace; -fx-font-size: 13;");
        ta.setPrefSize(450, 500);
        Scene scene = new Scene(new StackPane(ta), 450, 500);
        stage.setTitle("Historial de Acciones");
        stage.setScene(scene);
        stage.show();
    }

    private void treeToString(Node<String> node, StringBuilder sb, int depth) {
        for (int i = 0; i < depth; i++) sb.append("  ");
        if (depth > 0) sb.append("├─ ");
        sb.append(node.getDato()).append("\n");
        Iterador<Node<String>> it = node.getHijos().getIterador();
        while (it.hasNext()) {
            treeToString(it.next(), sb, depth + 1);
        }
    }

    private void comprarCamino() {
        if (gameOver || victoria || jugador.isDead()) return;
        if (mostrarCamino) {
            mostrarCamino = false;
            logArea.appendText("Camino ocultado\n");
            updateUI();
            return;
        }
        if (caminoMostrado == null) {
            if (jugador.getMoney() < 2) {
                logArea.appendText("No tienes suficiente dinero (necesitas 2 de oro)\n");
                return;
            }
            Coordenada salida = rooms[habitacionActual].salida;
            ListaSE<Coordenada> ruta = rooms[habitacionActual].bfs.caminoMinimo(jugador.getPosicion(), salida);
            if (ruta.isEmpty()) {
                logArea.appendText("No hay camino hacia la salida\n");
                return;
            }
            jugador.spendMoney(2);
            caminoMostrado = ruta;
            logArea.appendText("Has comprado el camino! (-2 oro)\n");
            registrarAccion("Compraste el camino");
        }
        mostrarCamino = true;
        calcularRutaOptima();
        updateUI();
    }

    private String nombreItem(Item item) {
        if (item instanceof Potion) return "Poción ♡";
        if (item instanceof Weapon) return "Espada ⚔";
        if (item instanceof Shield) return "Escudo ◆";
        if (item instanceof Boots) return "Botas »";
        if (item instanceof Key) return "Llave ->|";
        return "Objeto";
    }

    private void actualizarPanelIzquierdo() {
        leftPanel.getChildren().clear();

        Label enemyTitle = new Label("ENEMIGO");
        enemyTitle.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");

        Enemy cercano = getEnemigoMasCercano(jugador.getPosicion());
        if (cercano != null && !cercano.isDead()) {
            Label eInfo = new Label("Vida: " + Math.max(0, cercano.getCurrentHealth())
                + "/" + cercano.getMaxHealth()
                + "\nAtaque: " + cercano.getAtk()
                + "\nDefensa: " + cercano.getDef()
                + "\nPos: " + cercano.getPosicion());
            eInfo.setStyle("-fx-text-fill: #e74c3c;");
            leftPanel.getChildren().addAll(enemyTitle, eInfo);
        } else {
            Label ninguno = new Label("(ninguno cerca)");
            ninguno.setStyle("-fx-text-fill: #888;");
            leftPanel.getChildren().addAll(enemyTitle, ninguno);
        }

        leftPanel.getChildren().add(new Separator());

        Label invTitle = new Label("INVENTARIO");
        invTitle.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");
        leftPanel.getChildren().add(invTitle);

        if (jugador.getInventory().isEmpty()) {
            Label vacio = new Label("(vacio)");
            vacio.setStyle("-fx-text-fill: #888;");
            leftPanel.getChildren().add(vacio);
        } else {
            Iterador<Item> it = jugador.getInventory().getIterador();
            int i = 1;
            while (it.hasNext()) {
                Item item = it.next();
                String equipado = "";
                if (item instanceof Weapon && ((Weapon) item).getEquipped()) equipado = " (E)";
                else if (item instanceof Shield && ((Shield) item).getEquipped()) equipado = " (E)";
                else if (item instanceof Boots && ((Boots) item).getEquipped()) equipado = " (E)";
                Label itemLabel = new Label(i + ": " + nombreItem(item) + equipado);
                itemLabel.setStyle("-fx-text-fill: #ccc;");
                leftPanel.getChildren().add(itemLabel);
                i++;
            }
        }
    }

    private void useInventoryItem(int index) {
        Item item = getItemEnInventario(index);
        if (item == null) {
            logArea.appendText("No hay item en la ranura " + (index + 1) + "\n");
            return;
        }

        if (item instanceof Potion) {
            Potion p = (Potion) item;
            jugador.usePotion(p);
            jugador.getInventory().del(p);
            logArea.appendText("Has usado una Pocion\n");
            registrarAccion("Usaste Pocion");
        } else if (item instanceof Weapon) {
            Weapon w = (Weapon) item;
            if (w.getEquipped()) {
                jugador.removeWeapon(w);
                logArea.appendText("Has desequipado el arma\n");
                registrarAccion("Desequipaste arma");
            } else {
                jugador.equipWeapon(w);
                logArea.appendText("Has equipado el arma\n");
                registrarAccion("Equipaste arma");
            }
        } else if (item instanceof Shield) {
            Shield s = (Shield) item;
            if (s.getEquipped()) {
                jugador.removeShield(s);
                logArea.appendText("Has desequipado el escudo\n");
                registrarAccion("Desequipaste escudo");
            } else {
                jugador.equipShield(s);
                logArea.appendText("Has equipado el escudo\n");
                registrarAccion("Equipaste escudo");
            }
        } else if (item instanceof Boots) {
            Boots b = (Boots) item;
            if (b.getEquipped()) {
                jugador.removeBoots(b);
                logArea.appendText("Has desequipado las botas\n");
                registrarAccion("Desequipaste botas");
            } else {
                jugador.equipBoots(b);
                logArea.appendText("Has equipado las botas\n");
                registrarAccion("Equipaste botas");
            }
        } else if (item instanceof Key) {
            Coordenada pos = jugador.getPosicion();
            Door d = rooms[habitacionActual].puerta;
            boolean adyacenteAPuerta = false;
            if (d != null) {
                Cell doorCell = d.getPosition();
                adyacenteAPuerta = Math.abs(pos.getFila() - doorCell.getRow())
                                 + Math.abs(pos.getColumna() - doorCell.getColumn()) == 1;
            }
            if (adyacenteAPuerta && d.getLocked()) {
                Key k = (Key) item;
                if (jugador.useKey(k, d)) {
                    jugador.getInventory().del(k);
                    d.setLocked(false);
                    logArea.appendText("Has abierto la puerta con la llave!\n");
                    registrarAccion("Abriste la puerta con llave");
                    actualizarPanelIzquierdo();
                    updateUI();
                }
            } else if (d != null && !d.getLocked()) {
                logArea.appendText("La puerta ya esta abierta\n");
            } else {
                logArea.appendText("Debes estar en la puerta para usar la llave\n");
            }
            return;
        } else {
            logArea.appendText("No puedes usar " + item.getClass().getSimpleName() + "\n");
            return;
        }

        actualizarPanelIzquierdo();
        updateUI();
    }

    private Enemy getEnemigoMasCercano(Coordenada pos) {
        Iterador<Enemy> it = rooms[habitacionActual].enemigos.getIterador();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (!e.isDead()) {
                int df = Math.abs(e.getPosicion().getFila() - pos.getFila());
                int dc = Math.abs(e.getPosicion().getColumna() - pos.getColumna());
                if (df <= 1 && dc <= 1) {
                    return e;
                }
            }
        }
        return null;
    }

    private void updateUI() {
        calcularRutaOptima();
        if (mostrarCamino && caminoMostrado != null) {
            caminoMostrado = rooms[habitacionActual].bfs.caminoMinimo(jugador.getPosicion(), rooms[habitacionActual].salida);
        }
        drawGrid();
        turnoLabel.setText("Turno: " + turno + "/" + MAX_TURNOS);
        posLabel.setText("Pos: " + jugador.getPosicion());
        vidaLabel.setText("Vida: " + jugador.getCurrentHealth() + "/" + jugador.getMaxHealth());
        atkLabel.setText("Ataque: " + jugador.getAtk());
        defLabel.setText("Defensa: " + jugador.getDef());
        movLabel.setText("Mov: " + jugador.getMov());
        pasosLabel.setText("Pasos: " + pasosUsados + "/" + jugador.getMov());
        habInfo.setText(rooms[habitacionActual].nombre);
        distLabel.setText("Dist. salida: " + (distanciaSalida >= 0 ? distanciaSalida : "?"));
        habRestLabel.setText("Habs rest: " + habitacionesRestantes);
        pasosMinLabel.setText("Pasos min: " + (pasosMinimosRestantes >= 0 ? pasosMinimosRestantes : "?"));
        oroLabel.setText("Oro: " + jugador.getMoney());

        if (turno >= MAX_TURNOS && !gameOver) {
            gameOver = true;
            logArea.appendText("Se acabaron los turnos! Partida terminada.\n");
            registrarAccion("GAME OVER - Se acabaron los turnos");
        }
    }

    private void guardarPartida() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Guardar partida");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Partida guardada", "*.json"));
        fc.setInitialFileName("partida.json");
        File archivo = fc.showSaveDialog(stage);
        if (archivo == null) return;

        int maxId = grafo.getMaxId();
        JsonMapper.RoomDataHolder[] holders = new JsonMapper.RoomDataHolder[maxId];
        for (int id = 1; id <= maxId; id++) {
            RoomData rd = rooms[id];
            JsonMapper.RoomDataHolder h = new JsonMapper.RoomDataHolder();
            h.room = rd.room;
            h.bfs = rd.bfs;
            h.enemigos = rd.enemigos;
            h.salida = rd.salida;
            h.puerta = rd.puerta;
            h.nombre = rd.nombre;
            holders[id - 1] = h;
        }

        GameSaver.save(archivo, jugador, habitacionActual, turno, pasosUsados,
                       victoria, gameOver, holders, maxId, historial);
        logArea.appendText("Partida guardada en " + archivo.getName() + "\n");
    }

    private void cargarPartida() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Cargar partida");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Partida guardada", "*.json"));
        File archivo = fc.showOpenDialog(stage);
        if (archivo == null) return;

        JsonMapper.GameSaveDTO dto = GameLoader.load(archivo);
        if (dto == null) {
            logArea.appendText("Error: no se pudo cargar la partida\n");
            return;
        }

        initGame();

        int maxId = grafo.getMaxId();
        JsonMapper.RoomDataHolder[] holders = new JsonMapper.RoomDataHolder[maxId];
        for (int id = 1; id <= maxId; id++) {
            RoomData rd = rooms[id];
            JsonMapper.RoomDataHolder h = new JsonMapper.RoomDataHolder();
            h.room = rd.room;
            h.bfs = rd.bfs;
            h.enemigos = rd.enemigos;
            h.salida = rd.salida;
            h.puerta = rd.puerta;
            h.nombre = rd.nombre;
            holders[id - 1] = h;
        }

        JsonMapper.fromGameSave(dto, holders, maxId, historial);
        if (dto.Player != null) {
            JsonMapper.applyPlayerDTO(dto.Player, jugador);
        }

        habitacionActual = dto.habitacionActual;
        turno = dto.turno;
        pasosUsados = dto.pasosUsados;
        victoria = dto.victoria;
        gameOver = dto.gameOver;
        turnoActualNode = null;

        updateUI();
        actualizarPanelIzquierdo();
        logArea.appendText("Partida cargada: " + archivo.getName() + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
