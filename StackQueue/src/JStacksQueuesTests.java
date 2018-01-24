import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the stack and queue classes.
 *
 * @author Jasmine Liu
 * @version 1.0
 */

public class JStacksQueuesTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void arrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        assertEquals(20, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        assertEquals(26, backingArray.length);

        Object[] expected = new Object[26];
        for (int i = 0; i < 20; i++) {
            expected[i] = i;
        }

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void arrayStackPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        assertEquals((Integer) 19, stack.pop());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        assertEquals(26, backingArray.length);

        Object[] expected = new Object[26];
        for (int i = 0; i < 19; i++) {
            expected[i] = i;
        }

        assertArrayEquals(expected, backingArray);

        for (int i = 0; i < 19; i++) {
            stack.pop();
        }

        backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        assertEquals(0, stack.size());
        assertEquals(26, backingArray.length);
    }

    @Test(timeout = TIMEOUT)
    public void linkedStackPush() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        assertEquals(19, ((LinkedStack) stack).getHead().getData());
        assertEquals(20, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void linkedStackPop() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        assertEquals((Integer) 19, stack.pop());
        assertEquals(18, ((LinkedStack) stack).getHead().getData());

        for (int i = 0; i < 19; i++) {
            stack.pop();
        }

        assertEquals(null, ((LinkedStack) stack).getHead());
        assertEquals(0, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void arrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        for (int i = 0; i < 13; i++) {
            queue.enqueue(i);
        }

        assertEquals(13, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];

        for (int i = 0; i < 13; i++) {
            expected[i] = i;
        }

        assertEquals(13, backingArray.length);
        assertArrayEquals(expected, backingArray);

        //Regrow array
        queue = new ArrayQueue<>();

        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        assertEquals(20, queue.size());

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        expected = new Object[26];

        for (int i = 0; i < 20; i++) {
            expected[i] = i;
        }

        assertEquals(26, backingArray.length);
        assertArrayEquals(expected, backingArray);

    }

    @Test(timeout = TIMEOUT)
    public void arrayQueueDequeue() {
        queue = new ArrayQueue<>();

        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        //dequeue partially
        assertEquals((Integer) 0, queue.dequeue());

        for (int i = 0; i < 4; i++) {
            queue.dequeue();
        }

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[26];

        for (int i = 5; i < 20; i++) {
            expected[i] = i;
        }

        assertEquals(15, queue.size());
        assertEquals(26, backingArray.length);
        assertArrayEquals(expected, backingArray);

        //dequeue entirely

        for (int i = 0; i < 15; i++) {
            queue.dequeue();
        }

        expected = new Object[26];
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        assertEquals(0, queue.size());
        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void arrayCircularTest() {

        queue = new ArrayQueue<>();
        Object[] backingArray;
        Object[] expected;

        //0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[13];

        for (int i = 0; i < 10; i++) {
            expected[i] = i;
        }

        assertEquals(10, queue.size());
        assertArrayEquals(expected, backingArray);

        //null, null, null, 3, 4, 5, 6, 7, 8, 9, null, null, null
        assertEquals((Integer) 0, queue.dequeue());
        assertEquals((Integer) 1, queue.dequeue());
        assertEquals((Integer) 2, queue.dequeue());

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        expected[0] = null;
        expected[1] = null;
        expected[2] = null;

        assertEquals(7, queue.size());
        assertArrayEquals(expected, backingArray);

        //13, null, null, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        expected[0] = 13;
        expected[10] = 10;
        expected[11] = 11;
        expected[12] = 12;

        assertEquals(11, queue.size());
        assertArrayEquals(expected, backingArray);

        //3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, null, null, null, null,
        //null, null, null, null, null, null, null
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[26];

        for (int i = 0; i < 15; i++) {
            expected[i] = i + 3;
        }

        assertEquals(15, queue.size());
        assertArrayEquals(expected, backingArray);

        //null * 26
        for (int i = 0; i < 15; i++) {
            queue.dequeue();
        }

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[26];

        assertEquals(0, queue.size());
        assertArrayEquals(expected, backingArray);

        //null, null, null, null, null, null, null, null, null, null, null, null,
        //null, null, null, 1, 2, 3, 4, 5, null, null, null, null, null, null
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected[15] = 1;
        expected[16] = 2;
        expected[17] = 3;
        expected[18] = 4;
        expected[19] = 5;

        assertEquals(5, queue.size());
        assertArrayEquals(expected, backingArray);

        //null, null, null, null, null, null, null, null, null, null, null, null,
        //null, null, null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected[20] = 6;
        expected[21] = 7;
        expected[22] = 8;
        expected[23] = 9;
        expected[24] = 10;
        expected[25] = 11;

        assertEquals(11, queue.size());
        assertArrayEquals(expected, backingArray);

        //12, 13, null, null, null, null, null, null, null, null, null, null,
        //null, null, null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        queue.enqueue(12);
        queue.enqueue(13);

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected[0] = 12;
        expected[1] = 13;

        assertEquals(13, queue.size());
        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void linkedQueueEnqueue() {

        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(1);

        assertEquals(1, ((LinkedQueue) queue).getHead().getData());
        assertEquals(1, ((LinkedQueue) queue).getTail().getData());

        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals(1, ((LinkedQueue) queue).getHead().getData());
        assertEquals(5, ((LinkedQueue) queue).getTail().getData());

        assertEquals(5, queue.size());

    }

    @Test(timeout = TIMEOUT)
    public void linkedQueueDequeue() {
        queue = new LinkedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals((Integer) 1, queue.dequeue());

        assertEquals(2, ((LinkedQueue) queue).getHead().getData());
        assertEquals(5, ((LinkedQueue) queue).getTail().getData());

        assertEquals((Integer) 2, queue.dequeue());
        assertEquals((Integer) 3, queue.dequeue());
        assertEquals((Integer) 4, queue.dequeue());
        assertEquals((Integer) 5, queue.dequeue());

        assertEquals(0, queue.size());

        assertEquals(null, ((LinkedQueue) queue).getHead());
        assertEquals(null, ((LinkedQueue) queue).getTail());
    }
}
