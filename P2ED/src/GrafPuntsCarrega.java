import java.util.*;

public class GrafPuntsCarrega<K,E> implements TADGrafPuntsCarrega<K,E> {
    private Hashtable<K, NodePuntsCarrega<K,E>> hashtable;

    GrafPuntsCarrega(){
        crearGraf();
    }

    public void afegirNode(K k, NodePuntsCarrega<K,E> nodeNou){ hashtable.put(k, nodeNou); }
    public NodePuntsCarrega<K,E> getHashValue(K k){ return hashtable.get(k); }

    @Override
    public void crearGraf() { hashtable = new Hashtable<>(); }

    @Override
    public void afegirAresta(NodePuntsCarrega<K,E> v1, NodePuntsCarrega<K,E> v2, E e) {
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
    public boolean existeixAresta(NodePuntsCarrega<K,E> v1, NodePuntsCarrega<K,E> v2) {
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
    public E valorAresta(NodePuntsCarrega<K,E> v1, NodePuntsCarrega<K,E> v2) {
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
    public List<NodePuntsCarrega<K,E>> adjacents(NodePuntsCarrega<K,E> v) {
        List<NodePuntsCarrega<K,E>> value = new LinkedList<>();
        Aresta<K,E> aux;
        aux = v.primeraFila;
        while (aux!=null){ value.add(this.getHashValue(aux.refCol)); aux = aux.segFila; }
        aux = v.primeraColumna;
        while (aux!=null){ value.add(this.getHashValue(aux.refFila)); aux = aux.segCol; }
        return value;
    }
    ///Algorismes
    List<String> camiOptim(K identificador_origen, K
            identificador_desti, int autonomia){

        ArrayList<K> noVisitats = new ArrayList<>();
        Hashtable<K, Boolean> visitats = new Hashtable<>();
        Hashtable<K, Double> distancies = new Hashtable<>();
        Hashtable<K,K> camiNodes = new Hashtable<>();
        //Dijkstra
        Enumeration<K> e = hashtable.keys();
        // Iterating through the Hashtable
        //Setting elements
        while (e.hasMoreElements()) {
            K key = e.nextElement();

            noVisitats.add(key);
            camiNodes.put(key, key);
            visitats.put(key, false);

            if(key.equals(identificador_origen)){
                distancies.put(key,0.0);
            }else{
                distancies.put(key,Double.MAX_VALUE);
            }

        }

        K currentVertex = null;
        K key = null;
        Aresta<K,E> inici = null;
        Double shortestDistance;

        while (noVisitats.size()!=0){//mentre no s'hagin visitat tots els nodes

            //Get currentVertex: unvisited && shortestDistance
            e = visitats.keys();
            shortestDistance = Double.MAX_VALUE;
            while (e.hasMoreElements()){
                key = e.nextElement();
                if (!visitats.get(key) && distancies.get(key) < shortestDistance){
                    currentVertex = key;
                    shortestDistance = distancies.get(key);
                }
            }

            //Visit NeighBours
            inici = hashtable.get(currentVertex).primeraFila;
            while (inici!=null){//For each unvisited NeighBours

                //Calculate distance from origin vertex: inici.infoAresta
                //if inici.infoAresta < distancies.get(inici.refCol)
                if((Double) inici.infoAresta+distancies.get(inici.refFila) < distancies.get(inici.refCol)){
                    // Update shortest distance to this vertex -> distancies
                    // Update the previous vertex with the current vertex -> camiNodes
                    distancies.put(inici.refCol, (Double) inici.infoAresta+distancies.get(inici.refFila));
                    camiNodes.put(inici.refCol, inici.refFila);
                }

                inici = inici.segFila;
            }

            inici = hashtable.get(currentVertex).primeraColumna;
            while (inici!=null){//For each unvisited NeighBours

                //Calculate distance from origin vertex: inici.infoAresta+
                //if inici.infoAresta < distancies.get(inici.refCol)
                if((Double) inici.infoAresta+distancies.get(inici.refCol) < distancies.get(inici.refFila)){
                    // Update shortest distance to this vertex -> distancies
                    // Update the previous vertex with the current vertex -> camiNodes
                    distancies.put(inici.refFila, (Double) inici.infoAresta+distancies.get(inici.refCol));
                    camiNodes.put(inici.refFila, inici.refCol);
                }

                inici = inici.segCol;
            }

            //Current vertex is visited -> visitats
            //Current vertex remove from noVisitats
            visitats.put(currentVertex, true);
            noVisitats.remove(currentVertex);
        }

        //Calcul cami optim
        LinkedList<String> cami = new LinkedList<>();
        double consum = autonomia;
        K aux = identificador_desti, prev;

        while (!aux.equals(identificador_origen)){
            //cami.addFirst(camiNodes.get(aux)+" - "+distancies.get(aux)+" km -> "+aux);
            prev = aux;
            aux = camiNodes.get(aux);//Gets prev Node
            consum = consum - (Double) valorAresta(hashtable.get(aux), hashtable.get(prev));
            if(consum<0){
                cami.addFirst("Requereix recarrega al punt: "+hashtable.get(prev).info.getId());
                consum = autonomia;
                cami.addFirst(hashtable.get(aux).info.getId()+"");
            }else {
                cami.addFirst(hashtable.get(prev).info.getId()+"");
                cami.addFirst(hashtable.get(aux).info.getId()+"");
            }
        }

        return cami;
    }
    List<String> zonesDistMaxNoGarantida(K indentificador_origen,
                                         int autonomia){
        //Floyd
        ArrayList<K> identifiers = new ArrayList<>();//Every position is a distance!
        Enumeration<K> e = hashtable.keys();
        // Iterating through the Hashtable
        //Setting elements
        while (e.hasMoreElements()) {
            K key = e.nextElement();
            identifiers.add(key);
        }
        ///
        int numNodes = hashtable.size();
        double[][] distances = new double[numNodes][numNodes];
        for (int i = 0; i<numNodes; i++) {
            distances[i][i] = 0;
        }
        for (int u = 0; u < numNodes; u++){
            for(int v = 0; v < numNodes; v++){
                if(existeixAresta(hashtable.get(identifiers.get(u)), hashtable.get(identifiers.get(v))))
                    distances[u][v] = (Double) valorAresta(hashtable.get(identifiers.get(u)), hashtable.get(identifiers.get(v)));
                else
                    distances[u][v] = Double.MAX_VALUE;//Max distance as marker of no connection
            }
        }
        //O n^3 Floyd
        for (int k = 0; k < numNodes; k++){
            for (int i = 0; i < numNodes; i++){
                for (int j = 0; j < numNodes; j++){
                    if(distances[i][j]>distances[i][k]+distances[k][j])
                        distances[i][j] = distances[i][k]+distances[k][j];
                }
            }
        }

        int index = -1;
        for (K id:identifiers) {
            if(id.equals(indentificador_origen)){
                index = identifiers.indexOf(id);
                break;
            }
        }
        LinkedList<String> distMax = new LinkedList<>();
        for (int i = 0; i<numNodes; i++){
            if(distances[index][i]<autonomia && index != i)
                distMax.addFirst("Id estacio: "+hashtable.get(identifiers.get(i)).info.getId());
        }
        return distMax;
    }
}