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
}
