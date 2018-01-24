/**
 * Your implementation of a min heap.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item passed in is null, "
                    + "please pass in valid data that isn't null");
        }
        if (backingArray.length - 1 == size) {
            T[] tempArr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArr[i] = backingArray[i];
            }
            backingArray = tempArr;
        }
        backingArray[++size] = item;
        int curr = size;

        while (curr != 1 && backingArray[curr].compareTo(
                backingArray[curr / 2]) < 0) {
            T temp = backingArray[curr];
            backingArray[curr] = backingArray[curr / 2];
            backingArray[curr / 2] = temp;
            curr = curr / 2;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap is empty and"
                    + "there isn't an element that can be removed");
        }
        T elem = backingArray[1];
        if (size == 1) { // one element
            backingArray[1] = null;
            size--;
        } else {
            backingArray[1] = backingArray[size];
            backingArray[size--] = null;
            heapifyMin(1);
        }
        return elem;
    }

    /**
     * recursive helper that heapifys from index
     * @param curr index to heapify
     */
    private void heapifyMin(int curr) {
        if (curr > (size / 2)) {
            return;
        }
        int smallestIndex = curr;
        int right = (curr * 2) + 1;
        int left = curr * 2;
        if (backingArray[right] != null
                && (backingArray[curr].compareTo(backingArray[right]) > 0)) {
            smallestIndex = right;
        }
        if (backingArray[left] != null
                && (backingArray[smallestIndex].compareTo(
                        backingArray[left]) > 0)) {
            smallestIndex = left;
        }

        if (smallestIndex != curr) {
            // swap the two
            T temp = backingArray[curr];
            backingArray[curr] = backingArray[smallestIndex];
            backingArray[smallestIndex] = temp;
            heapifyMin(smallestIndex);
        }



    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
