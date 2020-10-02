package tp2;

import java.util.Iterator;

public class HashMap<KeyType, DataType> implements Iterable<KeyType> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor; // Compression factor

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY,
                DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = 1 / loadFactor;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     * @param key Value used to access to a particular instance of a DataType within map
     * @return Index value where this key should be placed in attribute map
     */
    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    /**
     * @return if map should be rehashed
     */
    private boolean needRehash() {
        return size * loadFactor > capacity;
    }

    /**
     * @return Number of elements currently in the map
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space for the map
     */
    public int capacity(){
        return capacity;
    }

    /**
     * @return if map does not contain any element
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** TODO Average Case : O(1)
     * Find the next prime after increasing the capacity by CAPACITY_INCREASE_FACTOR (multiplication)
     */
    private void increaseCapacity() {
        int nextCapacity = (capacity * CAPACITY_INCREASE_FACTOR) + 1; //even numbers cannot be prime
        double i = 3.0;

        while(i <= Math.floor(Math.sqrt(nextCapacity))){
            if((nextCapacity*10 / i) % 10 == 0){
                i = 3.0; // reset counter
                nextCapacity += 2; // increase to the next odd number
            } else {
                i += 2;
            }
        }
    }

    /** TODO Average Case : O(n)
     * Increases capacity by CAPACITY_INCREASE_FACTOR (multiplication) and
     * reassigns all contained values within the new map
     */
    private void rehash() {
        increaseCapacity();
        Node<KeyType, DataType>[] oldMap = new Node[capacity];
        System.arraycopy(map, 0, oldMap, 0, capacity);
        map = new Node[capacity];
        size = 0;

        for (int i = 0; i < capacity; i++){
            if(oldMap[i] != null){
                Node<KeyType, DataType> nodeToPlace = oldMap[i];
                while(nodeToPlace.next != null){
                    Node<KeyType, DataType> temp = oldMap[i].next;
                    nodeToPlace.next = null;
                    put(nodeToPlace.key, nodeToPlace.data);
                    nodeToPlace = temp;
                }
                put(nodeToPlace.key, nodeToPlace.data);
            }
        }
    }

    /** TODO Average Case : O(1)
     * Finds if map contains a key
     * @param key Key which we want to know if exists within map
     * @return if key is already used in map
     */
    public boolean containsKey(KeyType key) {
        if(map[hash(key)] != null){
            Node<KeyType, DataType> foundNode =  map[hash(key)];
            while(foundNode.key != key){
                if(foundNode.next == null){
                    return false;
                } else{
                    foundNode = foundNode.next;
                }
            }
            return true;
        }
        return false;
    }

    /** TODO Average Case : O(1)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        if(containsKey(key)){
            Node<KeyType, DataType> foundNode =  map[hash(key)];
            while(foundNode.key != key){
                foundNode = foundNode.next;
            }
            return foundNode.data;
        }
        return null;
    }

    /** TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        Node<KeyType, DataType> nodeToPlace = new Node<KeyType, DataType>(key, value);
        DataType oldValue = get(key);

        if(oldValue != null){
            Node<KeyType, DataType> nodeToReplace =  map[hash(key)];
            while(nodeToReplace.key != key){
                nodeToReplace = nodeToReplace.next;
            }
            nodeToReplace.data = value;
            return oldValue;
        }
        map[hash(key)] = nodeToPlace;
        size++;

        return null;
    }

    /** TODO Average Case : O(1)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        DataType oldValue = get(key);

        if(oldValue != null){
            Node<KeyType, DataType> nodeToRemove =  map[hash(key)];
            Node<KeyType, DataType> previousNode = null;
            while(nodeToRemove.key != key){
                previousNode = nodeToRemove;
                nodeToRemove = nodeToRemove.next;
            }

            if(previousNode == null){
                map[hash(key)] = nodeToRemove.next;
            } else {
                previousNode.next = nodeToRemove.next;
            }
            nodeToRemove.next = null;
            size--;
            return oldValue;
        }
        return null;
    }

    /** TODO Worst Case : O(1)
     * Removes all nodes contained within the map
     */
    public void clear() {
        for(int i = 0; i < capacity; i++){
            while(map[i] != null){
                remove(map[i].key);
            }
        }
    }

    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }

    @Override
    public Iterator<KeyType> iterator() {
        return new HashMapIterator();
    }

    // Iterators are used to iterate over collections like so:
    // for (Key key : map) { doSomethingWith(key); }
    private class HashMapIterator implements Iterator<KeyType> {
        // TODO: Add any relevant data structures to remember where we are in the list.

        /** TODO Worst Case : O(n)
         * Determine if there is a new element remaining in the hashmap.
         */
        public boolean hasNext() {
            return false;
        }

        /** TODO Worst Case : O(n)
         * Return the next new key in the hashmap.
         */
        public KeyType next() {
            return null;
        }
    }
}
