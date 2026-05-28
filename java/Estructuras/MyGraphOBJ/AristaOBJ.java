package Estructuras.MyGraphOBJ;

public class AristaOBJ implements Comparable<AristaOBJ> {
        protected int idDestino;
        protected String predicado;

        public AristaOBJ(int idDestino, String predicado) {
            this.idDestino = idDestino;
            this.predicado = predicado;
        }

        public String getPredicado() {
            return predicado;
        }

        public int getIdDestino() {
            return idDestino;
        }

    public int compareTo(AristaOBJ a) {
        return predicado.compareTo(a.predicado);
    }



    @Override
    public String toString() {
            return " " + predicado + " (" + idDestino + ")";
    }


}
