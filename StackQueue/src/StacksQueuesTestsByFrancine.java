import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the stack and queue classes.
 *
 * @author Francine Luo
 * @version 1.0
 */
public class StacksQueuesTestsByFrancine {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStack() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        //testing push
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertEquals(10, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        for (int i = 0; i < 10; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, backingArray);

        //testing pop
        assertEquals((Integer) 9, stack.pop());
        assertEquals((Integer) 8, stack.pop());
        assertEquals((Integer) 7, stack.pop());
        assertEquals(7, stack.size());

        //testing resize
        for (int i = 7; i < 15; i++) {
            stack.push(i);
        }
        assertEquals(15, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStack() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        //testing push
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertEquals(10, stack.size());
        assertEquals(9, ((LinkedStack) stack).getHead().getData());
        assertEquals(8, ((LinkedStack) stack).getHead().getNext().getData());

        //testing pop
        assertEquals((Integer) 9, stack.pop());
        assertEquals((Integer) 8, stack.pop());
        assertEquals((Integer) 7, stack.pop());
        assertEquals(7, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        //testing enqueue
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertEquals(10, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        for (int i = 0; i < 10; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, backingArray);

        //testing dequeue
        assertEquals((Integer) 0, queue.dequeue());
        assertEquals((Integer) 1, queue.dequeue());
        assertEquals((Integer) 2, queue.dequeue());
        assertEquals(7, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[StackInterface.INITIAL_CAPACITY];
        for (int i = 3; i < 10; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, backingArray);

        //testing resize
        for (int i = 10; i < 20; i++) {
            queue.enqueue(i);
        }
        assertEquals(17, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[StackInterface.INITIAL_CAPACITY * 2];
        for (int i = 3; i < 20; i++) {
            expected[i - 3] = i;
        }
        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        //testing enqueue
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertEquals(10, queue.size());
        assertEquals(0, ((LinkedQueue) queue).getHead().getData());
        assertEquals(1, ((LinkedQueue) queue).getHead().getNext().getData());
        assertEquals(9, ((LinkedQueue) queue).getTail().getData());
        assertEquals(null, ((LinkedQueue) queue).getTail().getNext());

        //testing dequeue
        assertEquals((Integer) 0, queue.dequeue());
        assertEquals((Integer) 1, queue.dequeue());
        assertEquals((Integer) 2, queue.dequeue());
        assertEquals(7, queue.size());
        assertEquals(3, ((LinkedQueue) queue).getHead().getData());
        assertEquals(9, ((LinkedQueue) queue).getTail().getData());

        queue.enqueue(10);
        assertEquals(3, ((LinkedQueue) queue).getHead().getData());
        assertEquals(10, ((LinkedQueue) queue).getTail().getData());
    }
}
