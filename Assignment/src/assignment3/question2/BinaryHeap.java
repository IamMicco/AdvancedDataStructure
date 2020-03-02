package assignment3.question2;

import java.lang.reflect.Array;

public class BinaryHeap <T extends Comparable<T>> {

    protected T[] heap;
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
        /**
         * This method compares the children with the parents
         * and sinks the parent if it is greater than the child 
         */
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
        heap[--size] = null;
        return value;
    }

    public int getCapacity () {

        return capacity;
    }

    @Override
    public String toString () {

        return "BinaryHeap explanation";
    }
}