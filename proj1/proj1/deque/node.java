package deque;
public class node<T> {
    node prev;
    node next;
    T item;

    public node(node a, node b, Object c) {
        prev = a;
        next = b;
        item = (T) c;
    }

    public node() {
        prev = null;
        next = null;
        item = null;
    }
}
