import java.util.ArrayList;

public class HashTable <K, T> implements TADHashTable<K,T> {

    private float loadFactor;
    private ArrayList<HashNode> table;

    protected class HashNode {
        protected K key;
        protected T value;
        protected HashNode next;

        public HashNode(K key, T value){
            this.key = key;
            this.value = value;
            next = null;
        }

        public T getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }

    //HashList
    public HashTable(){
        create();
    }

    @Override
    public void create() {
        loadFactor = 0;
        table = new ArrayList<>();
        for(int i=0;i<10;i++){
            HashNode aux = new HashNode(null, null);
            table.add(aux);
        }
    }

    @Override
    public void insert(K key, T elem) {
        // key -> index; index -> elem
        int code = (Math.abs(key.hashCode())) % table.size();
        HashNode newNode = new HashNode(key, elem);
        if(table.get(code).next==null){
            table.get(code).next = newNode;
        }else{
            HashNode aux = table.get(code);
            while (aux.next!=null && !aux.next.getKey().equals(key)){ aux=aux.next; }
            aux.next = newNode;
        }
    }

    @Override
    public T get(K key) {
        T aux = null;
        int code = (Math.abs(key.hashCode())) % table.size();
        try {
            HashNode currentNode = table.get(code);
            while(currentNode.next != null && !currentNode.next.getKey().equals(key)){ currentNode = currentNode.next; }
            aux = currentNode.next.getValue();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return aux;
    }

    @Override
    public int search(K elem) {
        return 0;
    }

    @Override
    public int lon() {
        return 0;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public TADList<T> getValues() {
        return null;
    }

    @Override
    public TADList<K> getKeys() {
        return null;
    }

    @Override
    public float getLoadFactor() {
        return 0;
    }

    private int hashcode(K key){ return (Math.abs(key.hashCode())) % table.size(); }
}
