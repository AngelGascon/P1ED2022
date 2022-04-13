public class HashTable <K extends Comparable<K>, T extends Comparable<T>> implements TADHashTable<K,T> {

    private float nElems;
    private StaticTable<HashNode<K, T>> table;

    //HashList
    public HashTable(){
        create();
    }

    @Override
    public void create() {
        nElems = 0;
        table = new StaticTable<>();
        for(int i=0;i<table.lon();i++){
            HashNode<K, T> aux = new HashNode<>(null, null);
            table.add(aux);
        }
    }

    @Override
    public void insert(K key, T elem) {
        if(0.75 < getLoadFactor()) newSize();
        // key -> index; index -> elem
        int code = hashcode(key);
        HashNode<K, T> newNode = new HashNode<>(key, elem);
        if(table.get(code).next==null){
            table.get(code).next = newNode;
        }else{
            HashNode<K, T> aux = table.get(code);
            while (aux.next!=null && !(aux.next.key.compareTo(key)==0)){ aux=aux.next; }
            aux.next = newNode;
        }
        nElems++;
    }

    @Override
    public T get(K key) {
        T aux = null;
        try {
            HashNode<K, T> currentNode = table.get(hashcode(key));
            while(currentNode.next != null && !(currentNode.next.key.compareTo(key)==0)){ currentNode = currentNode.next; }
            aux = currentNode.next.value;
        }catch (NullPointerException e) {
            System.out.println("Node K: "+ key +" does not exist, error code null");
        }
        return aux;
    }

    @Override
    public int search(K elem) {
        int numChecks = 1;
        try {
            HashNode<K, T> currentNode = table.get(hashcode(elem));
            while(currentNode.next != null && !(currentNode.next.key.compareTo(elem)==0)){ currentNode = currentNode.next; numChecks++;}
            if(currentNode.next == null) throw new NullPointerException();
            return numChecks;
        }catch (NullPointerException e) {
            System.out.println("Node K: "+ elem +" does not exist, error code -1");
        }
        return -1;
    }

    @Override
    public int lon() {
        //num of HashNode<K, T>s
        int num = 0;
        for(HashNode<K, T> node : table) {
            while(node.next != null){ node = node.next; num++;}
        }
        return num;
    }

    @Override
    public void delete(K key) {
        try {
            HashNode<K, T> currentNode = table.get(hashcode(key));
            while(currentNode.next != null && !(currentNode.next.key.compareTo(key)==0)){ currentNode = currentNode.next; }
            if(currentNode.next.key.compareTo(key)==0){
                //Deleting HashNode<K, T>
                currentNode.next = currentNode.next.next;
                nElems--;
            }else{
                throw new NullPointerException();
            }
        }catch (NullPointerException e) {
            System.out.println("Node K: "+ key +" cannot be reached");
        }
    }

    @Override
    public DoubleLinkedList<T> getValues() {
        DoubleLinkedList<T> aux = new DoubleLinkedList<>();
        for (HashNode<K, T> ref : table) {
            while (ref.next!=null){
                aux.add(ref.next.value);
                ref = ref.next;
            }
        }
        return aux;
    }

    @Override
    public DoubleLinkedList<K> getKeys() {
        DoubleLinkedList<K> aux = new DoubleLinkedList<>();
        for (HashNode<K, T> ref : table) {
            while (ref.next!=null){
                aux.add(ref.next.key);
                ref = ref.next;
            }
        }
        return aux;
    }

    @Override
    public float getLoadFactor() {
        return nElems/table.lon();
    }

    private int hashcode(K key){ return (Math.abs(key.hashCode())) % table.lon(); }

    private void newSize(){
        //Re sized table init
        StaticTable<HashNode<K,T>> aux = new StaticTable<>(table.lon()*2);
        for(int i=0;i<aux.lon();i++){
            HashNode<K, T> nullNode = new HashNode<>(null, null);
            aux.add(nullNode);
        }
        //Copying HashNodes
        int code;
        //Loop through current table
        for (HashNode<K, T> ref : table) {
            while (ref.next!=null){
                //Insert current node to new table: aux
                code = Math.abs(ref.next.key.hashCode()) % aux.lon();//hashcode of the new table
                HashNode<K, T> newNode = new HashNode<>(ref.next.key, ref.next.value);
                if(aux.get(code).next==null){
                    aux.get(code).next = newNode;
                }else{
                    HashNode<K, T> auxNode = aux.get(code);
                    while (auxNode.next!=null && !(auxNode.next.key.compareTo(newNode.key)==0)){ auxNode=auxNode.next; }
                    auxNode.next = newNode;
                }
                ref = ref.next;
            }
        }
        table = aux;
    }
}
