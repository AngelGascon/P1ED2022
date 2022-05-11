public class Node<K,V,E> {

    protected Aresta<K,E> primeraFila, primeraColumna;
    protected V info;
    protected K ref;

    Node(V info, K ref){
        this.ref = ref;
        this.info = info;
        primeraFila = null;
        primeraColumna = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "primeraFila=" + primeraFila +
                ", primeraColumna=" + primeraColumna +
                ", info=" + info +
                ", ref=" + ref +
                '}';
    }
}
