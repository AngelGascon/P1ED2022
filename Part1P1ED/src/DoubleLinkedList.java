import java.lang.Iterable;
import java.util.Iterator;

public class DoubleLinkedList<T> implements TADdLinkedList<T>, Iterable<T> {

    private int size;
    private DoubleLinkedListNode head, tail;

    public DoubleLinkedList(){
        create();
    }

    @Override
    public Iterator<T> iterator() { return new DllIterator(head); }

    private class DllIterator implements Iterator<T>{

        protected DoubleLinkedListNode current;

        public DllIterator(DoubleLinkedListNode name){
            current = name;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            T info = current.getInfo();
            current = current.next;
            return info;
        }

        public DoubleLinkedListNode getCurrent(){ return current; }
    }

    private class DoubleLinkedListNode implements Comparable<T> {

        protected DoubleLinkedListNode prev, next;
        protected T info;

        DoubleLinkedListNode(T info){
            this.info = info;
            prev = null;
            next = null;
        }

        public T getInfo() { return info; }

        @Override
        public int compareTo(T o) {
            // returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
            // Using compareTo defined in java.lang.String, viable to look for elements that are equal
            return (info.toString().compareTo(o.toString()));
        }
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
        }else{
            aux.prev = tail.prev;
            tail.prev.next = aux;
        }
        aux.next = tail;
        tail.prev = aux;
        size++;
    }

    @Override
    public void add(int pos, T elem) {
        try{
            DoubleLinkedListNode inAux = new DoubleLinkedListNode(elem);
            DoubleLinkedListNode itAux = this.getListNode(pos);
            //Adding new node pointers
            inAux.next = itAux;
            inAux.prev = itAux.prev;
            //Updating current node pointers
            itAux.prev.next = inAux;
            itAux.prev = inAux;
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T get(int pos) {
        return this.getListNode(pos).getInfo();
    }

    @Override
    public int lon() {
        return size;
    }

    @Override
    public void delete(int pos) {
        try {
            DoubleLinkedListNode aux = this.getListNode(pos);
            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
            size--;
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int search(T elem) {
        DllIterator it = new DllIterator(head);
        int aux = 0;
        boolean found = false;

        while (it.hasNext() && !found){
            it.next(); aux++;
            if(it.getCurrent().compareTo(elem)==0) found =true;//TODO needs to start comparing after it.next(), first iteration is head and throws nullPointerException
        }
        return aux;
    }

    /**
     * Returns node reference at the specified position.
     * If pos > than current number of nodes, returns tail reference.
     * @param pos int
     * @return DoubleLinkedListNode
     */
    private DoubleLinkedListNode getListNode(int pos){
        DllIterator it = new DllIterator(head);
        int aux = 0;
        while (it.hasNext() && aux <= pos){ it.next(); aux++; }
        return it.getCurrent();
    }
}
