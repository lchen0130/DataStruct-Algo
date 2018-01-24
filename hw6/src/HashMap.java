import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("input values for either key "
                + "or value is null, please enter valid entries");
        }
        boolean foundRem = false;
        int remIndex = 0;
        if (((size + 1) / (double) table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int bucket = key.hashCode() % table.length;
        for (int i = 0; i < table.length; i++) {
            int currIndex = Math.abs((bucket + i) % table.length);
            if (table[currIndex] == null) {
                if (foundRem) {
                    table[remIndex] = new MapEntry<>(key, value);
                    table[remIndex].setRemoved(false);
                } else {
                    table[currIndex] = new MapEntry<>(key, value);
                    table[currIndex].setRemoved(false);
                }
                size++;
                return null;
            } else if (table[currIndex].isRemoved() && !foundRem) {
                remIndex = currIndex;
                foundRem = true;
            } else if (table[currIndex].getKey().equals(key)) {
                V tempVal = table[currIndex].getValue();
                table[currIndex].setKey(key);
                table[currIndex].setValue(value);
                return tempVal;
            }

        }
        // doesnt exist and can't fit in
        return null; //TODO is this the right way to structure these statements?
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key input is null"
                + "please input valid key that isn't null");
        }
        int currIndex = (key.hashCode() % table.length);
        for (int i = 0; i < table.length; i++) {
            int chaIndex = Math.abs((currIndex + i) % table.length);
            if (table[chaIndex] == null) { //null check that terminates
                throw new java.util.NoSuchElementException("There isn't a "
                        + "matching key in this map, please enter a different "
                        + "key");
            } else if (table[chaIndex].getKey().equals(key)
                    && !table[chaIndex].isRemoved()) {
                table[chaIndex].setRemoved(true);
                size--;
                return table[chaIndex].getValue();
            }
        }
        throw new java.util.NoSuchElementException("There isn't a "
                + "matching key in this map, please enter a different "
                + "key");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null, please "
                + "enter valid key entry");
        }
        int currIndex = key.hashCode() % table.length;
        for (int i = 0; i < table.length; i++) {
            int chaIndex = Math.abs((currIndex + i) % table.length);
            if (table[chaIndex] == null) {
                throw new java.util.NoSuchElementException("Key is not in "
                    + "map, please enter another key");
            } else if (table[chaIndex].getKey().equals(key)
                    && !table[chaIndex].isRemoved()) {
                return table[chaIndex].getValue();
            }
        }
        throw new java.util.NoSuchElementException("Key was not found in "
            + "this data structure. Please enter another key that exists");
    }


    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null, please "
                    + "enter valid key entry");
        }
        int currIndex = Math.abs(key.hashCode() % table.length); //taking in key
        for (int i = 0; i < table.length; i++) {
            int chaIndex = (currIndex + i) % table.length;
            if (table[chaIndex] == null) {
                return false;
            } else if (table[chaIndex].getKey().equals(key)
                    && !table[chaIndex].isRemoved()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;

    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        java.util.HashSet<K> hashKey = new java.util.HashSet<>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                hashKey.add(table[i].getKey());
            }
        }
        return hashKey;
    }

    @Override
    public List<V> values() {
        java.util.LinkedList<V> valueList = new java.util.LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                valueList.add(table[i].getValue());
            }
        }
        return valueList;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length is less than or equal to"
                    + " zero please enter a valid length greater than 0");
        }
        if (length < size) {
            throw new IllegalArgumentException("length is less than size of "
                + "hashmap, please enter valid length");
        }
        MapEntry<K, V>[] temp = new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                int currIndex = Math.abs(table[i].getKey().hashCode()
                        % temp.length);
                while (temp[currIndex] != null) {
                    currIndex = ++currIndex % temp.length;
                }
                temp[currIndex] = table[i];
            }
        }
        table = temp;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
// TODO check size variable