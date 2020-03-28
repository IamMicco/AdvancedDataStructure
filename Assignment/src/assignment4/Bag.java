package assignment4;

/** ****************************************************************************
 *  A generic bag or multiset, implemented using a singly linked list.
 *
 ***************************************************************************** */
import java.util.Iterator;

/**
 * The Bag class represents a bag (or multiset) of generic items. It supports
 * insertion and iterating over the items in arbitrary order.
 */
public class Bag<T> implements Iterable<T> {

    private Node<T> first;    // beginning of bag
    private int n;            // number of elements in bag

    // helper linked list Node class
    private static class Node<T> {

        private T item;
        private Node<T> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    public Bag (Bag bag) {

        this.first = bag.first();
        this.n = bag.n();
    }

    public Node<T> first () {

        return this.first;
    }

    public int n () {

        return this.n;
    }

    // Returns true if this bag is empty.
    public boolean isEmpty() {
        return first == null;
    }

    // Returns the number of items in this bag.
    public int size() {
        return n;
    }

    // Adds the item to this bag.
    public void add(T item) {
        Node<T> oldfirst = first;
        first = new Node<T>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    //Returns an iterator that iterates over the items in this bag in arbitrary order.
    public Iterator<T> iterator() {
        return new LinkedIterator(first);
    }

    // An Iterator helper class 
    private class LinkedIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) return null;
            T item = current.item;
            current = current.next;               
            return item;            
        }
    }

    
    //Test Bag class 
    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        bag.add("Hello World");
        bag.add("Hi there");
        System.out.println("size of bag = " + bag.size());
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
