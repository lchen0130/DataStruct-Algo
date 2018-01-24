import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author Sanath Nagaraj
 * @version 1.0
 */
public class StacksQueuesTestsSanath {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 2000;

    @Test(timeout = 100000)
    public void testArrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        // Pushing Until capacity
        Object[] expected = new Object[13];
        for (int i = 0; i < 13; i++) {
            expected[i] = i;
            stack.push(i);
        }
        assertEquals(13, stack.size());
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Resizing backing array test
        expected = new Object[26];
        for (int i = 0; i < 14; i++) {
            expected[i] = i;
        }
        stack.push(13);
        assertEquals(14, stack.size());
        backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Continuing to push after resize;
        expected = new Object[26];
        for (int i = 0; i < 14; i++) {
            expected[i] = i;
        }
        for (int i = 14; i < 20; i++) {
            expected[i] = i;
            stack.push(i);
        }
        assertEquals(20, stack.size());
        backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Pushing 100000 elements
        expected = new Object[106496];
        for (int i = 0; i < 20; i++) {
            expected[i] = i;
        }
        for (int i = 20; i < 100020; i++) {
            expected[i] = i;
            stack.push(i);
        }
        assertEquals(100020, stack.size());
        backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = 100000)
    public void testArrayStackPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        // Pushing 100000 elements
        Object[] expected = new Object[106496];
        for (int i = 0; i < 100020; i++) {
            expected[i] = i;
            stack.push(i);
        }
        assertEquals(100020, stack.size());
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Testing popping
        Object[] poppedArray = new Object[106496];
        for (int i = 100020 - 1; i >= 0 ; i--) {
            poppedArray[i] = stack.pop();
        }
        assertEquals(0, stack.size());
        assertArrayEquals(expected, poppedArray);
    }

    @Test(timeout = 100000)
    public void testArrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        // enqueueing Until capacity
        Object[] expected = new Object[13];
        for (int i = 0; i < 13; i++) {
            expected[i] = i;
            queue.enqueue(i);
        }
        assertEquals(13, queue.size());
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Resizing backing array test
        expected = new Object[26];
        for (int i = 0; i < 14; i++) {
            expected[i] = i;
        }
        queue.enqueue(13);
        assertEquals(14, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Continuing to enqueue after resize;
        expected = new Object[26];
        for (int i = 0; i < 14; i++) {
            expected[i] = i;
        }
        for (int i = 14; i < 20; i++) {
            expected[i] = i;
            queue.enqueue(i);
        }
        assertEquals(20, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Enqueueing 100000 elements
        expected = new Object[106496];
        for (int i = 0; i < 20; i++) {
            expected[i] = i;
        }
        for (int i = 20; i < 100020; i++) {
            expected[i] = i;
            queue.enqueue(i);
        }
        assertEquals(100020, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = 100000)
    public void testArrayQueueDequeue() {
        queue = new ArrayQueue<>();
        // Enqueueing 100000 elements
        Object[] expected = new Object[106496];
        for (int i = 0; i < 100020; i++) {
            expected[i] = i;
            queue.enqueue(i);
        }
        assertEquals(100020, queue.size());
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Testing Dequeueing
        Object[] dqeueuedArray = new Object[106496];
        for (int i = 0; i < 100020; i++) {
            dqeueuedArray[i] = queue.dequeue();
        }
        assertEquals(0, queue.size());
        assertArrayEquals(expected, dqeueuedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueCircular() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        // enqueueing Until capacity
        Object[] expected = new Object[13];
        for (int i = 0; i < 13; i++) {
            expected[i] = i;
            queue.enqueue(i);
        }
        assertEquals(13, queue.size());
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Dequeueing 4 elements
        for (int i = 0; i < 4; i++) {
            Object thisOne = queue.dequeue();
            assertEquals(thisOne, i);
            expected[i] = null;
        }
        assertEquals(9, queue.size());
        assertArrayEquals(expected, backingArray);

        // Refilling to capacity
        for (int i = 13; i < 17; i++) {
            expected[i % 13] = i;
            queue.enqueue(i);
        }
        assertEquals(13, queue.size());
        assertArrayEquals(expected, backingArray);

        // Resizing backing array test
        expected = new Object[26];
        for (int i = 4; i < 18; i++) {
            expected[i - 4] = i;
        }
        queue.enqueue(17);
        assertEquals(14, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Continuing to enqueue after resize;
        for (int i = 18; i < 22; i++) {
            expected[i - 4] = i;
            queue.enqueue(i);
        }
        assertEquals(18, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Dequeueing after resize:
        for (int i = 0; i < 4; i++) {
            Object thisOne = queue.dequeue();
            assertEquals(thisOne, i + 4);
            expected[i] = null;
        }
        assertEquals(14, queue.size());
        assertArrayEquals(expected, backingArray);

        // Continuing to enqueue until rotation;
        for (int i = 22; i < 34; i++) {
            expected[(i - 4) % 26] = i;
            queue.enqueue(i);
        }
        assertEquals(26, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Dequeue elements starting at middle of array
        for (int i = 0; i < 4; i++) {
            Object thisOne = queue.dequeue();
            assertEquals(thisOne, i + 8);
            expected[i + 4] = null;
        }
        assertEquals(22, queue.size());
        assertArrayEquals(expected, backingArray);

        // Continuing to enqueue until capacity;
        for (int i = 34; i < 38; i++) {
            expected[(i - 4) % 26] = i;
            queue.enqueue(i);
        }
        assertEquals(26, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);

        // Resizing array
        expected = new Object[52];
        for (int i = 12; i < 39; i++) {
            expected[i - 12] = i;
        }
        queue.enqueue(38);
        assertEquals(27, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(expected, backingArray);
    }
}

