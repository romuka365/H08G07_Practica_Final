package Estructuras.MyGraphOBJ;

import Estructuras.MyGraphOBJ.ListaSimple.ListaSimple;
import Estructuras.MyGraphOBJ.ListaSimple.MiIterador;

public class VerticeOBJ<T> implements Comparable<VerticeOBJ<T>> {
   private ListaSimple<AristaOBJ> listaAristas;

   private T dato;
   private int idOBJ;

   public VerticeOBJ(int idOBJ, T dato) {
       this.idOBJ = idOBJ;
       this.dato = dato;
       this.listaAristas = new ListaSimple<AristaOBJ>();
   }

   public ListaSimple<AristaOBJ> getListaAristas() {
       return this.listaAristas;
   }

    @Override
    public int compareTo(VerticeOBJ<T> v) {
        boolean idIgual = this.idOBJ == v.getIdOBJ();
        if (idIgual) {
            return 0;
        } else {
            return -1;
        }
    }

   public int getIdOBJ() {
       return this.idOBJ;
   }

   public T getDato() {
       return this.dato;
   }


   public void addArista(AristaOBJ a) {
       listaAristas.add(a);
   }



   public String getStringAristas() {
       if (listaAristas == null) return "";
       MiIterador<AristaOBJ> it = listaAristas.getIterador();
       int i = 0;
       String resultado = "";

       while (it.hasNext()) {
           AristaOBJ a = it.next(); // Obtenemos el vértice y el iterador avanza solo

           if (a != null) {
               resultado += a.toString() + " ";
               i++;

           }
       }
       return resultado;
   }

   @Override
    public String toString() {
       return idOBJ + ":"+dato;
   }


}
