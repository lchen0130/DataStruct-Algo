/**
 * Your implementation of a min priority queue.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {
        backingHeap = new MinHeap<>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new java.util.NoSuchElementException("item passed in is "
                + "null, please pass in valid input that isn't null");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (backingHeap.isEmpty()) {
            throw new java.util.NoSuchElementException("The priority queue "
                + "is empty and cannot dequeue anything");
        }
        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap;
    }

}
