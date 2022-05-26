public class Aresta<V,E> {

    protected Aresta<V,E> segFila, segCol;
    protected E infoAresta;
    protected V refFila, refCol;

    Aresta(E info){
        infoAresta = info;
        segFila = null;
        segCol = null;
        refFila = null;
        refCol = null;
    }

    public void setRefs(Aresta<V,E> segFila, Aresta<V,E>segCol, V refFila, V refCol){
        this.segFila = segFila;
        this.segCol = segCol;
        this.refCol = refCol;
        this.refFila = refFila;
    }

}
