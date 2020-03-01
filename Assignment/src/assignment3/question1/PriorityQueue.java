package assignment3.question1;

import java.lang.reflect.Array;

/**
 *
 * @author Michael Nwabuobi
 */

public class PriorityQueue<T extends Comparable<T>> {
    
    private T[] pqArray;
    private int capacity, qSize, index;
    private Class<T> cl;
    
    public PriorityQueue (Class<T> cl, int capacity) {
        
        this.cl = cl;
        this.capacity = capacity;
        pqArray = (T[])Array.newInstance(cl, capacity);
        qSize = 0;
        index = 0;
    }
    
    public boolean isEmpty () {
        
        return qSize == 0;
    }
    
    public int getSize () {
        
        return qSize; 
    }
    
    public T peek () {
        
        if (qSize == 0) {
            
            System.out.println("Queue is Empty");
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < qSize; i++) {
            
            if (pqArray[i].compareTo(pqArray[maxIndex]) > 0) {
                
                maxIndex = i;
            }
        }
        T result = pqArray[maxIndex];
        return result;
    }
    
    public void clear () {
        
        pqArray = (T[])Array.newInstance(cl, capacity);
        qSize = 0;
    }
    
    public boolean isFull () {
        
        return qSize == capacity;
    }
    
    public void add (T data) {
        
        if (qSize == capacity) System.out.println("Queuse is full");
        pqArray[index++] = data;
        qSize++;
        System.out.println(String.format("Added element at index %d", index));
    }
    
    public T remove () {
        
        if (qSize == 0) {
            
            System.out.println("Queue is Empty");
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < qSize; i++) {
            
            if (pqArray[i].compareTo(pqArray[maxIndex]) > 0) {
                
                maxIndex = i;
            }
        }
        T result = pqArray[maxIndex];
        index--;
        pqArray[maxIndex] = pqArray[index];
        qSize--;
        return result;
    }
}