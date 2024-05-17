package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements deque<T>{
    private int size;
    private int pSize;
    private int mSize;
    private int capacity;
    private T[] items;
    public ArrayDeque() {
        size = 0;
        pSize = 0;
        mSize = -1;
        capacity = 8;
        items = (T[]) new Object[capacity];
    }
    private void resizing(int Capacity) {
        T[] a = (T[]) new Object[Capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        capacity = Capacity;
    }
    private void Resizing() {
        float R = size / capacity;
        while (R < 0.25) {
            capacity /= 2;
        }
        resizing(capacity);
    }
    @Override
    public void addFirst(T item) {
        items[mSize--] = item;
        size++;
        if(size == capacity){
            resizing(size * 2);
        }
    }

    @Override
    public void addLast(T item) {
        items[pSize++] = item;
        size++;
        if(size == capacity){
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
        for (int i = mSize+1; i < pSize; i ++) {
            System.out.println(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0)  return null;
        T First = items[mSize + 1];
        mSize++;
        size--;
        Resizing();
        return First;
    }

    @Override
    public T removeLast() {
        if (size == 0)  return null;
        T Last = items[pSize - 1];
        pSize--;
        size--;
        Resizing();
        return Last;
    }

    @Override
    public T get(int index) {
        return items[index + mSize + 1];
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
