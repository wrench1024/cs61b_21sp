package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements deque<T>{
    private int size;
    private int pSize;
    private int mSize;
    private int capacity;
    private int mid = capacity / 2;
    private T[] items;
    public ArrayDeque() {
        size = 0;
        pSize = 0;
        mSize = -1;
        capacity = 8;
        mid = 4;
        items = (T[]) new Object[capacity];
    }
    private void resizing(int Capacity) {
        T[] a = (T[]) new Object[Capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        capacity = Capacity;
        mid = capacity / 2;
    }
    private void Resizing() {
        double R = size * 1.0 / capacity;
        while (R < 0.25) {
            capacity /= 2;
            R = size * 1.0 / capacity;
        }
        resizing(capacity);
    }
    @Override
    public void addFirst(T item) {
        items[mid + mSize--] = item;
        size++;
        if(size == capacity - 1){
            resizing(size * 2);
        }
    }

    @Override
    public void addLast(T item) {
        items[mid + pSize++] = item;
        size++;
        if(size == capacity - 1){
            resizing(size * 2);
        }
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
        for (int i = mid + mSize + 1; i < mid + pSize; i ++) {
            System.out.println(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0)  return null;
        T First = items[mid + mSize + 1];
        mSize++;
        size--;
        Resizing();
        return First;
    }

    @Override
    public T removeLast() {
        if (size == 0)  return null;
        T Last = items[mid + pSize - 1];
        pSize--;
        size--;
        Resizing();
        return Last;
    }

    @Override
    public T get(int index) {
        return items[mid + index + mSize + 1];
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
