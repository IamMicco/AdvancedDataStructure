package assignment3.question2;

import java.lang.reflect.Array;

public class FourHeap <T extends Comparable<T>> {

    protected T[] heap;
    private Class<T> cl;
    private final int capacity;
    private int size;

    public FourHeap (Class<T> cl, int capacity) {

        size = 0;
        this.cl = cl;
        this.capacity = capacity;
        heap = (T[]) Array.newInstance(cl, capacity);
    }

    protected int parent (int index) {

        if (index == 0) return 0;
        return (index - 1) / 4;
    }

    protected int leftChild (int index) {

        return (4 * index) + 1;
    }

    protected int middleLeftChild (int index) {

        return (4 * index) + 2;
    }

    protected int middleRightChild (int index) {

        return (4 * index) + 3;
    }

    protected int rightChild (int index) {

        return 4 * ++index;
    }

    public void add (T value) {

        heap[size++] = value;
        swim(size - 1);
    }

    protected boolean isLeaf (int index) {

        if (index > ((size - 2) / 4) && index <= size) return true;
        return false;
    }

    protected void swap (int a, int b) {

        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    protected void sink (int index) {

        if (isLeaf(index)) return;
        if (heap[index].compareTo(heap[leftChild(index)]) > 0 || heap[index].compareTo(heap[middleLeftChild(index)]) > 0 || 
        heap[index].compareTo(heap[middleRightChild(index)]) > 0 || heap[index].compareTo(heap[rightChild(index)]) > 0) {

            if (heap[index].compareTo(heap[leftChild(index)]) > 0) {

                swap(index, leftChild(index));
                sink(leftChild(index));
            } else if (heap[index].compareTo(heap[middleLeftChild(index)]) > 0) {

                swap(index, middleLeftChild(index));
                sink(middleLeftChild(index));
            } else if (heap[index].compareTo(heap[middleRightChild(index)]) > 0) {

                swap(index, middleRightChild(index));
                sink(middleRightChild(index));
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

        return "FourHeap explanation";
    }
}