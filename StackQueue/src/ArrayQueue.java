/**
 * Your implementation of an array-backed queue.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        back = -1;
        size = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("the size is zero "
            + "and there are no elements to dequeue");
//        } else if (size == 1) {
//            T element = backingArray[front];
//            backingArray[front] = null;
//            size--;
//            return element;
        } else {
            T element = backingArray[front];
            backingArray[front] = null;
            front = (front + 1) % backingArray.length;
            size--;
            return element;
        }
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface #enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        // must regrow and when that happens the index start must go back to 0
        if (data == null) {
            throw new IllegalArgumentException("Data pass in is null"
                    + ", please enter data that isn't null");
        } else if (size < backingArray.length) {
            back = (back + 1) % backingArray.length;
            backingArray[back] = data;
        } else {
            T[] temp = (T[]) new Object[size * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[(i + front) % backingArray.length];
            }
            front = 0;
            back = backingArray.length - 1;
            temp[++back] = data;
            backingArray = temp;
        }
        size++;
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

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
