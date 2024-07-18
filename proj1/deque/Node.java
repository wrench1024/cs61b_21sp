package deque;
public class Node<T> {
    Node prev;
    Node next;
    T item;

    public Node(Node a, Node b, Object c) {
        prev = a;
        next = b;
        item = (T) c;
    }

    public Node() {
        prev = null;
        next = null;
        item = null;
    }
}
