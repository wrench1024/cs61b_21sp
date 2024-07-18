package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    public int size;
    public int nextFirst;
    public int nextLast;
    public T[] items;
    public int capacity;
    public ArrayDeque() {
        size = 0;
        capacity = 8;
        items = (T[]) new Object[capacity];
        nextFirst = -1;
        nextLast = 0;
    }
    private void resizing(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        if (nextFirst == capacity - 1) {
            nextFirst = -1;
        }
        if (nextFirst >= nextLast) {
            System.arraycopy(items, nextFirst + 1, newItems, 0, size - nextLast);
            System.arraycopy(items, 0, newItems, size - nextLast, nextLast);
        } else {
            System.arraycopy(items, nextFirst + 1, newItems, 0, size);
        }
        nextFirst = newCapacity - 1;
        nextLast = size;

//        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
        capacity = newCapacity;
    }

    private void removeCheck() {
        int tmp = capacity;
        double R = size * 1.0 / capacity;
        while (R < 0.25) {
            capacity /= 2;
            R = size * 1.0 / capacity;
        }
        if (tmp != capacity) {
            resizing(capacity);
        }
    }
    public void addCheck() {
        if (size == capacity) {
            resizing(capacity * 2);
        }
    }
    @Override
    public void addFirst(T item) {
        addCheck();
        if (nextFirst < 0) {
            nextFirst = capacity - 1;
        }
        items[nextFirst--] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        addCheck();
        if (nextLast == capacity) {
            nextLast = 0;
        }
        items[nextLast++] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = nextFirst; i < capacity; i++) {
            System.out.println(items[i]);
        }
        for (int i = 0; i <= nextLast; i++) {
            System.out.println(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem;
        if (nextFirst < capacity - 1) {
            nextFirst++;
        } else {
            nextFirst = 0;
        }
        firstItem = get(nextFirst);
        size--;
        if (size != 0) {
            removeCheck();
        }
        return firstItem;
    }
    public T getFirst() {
        T firstItem;
        int first = nextFirst;
        if (nextFirst < capacity - 1) {
            first++;
        } else {
            first = 0;
        }
        firstItem = get(first);
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast > 0) {
            nextLast--;
        } else {
            nextLast = capacity - 1;
        }
        T lastItem = get(nextLast);
        size--;
        removeCheck();
        return lastItem;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ADequeIterator();
    }

    private class ADequeIterator implements Iterator<T> {
        private int pos;
        ADequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            return items[pos++];
        }
    }
}
