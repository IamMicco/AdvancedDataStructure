package assignment3.question2;

public class SmartBinaryHeap <T extends Comparable<T>> extends BinaryHeap <T> {

    private boolean isMinHeap;

    public SmartBinaryHeap(Class<T> cl, int capacity) {

        super(cl, capacity);

    }

    public SmartBinaryHeap(Class<T> cl, int capacity, boolean isMinHeap) {

        super (cl, capacity);
        this.isMinHeap = isMinHeap;
    }

    @Override
    protected void swim (int index) {

        if (this.isMinHeap) super.swim(index);
        else {

            if (heap[index].compareTo(heap[parent(index)]) > 0) {

                swap(index, parent(index));
                swim(parent(index));
            }
        }
    }

    @Override
    protected void sink (int index) {

        if (this.isMinHeap) super.sink(index);
        else {

            if (isLeaf(index)) return;
            if (heap[index].compareTo(heap[leftChild(index)]) < 0 || heap[index].compareTo(heap[rightChild(index)]) < 0) {

                if (heap[index].compareTo(heap[leftChild(index)]) < 0) {

                    swap(index, leftChild(index));
                    sink(leftChild(index));
                } else {

                    swap(index, rightChild(index));
                    sink(rightChild(index));
                }
            }
        }
    }
}