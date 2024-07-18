package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void test1() {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((int)o1 - (int)o2);
            }
        };
        MaxArrayDeque<Integer> ma = new MaxArrayDeque<Integer>(comparator);
        for (int i = 0; i < 100; i++) {
            ma.addLast(i);
        }
        System.out.println(ma.max(comparator));
    }

    @Test
    public void failedTests() {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((int)o1 - (int)o2);
            }
        };
        MaxArrayDeque mad = new MaxArrayDeque(comparator);
//        mad.addFirst(0);
//        mad.removeLast();
//        mad.addFirst(2);
//        mad.removeLast();
//        mad.isEmpty();
//        mad.addFirst(5);
//        mad.addFirst(6);
//        System.out.println("1212" + mad.removeLast());
//        System.out.println(mad.removeLast());

        mad.addFirst(0);
        mad.removeFirst();
        mad.addLast(2);
        mad.addLast(3);
        mad.get(1);
        mad.addFirst(5);
        System.out.println(mad.get(2));
    }
}
