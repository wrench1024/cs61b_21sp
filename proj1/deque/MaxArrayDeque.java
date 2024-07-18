package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return getT();
    }

    public T max(Comparator<T> c) {
        comparator = c;
        return getT();
    }

    private T getT() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = items[0];
        for (int i = 0; i < size; i++) {
            if (comparator.compare(items[i], maxItem) > 0) {
                maxItem = items[i];
            }
        }
        return maxItem;
    }
}
