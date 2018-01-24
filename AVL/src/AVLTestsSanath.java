import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * AVL tests.
 * @author Sanath Nagaraj
 * @version 1.0
 */
public class AVLTestsSanath {

    private static final int TIMEOUT = 200;
    private AVLInterface<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    //---------- Constructor ----------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentException() {
        avlTree = new AVL<>(null);
    }

    //---------- Add ----------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentException() {
        avlTree.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        // left rotate
        avlTree = new AVL<>();
        Integer[] array0 = {};
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(array0));
        assertEquals(0, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        Integer[] array1 = {70};
        expected = new LinkedList<>(Arrays.asList(array1));
        assertEquals(1, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(90);
        Integer[] array2 = {70, 90};
        expected = new LinkedList<>(Arrays.asList(array2));
        assertEquals(2, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(110);
        Integer[] array3 = {90, 70, 110};
        expected = new LinkedList<>(Arrays.asList(array3));
        assertEquals(3, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // right-left rotate

        avlTree.clear();
        Integer[] array4 = {};
        expected = new LinkedList<>(Arrays.asList(array4));
        assertEquals(0, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        Integer[] array5 = {70};
        expected = new LinkedList<>(Arrays.asList(array5));
        assertEquals(1, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(110);
        Integer[] array6 = {70, 110};
        expected = new LinkedList<>(Arrays.asList(array6));
        assertEquals(2, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(90);
        Integer[] array7 = {90, 70, 110};
        expected = new LinkedList<>(Arrays.asList(array7));
        assertEquals(3, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // right rotate

        avlTree.clear();
        Integer[] array8 = {};
        expected = new LinkedList<>(Arrays.asList(array8));
        assertEquals(0, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        Integer[] array9 = {70};
        expected = new LinkedList<>(Arrays.asList(array9));
        assertEquals(1, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(60);
        Integer[] array10 = {70, 60};
        expected = new LinkedList<>(Arrays.asList(array10));
        assertEquals(2, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(50);
        Integer[] array11 = {60, 50, 70};
        expected = new LinkedList<>(Arrays.asList(array11));
        assertEquals(3, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // left-right rotate

        avlTree.clear();
        Integer[] array12 = {};
        expected = new LinkedList<>(Arrays.asList(array12));
        assertEquals(0, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        Integer[] array13 = {70};
        expected = new LinkedList<>(Arrays.asList(array13));
        assertEquals(1, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(50);
        Integer[] array14 = {70, 50};
        expected = new LinkedList<>(Arrays.asList(array14));
        assertEquals(2, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(60);
        Integer[] array15 = {60, 50, 70};
        expected = new LinkedList<>(Arrays.asList(array15));
        assertEquals(3, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        Integer[] base = {30, 8, 40, 5, 10, 35, 70, 9};

        // Left rotate

        avlTree = new AVL<>(Arrays.asList(base));
        expected = new LinkedList<>(Arrays.asList(base));
        assertEquals(8, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(90);
        Integer[] array16 = {30, 8, 40, 5, 10, 35, 70, 9, 90};
        expected = new LinkedList<>(Arrays.asList(array16));
        assertEquals(9, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(110);
        Integer[] array17 = {30, 8, 40, 5, 10, 35, 90, 9, 70, 110};
        expected = new LinkedList<>(Arrays.asList(array17));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Right-Left rotate

        avlTree = new AVL<>(Arrays.asList(base));
        expected = new LinkedList<>(Arrays.asList(base));
        assertEquals(8, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(110);
        Integer[] array18 = {30, 8, 40, 5, 10, 35, 70, 9, 110};
        expected = new LinkedList<>(Arrays.asList(array18));
        assertEquals(9, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(90);
        Integer[] array19 = {30, 8, 40, 5, 10, 35, 90, 9, 70, 110};
        expected = new LinkedList<>(Arrays.asList(array19));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Right rotate

        avlTree = new AVL<>(Arrays.asList(base));
        expected = new LinkedList<>(Arrays.asList(base));
        assertEquals(8, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(60);
        Integer[] array20 = {30, 8, 40, 5, 10, 35, 70, 9, 60};
        expected = new LinkedList<>(Arrays.asList(array20));
        assertEquals(9, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(50);
        Integer[] array21 = {30, 8, 40, 5, 10, 35, 60, 9, 50, 70};
        expected = new LinkedList<>(Arrays.asList(array21));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Left-right rotate

        avlTree = new AVL<>(Arrays.asList(base));
        expected = new LinkedList<>(Arrays.asList(base));
        assertEquals(8, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(50);
        Integer[] array22 = {30, 8, 40, 5, 10, 35, 70, 9, 50};
        expected = new LinkedList<>(Arrays.asList(array22));
        assertEquals(9, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(60);
        Integer[] array23 = {30, 8, 40, 5, 10, 35, 60, 9, 50, 70};
        expected = new LinkedList<>(Arrays.asList(array23));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Testing adding duplicates

        avlTree.add(60);
        expected = new LinkedList<>(Arrays.asList(array23));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(5);
        expected = new LinkedList<>(Arrays.asList(array23));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(9);
        expected = new LinkedList<>(Arrays.asList(array23));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        Integer[] base2 = {300, 50, 400, 30, 80, 350, 600, 60, 90, 500, 700};

        avlTree = new AVL<>(Arrays.asList(base2));
        expected = new LinkedList<>(Arrays.asList(base2));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // right left rotation

        avlTree.add(70);
        Integer[] array24 = {300, 60, 400, 50, 80, 350, 600, 30, 70, 90, 500, 700};
        expected = new LinkedList<>(Arrays.asList(array24));
        assertEquals(12, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Left rotate
        avlTree.add(690);
        Integer[] array25 = {300, 60, 600, 50, 80, 400, 700, 30, 70, 90, 350, 500, 690};
        expected = new LinkedList<>(Arrays.asList(array25));
        assertEquals(13, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // right rotate
        avlTree.add(55);
        avlTree.add(20);
        avlTree.add(35);
        avlTree.add(52);
        avlTree.add(56);
        avlTree.add(18);
        Integer[] array26 = {300, 50, 600, 30, 60, 400, 700, 20, 35, 55, 80, 350,
                500, 690, 18, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array26));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // left right
        avlTree.remove(690);
        avlTree.add(450);
        Integer[] array27 = {300, 50, 500, 30, 60, 400, 600, 20, 35, 55, 80, 350,
                450, 700, 18, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array27));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());
    }

    //---------- Remove ----------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgumentException() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        avlTree = new AVL<>(Arrays.asList(arr));
        avlTree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElementException() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        avlTree = new AVL<>(Arrays.asList(arr));
        avlTree.remove(9);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer[] base = {300, 50, 500, 30, 60, 400, 600, 20, 35, 55, 80, 350,
                450, 700, 18, 52, 56, 70, 90};
        avlTree = new AVL<>(Arrays.asList(base));
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(base));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Right rotate
        assertEquals(new Integer(35), avlTree.remove(35));
        Integer[] array1 = {300, 50, 500, 20, 60, 400, 600, 18, 30, 55, 80, 350,
                450, 700, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array1));
        assertEquals(18, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // no rotate
        assertEquals(new Integer(20), avlTree.remove(20));
        Integer[] array2 = {300, 50, 500, 18, 60, 400, 600, 30, 55, 80, 350,
                450, 700, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array2));
        assertEquals(17, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // left rotate
        assertEquals(new Integer(18), avlTree.remove(18));
        Integer[] array3 = {300, 60, 500, 50, 80, 400, 600, 30, 55, 70, 90, 350,
                450, 700, 52, 56};
        expected = new LinkedList<>(Arrays.asList(array3));
        assertEquals(16, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // no rotation
        assertEquals(new Integer(90), avlTree.remove(90));
        Integer[] array4 = {300, 60, 500, 50, 80, 400, 600, 30, 55, 70, 350,
                450, 700, 52, 56};
        expected = new LinkedList<>(Arrays.asList(array4));
        assertEquals(15, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // left-right rotation
        assertEquals(new Integer(80), avlTree.remove(80));
        Integer[] array5 = {300, 55, 500, 50, 60, 400, 600, 30, 52, 56, 70, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array5));
        assertEquals(14, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // no rotation
        assertEquals(new Integer(50), avlTree.remove(50));
        Integer[] array6 = {300, 55, 500, 30, 60, 400, 600, 52, 56, 70, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array6));
        assertEquals(13, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // no rotation
        assertEquals(new Integer(70), avlTree.remove(70));
        Integer[] array7 = {300, 55, 500, 30, 60, 400, 600, 52, 56, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array7));
        assertEquals(12, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // no rotation
        assertEquals(new Integer(30), avlTree.remove(30));
        Integer[] array8 = {300, 55, 500, 52, 60, 400, 600, 56, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array8));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // right - left
        assertEquals(new Integer(52), avlTree.remove(52));
        Integer[] array9 = {300, 56, 500, 55, 60, 400, 600, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array9));
        assertEquals(10, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Initializing
        Integer[] base2 = {300, 56, 500, 53, 60, 400, 600, 50, 55, 65, 350,
                450, 700, 45, 54};
        avlTree = new AVL<>(Arrays.asList(base2));
        expected = new LinkedList<>(Arrays.asList(base2));
        assertEquals(15, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Two children case
        // No rotation
        assertEquals(new Integer(56), avlTree.remove(56));
        Integer[] array10 = {300, 55, 500, 53, 60, 400, 600, 50, 54, 65, 350,
                450, 700, 45};
        expected = new LinkedList<>(Arrays.asList(array10));
        assertEquals(14, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Rotated once
        assertEquals(new Integer(55), avlTree.remove(55));
        Integer[] array11 = {300, 54, 500, 50, 60, 400, 600, 45, 53, 65, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array11));
        assertEquals(13, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        assertEquals(new Integer(300), avlTree.remove(300));
        Integer[] array12 = {65, 54, 500, 50, 60, 400, 600, 45, 53, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array12));
        assertEquals(12, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        assertEquals(new Integer(65), avlTree.remove(65));
        Integer[] array13 = {60, 50, 500, 45, 54, 400, 600, 53, 350,
                450, 700};
        expected = new LinkedList<>(Arrays.asList(array13));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Initializing
        Integer[] base3 = {300, 56, 500, 53, 60, 400, 600, 54, 350,
                450, 700, 469};
        avlTree = new AVL<>(Arrays.asList(base3));
        expected = new LinkedList<>(Arrays.asList(base3));
        assertEquals(12, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        // Rotated once in find predecessor, once in regular remove method
        // Tests both left-right and right-left rotations
        assertEquals(new Integer(300), avlTree.remove(300));
        Integer[] array14 = {400, 60, 500, 54, 350, 450, 600, 53, 56, 469, 700};
        avlTree = new AVL<>(Arrays.asList(array14));
        expected = new LinkedList<>(Arrays.asList(array14));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());
    }

    //---------- Get ----------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetIllegalArgumentException() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        avlTree = new AVL<>(Arrays.asList(arr));
        avlTree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNoSuchElementException() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        avlTree = new AVL<>(Arrays.asList(arr));
        avlTree.get(9);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGet() {
        Integer[] base2 = {300, 50, 400, 30, 80, 350, 600, 60, 90, 500, 700};

        avlTree = new AVL<>(Arrays.asList(base2));
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(base2));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        avlTree.add(690);
        avlTree.add(55);
        avlTree.add(20);
        avlTree.add(35);
        avlTree.add(52);
        avlTree.add(56);
        avlTree.add(18);
        avlTree.remove(690);
        avlTree.add(450);

        Integer[] array27 = {300, 50, 500, 30, 60, 400, 600, 20, 35, 55, 80, 350,
                450, 700, 18, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array27));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        assertEquals(new Integer(300), avlTree.get(300));
        assertEquals(new Integer(50), avlTree.get(50));
        assertEquals(new Integer(500), avlTree.get(500));
        assertEquals(new Integer(30), avlTree.get(30));
        assertEquals(new Integer(60), avlTree.get(60));
        assertEquals(new Integer(400), avlTree.get(400));
        assertEquals(new Integer(600), avlTree.get(600));
        assertEquals(new Integer(20), avlTree.get(20));
        assertEquals(new Integer(35), avlTree.get(35));
        assertEquals(new Integer(55), avlTree.get(55));
        assertEquals(new Integer(80), avlTree.get(80));
        assertEquals(new Integer(350), avlTree.get(350));
        assertEquals(new Integer(450), avlTree.get(450));
        assertEquals(new Integer(700), avlTree.get(700));
        assertEquals(new Integer(18), avlTree.get(18));
        assertEquals(new Integer(52), avlTree.get(52));
        assertEquals(new Integer(56), avlTree.get(56));
        assertEquals(new Integer(70), avlTree.get(70));
        assertEquals(new Integer(90), avlTree.get(90));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());
        assertEquals(new Integer(690), avlTree.get(690));
    }

    //---------- Contains ----------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsIllegalArgumentException() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        avlTree = new AVL<>(Arrays.asList(arr));
        avlTree.remove(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        Integer[] base2 = {300, 50, 400, 30, 80, 350, 600, 60, 90, 500, 700};

        avlTree = new AVL<>(Arrays.asList(base2));

        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(base2));
        assertEquals(11, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        avlTree.add(70);
        avlTree.add(690);
        avlTree.add(55);
        avlTree.add(20);
        avlTree.add(35);
        avlTree.add(52);
        avlTree.add(56);
        avlTree.add(18);
        avlTree.remove(690);
        avlTree.add(450);

        Integer[] array27 = {300, 50, 500, 30, 60, 400, 600, 20, 35, 55, 80, 350,
                450, 700, 18, 52, 56, 70, 90};
        expected = new LinkedList<>(Arrays.asList(array27));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());

        assertTrue(avlTree.contains(300));
        assertTrue(avlTree.contains(50));
        assertTrue(avlTree.contains(500));
        assertTrue(avlTree.contains(30));
        assertTrue(avlTree.contains(60));
        assertTrue(avlTree.contains(400));
        assertTrue(avlTree.contains(600));
        assertTrue(avlTree.contains(20));
        assertTrue(avlTree.contains(35));
        assertTrue(avlTree.contains(55));
        assertTrue(avlTree.contains(80));
        assertTrue(avlTree.contains(350));
        assertTrue(avlTree.contains(450));
        assertTrue(avlTree.contains(700));
        assertTrue(avlTree.contains(18));
        assertTrue(avlTree.contains(52));
        assertTrue(avlTree.contains(56));
        assertTrue(avlTree.contains(70));
        assertTrue(avlTree.contains(90));
        assertEquals(19, avlTree.size());
        assertEquals(expected, avlTree.levelorder());
        assertFalse(avlTree.contains(690));
        assertFalse(avlTree.contains(555));
        assertFalse(avlTree.contains(333));
        assertFalse(avlTree.contains(111));
        assertFalse(avlTree.contains(123));
        assertFalse(avlTree.contains(434));
        assertFalse(avlTree.contains(325));
        assertFalse(avlTree.contains(1123));
        assertFalse(avlTree.contains(1));
    }


    //---------- Height ----------

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        Integer[] arr = {1, 2, 3, 4, 5};
        avlTree = new AVL<>(Arrays.asList(arr));
        assertEquals(2, avlTree.height());
        avlTree.remove(4);
        assertEquals(2, avlTree.height());
        avlTree.remove(5);
        assertEquals(1, avlTree.height());
    }

}

