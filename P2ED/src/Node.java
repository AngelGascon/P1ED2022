public class Node<V, T, E> {

    protected Aresta<V,E> primeraFila, primeraColumna;
    protected T info;
    protected V ref;

    Node(V ref, T info){
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
