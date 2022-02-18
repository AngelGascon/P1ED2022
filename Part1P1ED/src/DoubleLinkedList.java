import java.lang.Iterable;
import java.util.Iterator;

public class DoubleLinkedList<T> implements TADdLinkedList<T>, Iterable<T>{

    private int size;
    private DoubleLinkedListNode head, tail;

    DoubleLinkedList(){
        create();
    }

    @Override
    public Iterator<T> iterator() {
        return new DllIterator(head);
    }

    private class DllIterator implements Iterator<T>{

        private DoubleLinkedListNode current;

        public DllIterator(DoubleLinkedListNode name){
            current = name;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            T info = current.getInfo(); //TODO why requires casting
            current = current.next;
            return info;
        }
    }

    private class DoubleLinkedListNode {

        DoubleLinkedListNode prev, next;
        T info;

        DoubleLinkedListNode(T info){
            this.info = info;
            prev = null;
            next = null;
        }

        public T getInfo() { return info; }
    }

    @Override
    public void create() {
        size = 0;
        head = new DoubleLinkedListNode(null);
        tail = new DoubleLinkedListNode(null);
    }

    @Override
    public void add(T elem) {
        DoubleLinkedListNode aux = new DoubleLinkedListNode(elem);
        if(size == 0){
            head.next = aux;
            aux.prev = head;
            aux.next = tail;
            tail.prev = aux;
        }else{
            aux.prev = tail.prev;
            tail.prev.next = aux;
            aux.next = tail;
            tail.prev = aux;
        }
        size++;
    }

    @Override
    public void add(int pos, T elem) {

    }

    @Override
    public T get(int pos) {
        return null;
    }

    @Override
    public int lon() {
        return 0;
    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public int search(T elem) {
        return 0;
    }
}
