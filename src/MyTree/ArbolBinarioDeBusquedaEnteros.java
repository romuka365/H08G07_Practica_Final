package MyTree;

public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {

    public int getSuma(){
        return sumaRec(getRaiz()); //Comenzamos por la raiz.
    }

    private int sumaRec(Nodo<Integer> actual){ //Metodo auxiliar recursivo para la suma.
        if (actual == null){
            return 0; //Si no hay nodo no lo sumamos.
        }

        //Vamos recorriendo el árbol tanto por la izquierda como por la derecha y sumamos.
        return actual.getDato() + sumaRec(actual.getHijoIz()) + sumaRec(actual.getHijoDr());
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();

        if (this.getRaiz() != null) {
            sub.setRaiz(this.getRaiz().getHijoIz());
        } else {
            sub.setRaiz(null);
        }

        return sub;
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();

        if (this.getRaiz() != null) {
            sub.setRaiz(this.getRaiz().getHijoDr());
        } else {
            sub.setRaiz(null);
        }

        return sub;
    }
}
