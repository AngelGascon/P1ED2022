public class Aresta<K,E> {

    protected Aresta<K,E> segFila, segCol;
    protected E infoAresta;
    protected K refFila, refCol;

    Aresta(E info){
        infoAresta = info;
        segFila = null;
        segCol = null;
        refFila = null;
        refCol = null;
    }

    public void setRefs(Aresta<K,E> segFila, Aresta<K,E>segCol, K refFila, K refCol){
        this.segFila = segFila;
        this.segCol = segCol;
        this.refCol = refCol;
        this.refFila = refFila;
    }

}
