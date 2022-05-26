import java.util.*;

public class GrafGeneric<V, T, E> implements TADGraf<V,E>{

    private Hashtable<V, Node<V, T, E>> hashtable;
    private Hashtable<V, Integer> ordreIns;
    private Integer numIns;

    GrafGeneric(){
        crearGraf();
    }

    public void afegirNode(V nouVertex, T novaInfo){ hashtable.put(nouVertex, new Node<>(nouVertex, novaInfo)); ordreIns.put(nouVertex, numIns); numIns++; }
    public Node<V, T, E> getHashValue(V v){ return hashtable.get(v); }

    @Override
    public void crearGraf() { hashtable = new Hashtable<>(); ordreIns = new Hashtable<>(); numIns=0; }

    @Override
    public void afegirAresta(V v1, V v2, E e) {
        Aresta<V,E> novArestaCol = new Aresta<>(e);
        try {
            Node<V, T, E> Node1 = getHashValue(v1);
            Node<V, T, E> Node2 = getHashValue(v2);
            if(Node1.primeraFila==null){
                Node1.primeraFila = novArestaCol;
                Node2.primeraColumna = novArestaCol;
                novArestaCol.setRefs(null, null, Node1.ref, Node2.ref);
            }else{
                novArestaCol.setRefs(Node1.primeraFila, Node2.primeraColumna, Node1.ref, Node2.ref);
                Node1.primeraFila = novArestaCol;
                Node2.primeraColumna = novArestaCol;
            }
        }catch (NullPointerException error){
            System.out.println(error);
        }
    }

    @Override
    public boolean existeixAresta(V v1, V v2) {
        boolean trobat = false;
        Aresta<V,E> aux;
        try {
            if(ordreIns.get(v1) < ordreIns.get(v2)){
                aux = hashtable.get(v1).primeraFila;
                while (aux!=null){
                    if (aux.refFila.equals(v1) && aux.refCol.equals(v2)) {
                        trobat = true;
                        break;
                    }
                    aux = aux.segFila;
                }
            }else {
                aux = hashtable.get(v2).primeraFila;;
                while (aux != null) {
                    if (aux.refCol.equals(v1) && aux.refFila.equals(v2)) {
                        trobat = true;
                        break;
                    }
                    aux = aux.segFila;
                }
            }
        }catch (NullPointerException error){
            System.out.println(error);
        }
        return trobat;
    }

    @Override
    public E valorAresta(V v1, V v2) {
        E value = null;
        Aresta<V,E> aux;
        try {
            if(ordreIns.get(v1) < ordreIns.get(v2)){
                aux = hashtable.get(v1).primeraFila;
                while (aux!=null){
                    if(aux.refFila.equals(v1) && aux.refCol.equals(v2)) value = aux.infoAresta;
                    aux = aux.segFila;
                }
            }else {
                aux = hashtable.get(v2).primeraFila;;
                while (aux!=null){
                    if(aux.refCol.equals(v1) && aux.refFila.equals(v2)) value = aux.infoAresta;
                    aux = aux.segFila;
                }
            }
        }catch (NullPointerException error){
            System.out.println(error);
        }
        return value;
    }

    @Override
    public List<V> adjacents(V v) {
        List<V> value = new LinkedList<>();
        Aresta<V, E> aux;
        try {
            aux = hashtable.get(v).primeraFila;
            while (aux != null) {
                value.add(aux.refCol);
                aux = aux.segFila;
            }
            aux = hashtable.get(v).primeraColumna;
            while (aux != null) {
                value.add(aux.refFila);
                aux = aux.segCol;
            }
        }catch (NullPointerException error){
            System.out.println(error);
        }
        return value;
    }

    ///Algorismes
    List<String> camiOptim(V identificador_origen, V
            identificador_desti, int autonomia){

        ArrayList<V> identifiers = new ArrayList<>();
        Enumeration<V> e = hashtable.keys();
        int src = -1, dest = -1, n=0;
        // Iterating through the Hashtable
        //Setting elements
        while (e.hasMoreElements()) {
            V key = e.nextElement();
            identifiers.add(key);
            if(key.equals(identificador_origen))
                src=n;
            if(key.equals(identificador_desti))
                dest=n;
            n++;
        }

        int numNodes = hashtable.size();
        double[][] graph = new double[numNodes][numNodes];
        for (int u = 0; u < numNodes; u++){
            for(int v = 0; v < numNodes; v++){
                if(existeixAresta(identifiers.get(u), identifiers.get(v)))
                    graph[u][v] = (Double) valorAresta(identifiers.get(u), identifiers.get(v));
            }
        }

        double dist[] = new double[numNodes]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[numNodes];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < numNodes; i++) {
            dist[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        int[] parents = new int[numNodes];
        for (int i = 0; i<numNodes; i++)//Every parent "points" src, finiching execution if path does not exist
            parents[i] = src;
        parents[src] = -1;

        // Find shortest path for all vertices
        for (int count = 0; count < numNodes - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, numNodes);
            // Mark the picked vertex as processed
            sptSet[u] = true;
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < numNodes; v++)
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] > 0 && dist[u] != Double.MAX_VALUE && (dist[u] + graph[u][v]) < dist[v] && (dist[u] + graph[u][v]) < autonomia){
                    dist[v] = dist[u] + graph[u][v];
                    parents[v] = u;//Crea el camí del nodes
                }
        }
        /*
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < numNodes; i++)
            System.out.println(identifiers.get(i) + " \t\t " + dist[i]);
        System.out.println("------------");*/

        LinkedList<String> camiMin = new LinkedList<>();
        camiMin.addFirst(hashtable.get(identifiers.get(parents[dest])).info.toString());
        while(parents[dest] != -1){
            camiMin.addFirst(hashtable.get(identifiers.get(parents[dest])).info.toString());
            dest = parents[dest];
        }

        return camiMin;
    }

    private int minDistance(double dist[], Boolean sptSet[], int numNodes)
    {
        // Initialize min value
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < numNodes; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    List<String> zonesDistMaxNoGarantida(V indentificador_origen,
                                         int autonomia){
        //Floyd
        ArrayList<V> identifiers = new ArrayList<>();//Every position is a distance!
        Enumeration<V> e = hashtable.keys();
        // Iterating through the Hashtable
        //Setting elements
        while (e.hasMoreElements()) {
            V key = e.nextElement();
            identifiers.add(key);
        }
        int numNodes = hashtable.size();
        double[][] distances = new double[numNodes][numNodes];
        for (int u = 0; u < numNodes; u++){
            for(int v = 0; v < numNodes; v++){
                if(existeixAresta(identifiers.get(u), identifiers.get(v)))
                    distances[u][v] = (Double) valorAresta(identifiers.get(u), identifiers.get(v));
                else if(u!=v)
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
        for (V id:identifiers) {
            if(id.equals(indentificador_origen)){
                index = identifiers.indexOf(id);
                break;
            }
        }
        LinkedList<String> distMax = new LinkedList<>();
        for (int i = 0; i<numNodes; i++){
            if(distances[index][i]<autonomia && index != i)
                distMax.addFirst("Id estacio: "+hashtable.get(identifiers.get(i)).info.toString());
        }
        return distMax;
    }
}