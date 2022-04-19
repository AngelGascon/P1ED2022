import java.lang.Iterable;
import java.util.Iterator;

/**
 * Double Linked List class defined using TADList and aux class DoubleLinkedList for its nodes
 * @author angel.gascon@estudiants.urv.cat
 * @param <T> info of the nodes
 */
public class DoubleLinkedList<T extends Comparable<T>> implements TADList<T>, Iterable<T> {

    private int size;
    private DoubleLinkedListNode<T> head, tail;

    public DoubleLinkedList(){
        create();
    }

    @Override
    public Iterator<T> iterator() { return new DllIterator(head); }

    protected class DllIterator implements Iterator<T>{

        protected DoubleLinkedListNode<T> current;

        public DllIterator(DoubleLinkedListNode<T> name){
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

        public DoubleLinkedListNode<T> getCurrent(){ return current; }
    }

    @Override
    public void create() {
        size = 0;
        head = new DoubleLinkedListNode<>(null);
        tail = new DoubleLinkedListNode<>(null);
    }

    @Override
    public void add(T elem) {
        DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>(elem);
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
            DoubleLinkedListNode<T> inAux = new DoubleLinkedListNode<>(elem);
            DoubleLinkedListNode<T> itAux = this.getListNode(pos);
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
            DoubleLinkedListNode<T> aux = this.getListNode(pos);
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
        try{
            while (it.hasNext() && !found){
                if(elem.compareTo(it.next())==0) found =true;
                aux++;
            }
            return aux;
        }catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Returns node reference at the specified position.
     * If pos > than current number of nodes, returns tail reference.
     * @param pos int
     * @return DoubleLinkedListNode<T>
     */
    private DoubleLinkedListNode<T> getListNode(int pos){
        DllIterator it = new DllIterator(head);
        int aux = 0;
        while (it.hasNext() && aux <= pos){ it.next(); aux++; }
        return it.getCurrent();
    }
}
