import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements TADList<T>, Iterable<T> {

    private int size;
    private DoubleLinkedListNode head, tail;

    public DoubleLinkedList(){
        create();
    }

    @Override
    public Iterator<T> iterator() { return new DllIterator(head); }

    protected class DllIterator implements Iterator<T>{

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
            current = current.next;
            return current.getInfo();
        }

        public DoubleLinkedListNode getCurrent(){ return current; }
    }

    protected class DoubleLinkedListNode {

        protected DoubleLinkedListNode prev, next;
        protected T info;

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
    public int search (T elem) {
        DllIterator it = new DllIterator(head);
        int aux = 0;
        boolean found = false;

        while (it.hasNext() && !found){
            if(elem.compareTo(it.next())==0) found =true;
            aux++;
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
