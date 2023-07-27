import java.util.Objects;

public class SeparateChainingHashTable {

    // Array of HashNodes that represents the separate chains in the hash table
    private HashNode[] chains;
    // Capacity of the hash table
    private Integer capacity;
    private Integer size; // number of key-value pairs in hash table or number of hash nodes in a HashTable

    // Default constructor with default capacity of 10
    public SeparateChainingHashTable() {
        this(10); // default capacity
    }

    // Constructor that initializes the hash table with the given capacity
    public SeparateChainingHashTable(Integer capacity) {
        this.capacity = capacity;
        this.chains = new HashNode[capacity];
        this.size = 0;
    }

    // Inner class representing each node in the separate chains
    private static class HashNode {
        private Integer key;
        private String value;
        private HashNode next; // reference to next HashNode

        public HashNode(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Returns the number of key-value pairs in the hash table
    public Integer size() {
        return size;
    }

    // Returns whether the hash table is empty or not
    public boolean isEmpty() {
        return size == 0;
    }

    // Adds a key-value pair to the hash table
    public void put(Integer key, String value) {
        Objects.requireNonNull(key, "Key must not be null");
        Objects.requireNonNull(value, "Value must not be null");

        int bucketIndex = getBucketIndex(key);
        HashNode head = chains[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        HashNode newNode = new HashNode(key, value);
        newNode.next = chains[bucketIndex];
        chains[bucketIndex] = newNode;
    }

    // Gets the index of the bucket in which the key should be stored
    private Integer getBucketIndex(Integer key) {
        return key % capacity;
    }

    // Gets the value associated with the given key
    public String get(Integer key) {
        Objects.requireNonNull(key, "Key must not be null");

        int bucketIndex = getBucketIndex(key);
        for (HashNode currentNode = chains[bucketIndex]; currentNode != null; currentNode = currentNode.next) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
        }

        return null;
    }

    // Removes the key-value pair associated with the given key


    public String remove(final Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null!");
        }

        final int bucketIndex = getBucketIndex(key);
        final HashNode head = chains[bucketIndex];
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            chains[bucketIndex] = head.next;
            size--;
            return head.value;
        }
        HashNode previous = head;
        HashNode current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                previous.next = current.next;
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }
}
