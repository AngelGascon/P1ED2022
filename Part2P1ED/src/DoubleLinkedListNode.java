/**
 * Aux class that defines the Double Linked List nodes
 * @author angel.gascon@estudiants.urv.cat
 * @param <T> info of the node
 */
public class DoubleLinkedListNode<T> {

    protected DoubleLinkedListNode<T> prev, next;
    protected T info;

    /**
     * Constructor
     * @param info info of the node
     */
    DoubleLinkedListNode(T info){
        this.info = info;
        prev = null;
        next = null;
    }

    /**
     * getter T
     * @return info of the node <T>
     */
    public T getInfo() { return info; }
}