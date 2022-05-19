public class NodePuntsCarrega <K,E>{
    protected Aresta<K,E> primeraFila, primeraColumna;
    protected Estacio info;
    protected K ref;

    NodePuntsCarrega(Estacio info, K ref){
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
