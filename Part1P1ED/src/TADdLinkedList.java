/**
 * TAD that defines a Double Linked List
 *
 * @author : angel.gascon@estudiants.urv.cat
 *
 */
public interface TADdLinkedList<T> {

    /**
     * Initialize List
     */
    void create();

    /**
     * Adds an element at the end of the list
     * @param elem T
     */
    void add(T elem);

    /**
     * Adds an element at the specified position
     * Throws exception if position can not be reached
     * @param pos int
     * @param elem T
     */
    void add(int pos, T elem);

    /**
     * Gets the element from the specified position
     * Throws exception if element does not exist
     * @param pos int
     * @return T
     */
    T get(int pos);

    /**
     * Returns current length of the list
     * @return int
     */
    int lon();

    /**
     * Deletes element at the specified position
     * Throws exception if position can not be reached
     * @param pos int
     */
    void delete(int pos);

    /**
     * Checks an element on the list, returns number of elements accessed to check
     * Throws exception if element does not exist, also returns number of elements accessed to check
     * @param elem T
     * @return int
     */
    int search(T elem);
}
