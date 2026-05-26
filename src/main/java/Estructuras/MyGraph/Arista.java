package Estructuras.MyGraph;

public class Arista implements Comparable<Arista> {
        protected int idDestino;
        protected String predicado;

        public Arista(int idDestino, String predicado) {
            this.idDestino = idDestino;
            this.predicado = predicado;
        }

        public String getPredicado() {
            return predicado;
        }

        public int getIdDestino() {
            return idDestino;
        }

    public int compareTo(Arista a) {
        return predicado.compareTo(a.predicado);
    }



    @Override
    public String toString() {
            return " " + predicado + " (" + idDestino + ")";
    }


}
