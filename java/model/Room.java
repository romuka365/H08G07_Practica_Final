package model;

import Estructuras.MyMatrix.MyMatrix;

public class Room<T> implements Comparable<Room<?>> {
    private int id;
    private int filas;
    private int columnas;
    private Cell dato;
    private MyMatrix room;

    public Room(int id, int filas, int columnas){
        room = new MyMatrix(filas,columnas);
        this.id = id;
        this.filas = filas;
        this.columnas = columnas;
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                room.set(i, j, new Cell());
            }
        }
    }

    public MyMatrix getMatrix(){
        return room;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Cell getDato(int fila, int columna){
        return (Cell) room.get(fila, columna);
    }

    public void fillCell(int fila, int columna, T objeto){
        Cell celdaFill =(Cell) room.get(fila,columna); //Obtengo un dato de tipo T y lo hago de tipo Cell
        celdaFill.addObjeto(objeto);

    }




    public int compareTo(Room otraRoom) {
        if(this.getId() == otraRoom.getId()){
            return 0;
        }
        else {
            return -1;
        }
    }
}
