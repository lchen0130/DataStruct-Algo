import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Sample JUnit tests for Homework 1.
 * @version 1
 */
public class ArrayListStudentTests {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {

        assertEquals(0, list.size());

//        list.addAtIndex(0, "0a"); //0a
//        list.addAtIndex(1, "1a"); //0a 1a
//        list.addAtIndex(2, "2a"); //0a 1a 2a
//        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
//        list.addAtIndex(4, "4a"); //0a 1a 2a 3a
//        list.addAtIndex(5, "5a"); //0a 1a 2a 3a
//        list.addAtIndex(6, "6a"); //0a 1a 2a 3a
//        list.addAtIndex(7, "7a"); //0a 1a 2a 3a
//        list.addAtIndex(8, "8a"); //0a 1a 2a 3a
//        list.addAtIndex(9, "9a"); //0a 1a 2a 3a

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a
        list.addAtIndex(6, "6a"); //0a 1a 2a 3a 4a 5a 6a
        list.addAtIndex(4, "4b"); //0a 1a 2a 3a 4b 4a 5a 6a
        list.addAtIndex(5, "5b"); //0a 1a 2a 3a 4b 5b 4a 5a 6a
        System.out.println(list.get(4));
        System.out.println(list.get(5));
        System.out.println(list.get(6));
        System.out.println(list.get(7));



        assertEquals(9, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
//        expected[0] = "0a";
//        expected[1] = "1a";
//        expected[2] = "2a";
//        expected[3] = "3a";
//        expected[4] = "4a";
//        expected[5] = "5a";
//        expected[6] = "6a";
//        expected[7] = "7a";
//        expected[8] = "8a";
//        expected[9] = "9a";
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4b";
        expected[5] = "5b";
        expected[6] = "4a";
        expected[7] = "5a";
        expected[8] = "6a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }
}

