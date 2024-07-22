package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 40; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 20; i++) {
//            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
            ad1.removeFirst();
        }

        for (double i = 39; i > 20; i--) {
//            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
            ad1.removeLast();
        }


    }

    @Test
    public void fillUpEmptyFillUp() {
//        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
//        ad.addFirst(0);
//
//        ad.removeLast();      //==> 0
//
//        ad.isEmpty();
//        ad.isEmpty();
//        ad.isEmpty();
//        ad.addFirst(5);
//        ad.removeLast()   ;   //==> 5
//        ad.addFirst(7);
//        ad.addFirst(8);
//        ad.removeLast()   ;   //==> 7
//        System.out.println(ad.removeLast());

        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
//        a.addFirst(0);
//        a.removeFirst();
//        a.addFirst(2);
//        a.addFirst(3);
//        a.addLast(4);
//        a.addLast(5);
//        a.removeFirst();
//        a.addFirst(7);
//        a.removeLast();
//        a.removeFirst();
//        a.removeFirst();
//        a.addFirst(11);

        a.addFirst(0);
        a.removeLast();
        a.addLast(2);
        a.removeLast();
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.addLast(10);
        a.removeLast();
        a.addLast(12);
        a.removeFirst();
        a.addFirst(14);
        a.addFirst(15);
        a.addFirst(16);
        a.addFirst(17);
        a.removeLast();
        System.out.println(a.get(1));
    }
    @Test
    public void equalsTest() {
        LinkedListDeque lld = new LinkedListDeque();
        ArrayDeque ad = new ArrayDeque();
        for (int i = 0; i < 8; i++) {
            lld.addLast(i);
        }
        for (int i = 7; i >= 0; i--) {
            ad.addFirst(i);
        }
        lld.printDeque();
        System.out.println(8888);
        System.out.println(ad.size());
        ad.printDeque();
        System.out.println(7777);
        System.out.println(ad.equals(lld));
    }
}