import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class GrafGeneric<K,V,E> implements TADGraf<K,V,E>{

    private Hashtable<K, Node<K,V,E>> hashtable;

    GrafGeneric(){
        crearGraf();
    }

    public void afegirNode(K k, Node<K,V,E> nodeNou){ hashtable.put(k, nodeNou); }
    public Node<K,V,E> getHashValue(K k){ return hashtable.get(k); }

    @Override
    public void crearGraf() { hashtable = new Hashtable<>(); }

    @Override
    public void afegirAresta(Node<K,V,E> v1, Node<K,V,E> v2, E e) {
        Aresta<K,E> novArestaCol = new Aresta<>(e);
        //Aresta<K,E> novArestaFil = new Aresta<>(e);
        if(v1.primeraFila==null){
            v1.primeraFila = novArestaCol;
            v2.primeraColumna = novArestaCol;
            novArestaCol.setRefs(null, null, v1.ref, v2.ref);
            /*
            v1.primeraColumna = novArestaFil;
            v2.primeraFila = novArestaFil;
            novArestaCol.setRefs(null, null, v2.ref, v1.ref);*/
        }else{
            novArestaCol.setRefs(v1.primeraFila, v2.primeraColumna, v1.ref, v2.ref);
            v1.primeraFila = novArestaCol;
            v2.primeraColumna = novArestaCol;
            /*
            novArestaFil.setRefs(v2.primeraFila, v1.primeraColumna, v2.ref, v1.ref);
            v2.primeraFila = novArestaFil;
            v1.primeraColumna = novArestaFil;*/
        }
    }

    @Override
    public boolean existeixAresta(Node<K,V,E> v1, Node<K,V,E> v2) {
        boolean trobat = false;
        Aresta<K,E> aux;
        if((Integer)v1.ref < (Integer)v2.ref){
            aux = v1.primeraFila;
            while (aux!=null){
                if (aux.refFila == v1.ref && aux.refCol == v2.ref) {
                    trobat = true;
                    break;
                }
                aux = aux.segFila;
            }
        }else{
            aux = v2.primeraFila;
            while (aux!=null){
                if (aux.refCol == v1.ref && aux.refFila == v2.ref) {
                    trobat = true;
                    break;
                }
                aux = aux.segFila;
            }
        }
        return trobat;
    }

    @Override
    public E valorAresta(Node<K,V,E> v1, Node<K,V,E> v2) {
        E value = null;
        Aresta<K,E> aux;
        if((Integer)v1.ref < (Integer)v2.ref){
            aux = v1.primeraFila;
            while (aux!=null){
                if(aux.refFila == v1.ref && aux.refCol == v2.ref) value = aux.infoAresta;
                aux = aux.segFila;
            }
        }else{
            aux = v2.primeraFila;
            while (aux!=null){
                if(aux.refCol == v1.ref && aux.refFila == v2.ref) value = aux.infoAresta;
                aux = aux.segFila;
            }
        }
        return value;
    }

    @Override
    public List<Node<K,V,E>> adjacents(Node<K,V,E> v) {
        List<Node<K,V,E>> value = new LinkedList<>();
        Aresta<K,E> aux;
        aux = v.primeraFila;
        while (aux!=null){ value.add(this.getHashValue(aux.refCol)); aux = aux.segFila; }
        aux = v.primeraColumna;
        while (aux!=null){ value.add(this.getHashValue(aux.refFila)); aux = aux.segCol; }
        return value;
    }
}
