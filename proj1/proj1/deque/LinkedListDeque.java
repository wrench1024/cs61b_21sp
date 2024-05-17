package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements deque<T>{

    private int size;
    private node sentinel;

    public LinkedListDeque() {
        size = 0;
        node t = new node();
        sentinel = new node(t, t, 0);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new node(sentinel, sentinel.next, item);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new node(sentinel.prev, sentinel, item);
        sentinel.prev = sentinel.prev.next;
        size++;
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
        for(node i = sentinel; i.next != sentinel; i = i.next){
            System.out.println(i.item);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0)  return null;
        T First = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return First;
    }

    @Override
    public T removeLast() {
        if (size == 0)  return null;
        T Last = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return Last;
    }

    @Override
    public T get(int index) {
        node i = sentinel;
        while(index-- > 0){
            i = i.next;
        }
        return (T) i.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LDequeIterator();
    }

    private class LDequeIterator implements Iterator<T> {
        private int pos;
        public LDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            return get(pos++);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof deque) return false;
        return o.equals(this);
    }


    public T getRecursive(int index) {
        if (index == 0) return (T) sentinel.item;
        sentinel = sentinel.next;
        return getRecursive(index-1);
    }

}
