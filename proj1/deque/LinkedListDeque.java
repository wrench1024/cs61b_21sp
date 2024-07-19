package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        Node t = new Node();
        sentinel = new Node(t, t, 0);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new Node(sentinel, sentinel.next, item);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, sentinel, item);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            System.out.println(i.item);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastItem = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return lastItem;
    }

    @Override
    public T get(int index) {
        Node i = sentinel;
        while (index-- > -1) {
            i = i.next;
        }
        return (T) i.item;
    }

    public Iterator<T> iterator() {
        return new LDequeIterator();
    }

    private class LDequeIterator implements Iterator<T> {
        private int pos;
        LDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            return get(pos++);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (((Deque<?>) o).size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            T thisItem = this.get(i);
            Object otherItem = ((Deque<?>) o).get(i);
            if (thisItem == null) {
                return otherItem == null;
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

    private T getRecursiveHelper(int index, Node t) {
        if (index == 0) {
            return (T) t.next.item;
        }
        t = t.next;
        return getRecursiveHelper(index - 1, t);
    }

    public T getRecursive(int index) {
//        if (index == 0) {
//            return (T) sentinel.next.item;
//        }
//        sentinel = sentinel.next;
//        return getRecursive(index - 1);
        return getRecursiveHelper(index, sentinel);
    }

}
