/**
 * Aux class that defines HashNodes
 * @param <K> key of the node
 * @param <T> value of the node
 */
public class HashNode<K, T> implements Comparable<HashNode<K, T>> {
    protected K key;
    protected T value;
    protected HashNode<K, T> next;

    public HashNode(K key, T value){
        this.key = key;
        this.value = value;
        next = null;
    }

    @Override
    public int compareTo(HashNode<K, T> o) {
        return o.compareTo(this);
    }
}