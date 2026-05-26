package Estructuras.MyGraph;

import Estructuras.MyGraph.ListaSimple.ListaSimple;
import Estructuras.MyGraph.ListaSimple.MiIterador;
import Estructuras.Interfaces.GrafoInterfaz;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;

public class Grafo implements GrafoInterfaz {
    private int maxId = 0; //esto lo creamos para que cuando eliminemos nodos, no depender de la cantidad de vértices para asignar los ids
    private int numVertices = 0;
    private ListaSimple<Vertice> vertices;

    public Grafo(){
        this.vertices = new ListaSimple<Vertice>();
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getMaxId() {
        return maxId;
    }

    public void addVertice(String tipoVertice, String nombreVertice) {
        //Primero comprobamos que el vértice que queremos añadir no esté
        int nuevoId = buscarIdVertice(tipoVertice, nombreVertice);
        if  (nuevoId == -1) { //si nos devuelve -1, significa que el elemento no está
            numVertices += 1;
            maxId += 1;
            Vertice v = new  Vertice(tipoVertice, nombreVertice);
            v.setId(maxId);
            vertices.add(v); //en la última posición añadimos el nuevo vértice
            ListaSimple<Arista> listaAristas = new ListaSimple<Arista>();
        }
    }

    public void addArista(String tipoOrigen, String nombreOrigen, String tipoDestino, String nombreDestino, String predicado) {
        int idOrigen = buscarIdVertice(tipoOrigen, nombreOrigen);
        int idDestino = buscarIdVertice(tipoDestino, nombreDestino);
        if(idOrigen >= 0 && idDestino >= 0) {
            Arista a = new Arista(idDestino, predicado);
            Vertice v = getVertice(idOrigen);
            v.addArista(a);
        }
    }

    public Vertice getVertice(int id) {
        boolean encontrado = false;
        MiIterador<Vertice> it = vertices.getIterador();
        Vertice b = null;
        while (!encontrado && it.hasNext()) {
            b = it.next();
            if (id == b.getId()) {
                encontrado = true;
            }
        }
        return b;
    }


    public int buscarIdVertice(String tipoVertice, String nombreVertice){
        //Miramos toda la lista de vértices en busca del nombre dado
        //Si el elemento está, devolvemos su identificador
        //Si el elemento no está, devolvemos -1
        Vertice v = new Vertice(tipoVertice,  nombreVertice);
        Vertice b = null;
        boolean encontrado = false;
        int i = 0;
        if(vertices.getSize() == 0) {
            return -1;
        }
        else {
            MiIterador<Vertice> it = vertices.getIterador();

            while (!encontrado && it.hasNext()) {
                b = it.next();
                int comparo = b.compareTo(v);
                if (comparo == 0) {
                    encontrado = true;
                }
                if (!encontrado) {
                /*
                solo sumamos si no ha sido encontrado porque queremos conservar
                el número i de la iteración el cual coincide con su id.
                Si lo sumasemos igualmente, estaríamos devolviendo una posición
                más de la deseada.
                 */
                    i += 1;
                }
            }
            if (!encontrado) {
                return -1;
            } else {
                return b.getId();
            }
        }
    }


    @Override
    public String toString() {
        MiIterador<Vertice> it = vertices.getIterador();
        int i = 0;
        String resultado = "";

        while (it.hasNext()) {
            Vertice b = it.next(); // Obtenemos el vértice y el iterador avanza solo

            if (b != null) {
                resultado += b.toString() ;
                resultado += imprimeConexiones(b.getId());
                i++;
                resultado += "\n";
            }
        }
        return resultado + "Hay " + i + " elementos";
    }
    public String imprimeConexiones(int id) {
        Vertice v = getVertice(id); //convierto el id en un vértice
        String imprimir = "";
        imprimir += v.getStringAristas();

         return imprimir;

    }



    public void cargarGrafoDesdeJson(String rutaArchivo) {
        Gson gson = new Gson();
        DatosGrafo datos = null;

        try (Reader reader = new FileReader(rutaArchivo)) {
            // GSON convierte todo el JSON en nuestra clase DatosGrafo
            //TripletaJson t = null;
            datos = gson.fromJson(reader, DatosGrafo.class);

            //Recorremos las tripletas para crear el grafo
            //MiIterador<TripletaJson> it = datos.tripletas.getIterador();
            //while (it.hasNext()) {
            //    t = it.next();
            for (TripletaJson t : datos.tripletas) {

                // Extraemos los datos (s, p, o)
                String sujeto = t.getSujeto();
                String predicado = t.getPredicado();
                String objeto = t.getObjeto();

                // 3. Insertamos en tu lógica de Grafo
                // Asumo que tu grafo tiene estos métodos o similares:
                this.addVertice(limpiarTipo(sujeto), limpiarNombre(sujeto));
                this.addVertice(limpiarTipo(objeto), limpiarNombre(objeto));
                this.addArista(limpiarTipo(sujeto),limpiarNombre(sujeto),limpiarTipo(objeto),limpiarNombre(objeto),predicado);
            }

            System.out.println("Grafo cargado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
    }


    private String limpiarNombre(String dato) {
        if (dato.contains(":")) {
            return dato.split(":")[1]; // Devuelve lo que hay después de los puntos
        }
        return dato; // Si no tiene puntos (como "1921"), lo deja igual
    }

    private String limpiarTipo(String dato) {
        if (dato.contains(":")) {
            return dato.split(":")[0]; // Devuelve lo que hay antes de los puntos
        }
        return dato;
    }
}
