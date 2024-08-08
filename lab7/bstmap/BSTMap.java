package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    private class BSTNode {
        BSTNode left;
        BSTNode right;
        K key;
        V value;

        public BSTNode(K nodeKey, V nodeValue) {
            left = null;
            right = null;
            key = nodeKey;
            value = nodeValue;
        }

        public void add(K key, V value) {
            if (size == 0 || root.findCloseNode(key) == null) {
                root = new BSTNode(key, value);
            } else if(root.findCloseNode(key).key.compareTo(key) > 0) {
                root.findCloseNode(key).left = new BSTNode(key, value);
            } else {
                root.findCloseNode(key).right = new BSTNode(key, value);
            }
            size ++;
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        public BSTNode findNode(K key) {
            if (findCloseNode(key) == null || findCloseNode(key).key == null || !(findCloseNode(key).key.equals(key))) {
                return null;
            } else {
                return findCloseNode(key);
            }
        }

        public BSTNode findCloseNode(K key) {
            if(this.isLeaf()) {
                return this;
            } else if(key.equals(this.key)) {
                return this;
            } else if(key.compareTo(this.key) > 0) {
                if (this.right == null) {
                    return this;
                } else {
                    return this.right.findNode(key);
                }
            } else {
                if (this.left == null) {
                    return this;
                } else {
                    return this.left.findNode(key);
                }
            }
        }

        public V get(K key){
            if (root.findNode(key) != null) {
                return root.findNode(key).value;
            } else return null;
        }
    }

    int h, size = 0;
    BSTNode root = new BSTNode(null, null);

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        size = 0;
        root = new BSTNode(null, null);
    }

    @Override
    public boolean containsKey(K key) {
        return root == null || root.findNode(key) != null;
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return root.get(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            root.findNode(key).value = value;
        } else {
            root.add(key, value);
        }
    }

//not need
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
