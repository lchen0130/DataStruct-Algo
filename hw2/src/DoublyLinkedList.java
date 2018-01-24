
/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    // _ _ _ _ _
    // size = 5
    // length = 5
    // max index = 4
    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("Please "
                    + "enter index between zero and size (non inclusive)");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null "
                + "please enter something other than null");
        }
        LinkedListNode currentNode = head;
        if (isEmpty()) {
            putHead(data);
        } else if (index == 0) {
            head = new LinkedListNode<T>(data, null, head);
        } else if (index == size) {
            tail.setNext(new LinkedListNode<T>(data, tail, null));
            tail = tail.getNext();
        } else {
            if (index > size / 2) {
                currentNode = tail;
                for (int i = size - 1; i > index; i--) {
                    currentNode = currentNode.getPrevious();
                }
            } else {
                for (int i = 0; i < index; i++) {
                    // brings current node to the one at index
                    currentNode = currentNode.getNext();
                }
            }
            LinkedListNode temp = new LinkedListNode<T>(data,
                    currentNode.getPrevious(), currentNode);
            currentNode.setPrevious(temp);
            temp.getPrevious().setNext(temp);
        }


        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Please "
                    + "enter valid data that's not null");
        }
        if (isEmpty()) {
            putHead(data);
        } else {
            head = new LinkedListNode<T>(data, null, head);
            head.getNext().setPrevious(head);
        }
        size++;
    }

    /**
     * Adds a head node and sets tail pointing to head
     * @param data the input of the new node
     */
    private void putHead(T data) {
        head = new LinkedListNode<T>(data, null, head);
        tail = head;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Please "
                    + "enter valid data that's not null");
        }
        if (isEmpty()) {
            putHead(data);
        } else {
            tail.setNext(new LinkedListNode<T>(data, tail, null));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Please "
                    + "enter index between zero and size (non inclusive)");
        }
        LinkedListNode<T> currentNode = head;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return currentNode.getData();
        } else if (index == 0) {
            LinkedListNode<T> temp = head;
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return temp.getData();
        } else if (index == size - 1) {
            LinkedListNode<T> temp = tail;
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
            size--;
            return temp.getData();
        }
        for (int i = 0; i < index; i++) {
            //stops at the the one to be removed
            currentNode = currentNode.getNext();
        }
        T tempData = currentNode.getData();
        currentNode.getPrevious().setNext(currentNode.getNext());
        currentNode.getNext().setPrevious(currentNode.getPrevious());
        size--;
        return tempData;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T tempData = head.getData();
        if (size == 1) {
            tail = null;
            head = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return tempData;
    }

    @Override
    public T removeFromBack() {
        if (head == null) {
            return null;
        }
        T tempData = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return tempData;
    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Please "
                    + "enter valid data that's not null");
        }
        if (head == null) {
            return false;
        }
        LinkedListNode<T> currentNode = head;
        boolean found = false;
        while (!found && currentNode.getNext() != null) {
            T currentData = currentNode.getData();
            if (currentData.equals(data)) {
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                currentNode.getPrevious().setNext(currentNode.getNext());
                size--;
                return true;
            } else {
                currentNode = currentNode.getNext();
            }
        }

        return false;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Please "
                    + "enter index between zero and size (non inclusive)");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getData();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] listArr = new Object[size];
        LinkedListNode currentNode = head;
        if (head == null) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            listArr[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return listArr;
    }

    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
