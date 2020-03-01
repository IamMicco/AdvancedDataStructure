package assignment3.question2;

import java.lang.reflect.Array;

public class BinaryHeap <T extends Comparable<T>> {

    public T[] heap;        // Remember to change to private
    private Class<T> cl;
    private final int capacity;
    private int size;

    public BinaryHeap (Class<T> cl, int capacity) {

        size = 0;
        this.cl = cl;
        this.capacity = capacity;
        heap = (T[]) Array.newInstance(cl, capacity);
    }

    protected int parent (int index) {

        if (index == 0) return 0;
        return (index - 1) / 2;
    }

    protected int leftChild (int index) {
        
        return (2 * index) + 1;
    }

    protected int rightChild (int index) {

        return (2 * index) + 2;
    }


    public void add (T value) {

        heap[size++] = value;
        swim(size - 1);
    }

    protected boolean isLeaf (int index) {

        if (index > (size / 2) && index <= size) return true;
        return false;
    }
    
    protected void swap (int a, int b) {

        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    protected void sink (int index) {

        if (isLeaf(index)) return;
        if (heap[index].compareTo(heap[leftChild(index)]) > 0 || heap[index].compareTo(heap[rightChild(index)]) > 0) {

            if (heap[index].compareTo(heap[leftChild(index)]) > 0) {

                swap(index, leftChild(index));
                sink(leftChild(index));
            } else {

                swap(index, rightChild(index));
                sink(rightChild(index));
            }
        }
    }

    protected void swim (int index) {

        if (heap[index].compareTo(heap[parent(index)]) < 0) {

            swap(index, parent(index));
            swim(parent(index));
        }
    }

    protected T peek () {

        return heap[size - 1];
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

    // public static void main (String[] args) {

    //     BinaryHeap<String> s = new BinaryHeap<>(String.class, 10);
    //     s.add("Hello");
    //     s.add("bye");
    //     s.add("yello");
    //     s.add("blahblahblah");
    //     s.add("jingle");

    //     for (int i = 0; i < s.getCapacity(); i++) {

    //         System.out.println(s.heap[i]);
    //     }
    // }
}