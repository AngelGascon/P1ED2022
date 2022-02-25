import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;

public class HashTable <K, T extends Comparable<T>> implements TADHashTable<K,T>, Iterable<T> {

    private int size;
    private List<K> keys;
    private List<K> data;

    public HashTable(){
        create();
    }

    @Override
    public void create() {
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void insert(K key, T elem) {

    }

    @Override
    public T get(K key) {
        return null;
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
}
