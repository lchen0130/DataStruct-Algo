/**
 * Your implementation of an ArrayList.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("please "
                    + "enter valid index");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("passed "
                    + "in data is null");
        }
        checkResize();
        if (index == size) {
            backingArray[index] = data;
        } else {
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("passed in data is null");
        }
        checkResize();
        for (int i = backingArray.length - 2; i >= 0; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("passed in data is null");
        }
        checkResize();
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("please "
                    + "enter valid index");
        }
        T tempItem;
        if (index == size) {
            tempItem = backingArray[size];
            backingArray[size] = null;
        } else {
            tempItem = backingArray[index];
            for (int i = index + 1; i < backingArray.length; i++) {
                backingArray[i - 1] = backingArray[i];
            }
        }
        backingArray[size - 1] = null;
        size--;
        return tempItem;
    }

    @Override
    public T removeFromFront() {
        T tempItem = backingArray[0];
        for (int i = 0; i < backingArray.length - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        return tempItem;
    }

    @Override
    public T removeFromBack() {
        T tempItem = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return tempItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("please "
                    + "pass in valid index");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
     *
     * Method checks if the backing array needs to be resized or not and then
     * resize if needed
     */
    private void checkResize() {
        if (size >= backingArray.length) {
            T[] tempArr = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArr[i] = backingArray[i];
            }
            backingArray = tempArr;
        }
    }
}
