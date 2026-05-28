package ui;
import model.*;

public class RoomView //VISUAL
{
    public void printRoom(Room room)
    {
        System.out.println("Habitación "+room.getId());
        for (int i = 0; i < room.getFilas(); i++){
            for (int j = 0; j < room.getColumnas(); j++){
                System.out.print(" "+i+""+j);
                Cell celdaComprobar = room.getDato(i,j);
                if(celdaComprobar.hasDoor()) {
                    System.out.print("*");
                }
                if(celdaComprobar.isOccupied()){
                    System.out.print(room.getDato(i,j));
                }

                else{
                    System.out.print("-  ");
                }
            }
            System.out.println("");
        }
    }
}
