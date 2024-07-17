package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements deque<T>{
    private int size;
    private int First;
    private int Last;
    private T[] items;
    private int capacity;
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        capacity = 8;
        First = -1;
        Last = 0;
    }
    private void resizing(int Capacity) {
        T[] newItems = (T[]) new Object[Capacity];
        if (First >= Last) {
            System.arraycopy(items, First+1, newItems, 0, size - Last);
            System.arraycopy(items, 0, newItems, size - Last, Last + 1);
        } else {
            System.arraycopy(items, First+1, newItems, 0, size);
        }
        First = Capacity - 1;
        Last = size;

//        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
        capacity = Capacity;
    }

    private void Resizing() {
        double R = size * 1.0 / capacity;
        while (R < 0.25) {
            capacity /= 2;
            R = size * 1.0 / capacity;
        }
        resizing(capacity);
    }
    public void addCheck() {
        if (size == capacity - 1) {
            resizing(capacity * 2);
        }
    }
    @Override
    public void addFirst(T item) {
        if (First < 0)  First = capacity - 1;
        items[First--] = item;
        size++;
        addCheck();
    }

    @Override
    public void addLast(T item) {
        items[Last++] = item;
        size++;
        addCheck();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = First; i < capacity; i++){
            System.out.println(items[i]);
        }
        for (int i = 0; i <= Last; i++){
            System.out.println(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0)  return null;
        T firstItem;
        if (First < capacity - 1) {
            First++;
        } else {
            First = 0;
        }
        firstItem = get(First);
        size--;
        if (size != 0) Resizing();
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0)  return null;
        if (Last > 0) {
            Last--;
        } else {
            Last = capacity - 1;
        }
        T lastItem = get(Last);
        size--;
        Resizing();
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
        public ADequeIterator() {
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
