/**
 * TAD that defines a Double Linked List
 *
 * @author : angel.gascon@estudiants.urv.cat
 *
 */
public interface TADHashTable<K,T> {

    /**
     * Initialize List
     */
    void create();

    /**
     * Adds an element at the end of the list
     * @param elem T
     */
    void insert(K key, T elem);

    /**
     * Gets the element from the specified position
     * Throws exception if element does not exist
     * @param key K
     * @return T
     */
    T get(K key);

    /**
     * Checks an element on the list, returns number of elements accessed to check
     * Throws exception if element does not exist, also returns number of elements accessed to check
     * @param elem K
     * @return int
     */
    int search (K elem);

    /**
     * Returns current length of the table
     * @return int
     */
    int lon();

    /**
     * Deletes element at the specified position
     * Throws exception if position can not be reached
     * @param key K
     */
    void delete(K key);

    /**
     * returns values of the table
     * @return values list
     */
    TADList<T> getValues();

    /**
     * returns keys of the table
     * @return keys list
     */
    TADList<K> getKeys();

    /**
     * returns current load factor
     * @return LoadFactor
     */
    float getLoadFactor();
}
