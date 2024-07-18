package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int capacity;
    public ArrayDeque() {
        size = 0;
        capacity = 4;
        items = (T[]) new Object[capacity];
        nextFirst = -1;
        nextLast = 0;
    }
//    public int getCapacity() {
//        return capacity;
//    }
//    public void setCapacity(int newCapacity) {
//        capacity = newCapacity;
//    }
//    public void setItems(T[] newItems) {
//        items = newItems;
//    }
//    public void setItem(T item, int i) {
//        items[i] = item;
//    }
//    public T getItem(int i) {
//        return items[i];
//    }
    private void resizing(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        if (nextFirst == capacity - 1) {
            nextFirst = -1;
        }
        if (nextFirst >= nextLast - 1) {
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
        while (R < 0.25 && size != 0) {
            capacity /= 2;
            R = size * 1.0 / capacity;
        }
        if (tmp != capacity) {
            capacity = tmp;
            resizing(capacity);
        }
    }
    private void addCheck() {
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
        if (nextFirst == capacity - 1) {
            nextFirst = -1;
        }
        if (nextFirst >= nextLast - 1) {
            for (int i = nextFirst + 1; i < capacity; i++) {
                System.out.println(items[i]);
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.println(items[i]);
            }
        } else {
            for (int i = nextFirst + 1; i < size; i++) {
                System.out.println(items[i]);
            }
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
        firstItem = getIndex(nextFirst);
        size--;
        if (size != 0) {
            removeCheck();
        }
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
        T lastItem = getIndex(nextLast);
        size--;
        if (size != 0) {
            removeCheck();
        }
        return lastItem;
    }

    @Override
    public T get(int index) {
        int t = nextFirst;
        if (t + index >= capacity - 1) {
            t -= capacity;
        }
        return items[t + 1 + index];
    }

    private T getIndex(int index) {
        return items[index];
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (((Deque<?>) o).size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != ((Deque<?>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

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
