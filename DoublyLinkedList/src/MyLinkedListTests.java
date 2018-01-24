import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class MyLinkedListTests {
    private LinkedListInterface<String> list;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }

    @Test
    public void testAddAtIndexNotEmpty() {
        list.addAtIndex(0, "element 1");
        list.addAtIndex(1, "element 2");
        list.addAtIndex(2, "element 3");
        list.addAtIndex(3, "element 4");
        list.removeAtIndex(2);

        assertEquals(3, list.size());
        assertEquals("element 1", list.get(0));
        assertEquals("element 2", list.get(1));
        assertEquals("element 4", list.get(2));
    }

    @Test
    public void testAddToFrontEmpty() {
        list.addToFront("element 1");
        assertEquals(1, list.size());
        assertEquals("element 1", list.getHead().getData()); //head points
        // to added element
        assertNull(list.getHead().getPrevious()); //head has no previous
        // pointer
        assertEquals("element 1", list.getTail().getData()); //tail points
        // to added element
        assertNull(list.getTail().getNext()); //tail has no next pointer
    }

    @Test
    public void testAddToFrontNotEmpty() {
        list.addToFront("element 1");
        list.addToFront("element 2");
        list.addToFront("element 3");
        list.addToFront("element 4");

        assertEquals(4, list.size());
        assertEquals("element 4", list.getHead().getData()); //correct head
        assertEquals("element 1", list.getTail().getData()); //correct tail

        assertNull(list.getTail().getNext()); //tail has no next pointer
        assertEquals("element 2", list.getTail().getPrevious().getData());
        //tail has correct previous pointer

        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 3", list.getHead().getNext().getData());
        //head has correct next pointer
    }

    @Test
    public void testAddToBackEmpty() {
        list.addToBack("element 1");
        assertEquals(1, list.size());
        assertEquals("element 1", list.getHead().getData()); //head points
        // to added element
        assertNull(list.getHead().getPrevious()); //head has no previous
        // pointer
        assertEquals("element 1", list.getTail().getData()); //tail points
        // to added element
        assertNull(list.getTail().getNext()); //tail has no next pointer
    }

    @Test
    public void testAddToBackNotEmpty() {
        list.addToBack("element 1");
        list.addToBack("element 2");
        list.addToBack("element 3");
        list.addToBack("element 4");

        assertEquals(4, list.size());
        assertEquals("element 1", list.getHead().getData()); //correct head
        assertEquals("element 4", list.getTail().getData()); //correct tail

        assertNull(list.getTail().getNext()); //tail has no next pointer
        assertEquals("element 3", list.getTail().getPrevious().getData());
        //tail has correct previous pointer

        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 2", list.getHead().getNext().getData());
        //head has correct next pointer
    }
    @Test
    public void testRemoveAtIndexNotEmpty() {
        list.addToBack("element 1");
        list.addToBack("element 2");
        list.addToBack("element 3");
        list.addToBack("element 4");

        list.removeAtIndex(0); // element 2, 3, 4
        assertEquals(3, list.size());
        assertEquals("element 2", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 3", list.getHead().getNext().getData()); //

        list.removeAtIndex(1); // element 2, 4
        assertEquals(2, list.size());
        assertEquals("element 2", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 4", list.getTail().getData()); //correct tail
        assertNull(list.getTail().getNext()); //tail has no next pointer

        assertEquals("element 4", list.getHead().getNext().getData());
        assertEquals("element 2", list.getTail().getPrevious().getData());
    }

    @Test
    public void testRemoveFromFrontEmpty() {
        assertNull(list.removeFromFront());
    }

    @Test
    public void testRemoveFromFrontNotEmpty() {
        list.addToFront("element 1");
        list.addToFront("element 2");
        list.addToFront("element 3");
        list.addToFront("element 4");

        String removedNodeData1 = list.removeFromFront();
        assertEquals(3, list.size());
        assertEquals("element 4", removedNodeData1); //removed node
        assertEquals("element 3", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 1", list.getTail().getData()); //correct tail
        assertNull(list.getTail().getNext()); // tail has no next pointer

        String removedNodeData2 = list.removeFromFront();
        assertEquals(2, list.size());
        assertEquals("element 3", removedNodeData2);
        assertEquals("element 2", list.getHead().getData());
        assertNull(list.getHead().getPrevious());
        assertEquals("element 1", list.getTail().getData());
        assertNull(list.getTail().getNext());

        String removedNodeData3 = list.removeFromFront();
        assertEquals(1, list.size());
        assertEquals("element 2", removedNodeData3);
        assertEquals("element 1", list.getHead().getData());
        assertNull(list.getHead().getPrevious());
        assertEquals("element 1", list.getTail().getData());
        assertNull(list.getTail().getNext());

        String removedNodeData4 = list.removeFromFront();
        assertEquals(0, list.size());
        assertEquals("element 1", removedNodeData4);
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    public void testRemoveFromBackEmpty() {
        assertNull(list.removeFromBack());
    }

    @Test
    public void testRemoveFromBackNotEmpty() {
        list.addToFront("element 1");
        list.addToFront("element 2");
        list.addToFront("element 3");
        list.addToFront("element 4");
        // 4,3,2,1

        String removedNodeData1 = list.removeFromBack(); // 4,3,2
        assertEquals(3, list.size());
        assertEquals("element 1", removedNodeData1); //removed node
        assertEquals("element 4", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 2", list.getTail().getData()); //correct tail
        assertNull(list.getTail().getNext()); // tail has no next pointer

        String removedNodeData2 = list.removeFromBack();
        assertEquals(2, list.size());
        assertEquals("element 2", removedNodeData2); //removed node
        assertEquals("element 4", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 3", list.getTail().getData()); //correct tail
        assertNull(list.getTail().getNext()); // tail has no next pointer

        String removedNodeData3 = list.removeFromBack();
        assertEquals(1, list.size());
        assertEquals("element 3", removedNodeData3); //removed node
        assertEquals("element 4", list.getHead().getData()); //correct head
        assertNull(list.getHead().getPrevious()); //head has no previous pointer
        assertEquals("element 4", list.getTail().getData()); //correct tail
        assertNull(list.getTail().getNext()); // tail has no next pointer

        String removedNodeData4 = list.removeFromBack();
        assertEquals(0, list.size());
        assertEquals("element 4", removedNodeData4); //removed node
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    public void testRemoveFirstOccurrence() {
        list.addAtIndex(0, "element 1");
        list.addAtIndex(1, "element 2");
        list.addAtIndex(2, "element 3");
        list.addAtIndex(3, "element 2");

        assertTrue(list.removeFirstOccurrence("element 2"));
        assertEquals(3, list.size());
        assertFalse(list.removeFirstOccurrence("element 4"));
        assertEquals(3, list.size());

        assertEquals("element 1", list.get(0));
        assertEquals("element 3", list.get(1));
        assertEquals("element 2", list.get(2));
    }

    @Test
    public void testGet() {
        list.addToBack("element 1");
        list.addToBack("element 2");
        list.addToBack("element 3");
        list.addToBack("element 4");

        assertEquals("element 1", list.get(0));
        assertEquals("element 2", list.get(1));
        assertEquals("element 3", list.get(2));
        assertEquals("element 4", list.get(3));
    }

    @Test
    public void testToArray() {
        Object[] returnedArr = list.toArray(); //empty array
        list.addToBack("element 1");
        list.addToBack("element 2");
        list.addToBack("element 3");
        list.addToBack("element 4");

        returnedArr = list.toArray();
        assertEquals("element 1", returnedArr[0]);
        assertEquals("element 2", returnedArr[1]);
        assertEquals("element 3", returnedArr[2]);
        assertEquals("element 4", returnedArr[3]);
    }

    @Test
    public void testClear() {
        list.addToFront("element 1");
        list.addToFront("element 2");
        list.addToFront("element 3");
        list.addToFront("element 4");

        list.clear();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.size());
    }
}
