package Estructuras.MyGraph;

public class TripletaJson implements Comparable<TripletaJson> {
    private String s; // Sujeto
    private String p; // Predicado
    private String o; // Objeto

    public int compareTo(TripletaJson t) {
        return s.compareTo(t.s);
    }

    public String getSujeto() {
        return s;
    }
    public String getPredicado() {
        return p;
    }
    public String getObjeto() {
        return o;
    }
}
