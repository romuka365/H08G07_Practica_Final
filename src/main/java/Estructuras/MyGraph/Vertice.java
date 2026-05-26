package Estructuras.MyGraph;

import Estructuras.MyGraph.ListaSimple.ListaSimple;
import Estructuras.MyGraph.ListaSimple.MiIterador;

public class Vertice implements Comparable<Vertice> {
   private String nombre;
   private String tipo;
   private int id;
   private ListaSimple<Arista> listaAristas;

   public Vertice(String tipo, String nombre) {
       this.tipo = tipo;
       this.nombre = nombre;
       id = -1; //lo inicializamos en -1 pq al añadirlo al grafo, se le otorgará el id bueno
       this.listaAristas = new ListaSimple<Arista>();
   }

   public ListaSimple<Arista> getListaAristas() {
       return this.listaAristas;
   }
   public String getNombre() {
       return nombre;
   }

   public String getTipo() {
       return tipo;
   }

   public int getId() {
       return id;
   }

   public int compareTo(Vertice v) {
       int nombreIgual = this.nombre.compareTo(v.getNombre());
       int  tipoIgual = this.tipo.compareTo(v.getTipo());
       return nombreIgual + tipoIgual;
   }

   public void setId(int id) { //este id me lo dan desde el grafo
       this.id = id;
   }

   public void addArista(Arista a) {
       listaAristas.add(a);
   }



   public String getStringAristas() {
       if (listaAristas == null) return "";
       MiIterador<Arista> it = listaAristas.getIterador();
       int i = 0;
       String resultado = "";

       while (it.hasNext()) {
           Arista a = it.next(); // Obtenemos el vértice y el iterador avanza solo

           if (a != null) {
               resultado += a.toString() + " ";
               i++;

           }
       }
       return resultado;
   }

   @Override
    public String toString() {
       return tipo + ":"+nombre + "(" + id + ")";
   }


}
