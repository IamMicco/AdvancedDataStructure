package assignment3.question2;

import java.lang.reflect.Array;

public class BinaryHeap<T> {

    public T[] heap;        // Remember to change to private
    private Class<T> cl;
    private final int capacity;
    private int size;

    public BinaryHeap (Class<T> cl, int capacity) {

        this.cl = cl;
        this.capacity = capacity;
        heap = (T[]) Array.newInstance(cl, capacity);
    }

    public void add (T value) {

        heap[size++] = value;
    }

    protected void swim () {
        
        if ()
    }

    protected void sink () {


    }

    public boolean isEmpty () {

        if (size == 0) return true;
        else return false;
    }

    public T remove () {

        if (isEmpty()) return null;
        T value = heap[size - 1];
        heap[size - 1] = null;
        return value;
    }

    public int getCapacity () {

        return capacity;
    }

    @Override
    public String toString () {

        return "BinaryHeap explanation";
    }

    public static void main (String[] args) {

        BinaryHeap<String> s = new BinaryHeap<>(String.class, 10);
        s.add("Hello");
        s.add("bye");
        s.add("yello");
        s.add("blahblahblah");
        s.add("jingle");

        for (int i = 0; i < s.getCapacity(); i++) {

            System.out.println(s.heap[i]);
        }
    }
}