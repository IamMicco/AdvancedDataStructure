package assignment3.question2;

import java.util.Random;

/**
 * COSC 3351 Advanced Data Structures Spring'20
 * This program tests the PriorityQueue implementations.
 * YOU SHOULD ADD YOUR TESTS TO THIS FILE.
 */

public class HeapTest {
    public static void main(String[] args) {
        testSmartBinaryHeap(true);
        System.out.println();
        
        testSmartBinaryHeap(false);
        System.out.println();
    }   
    
    /**
     * A very minimal test for the SmartBinaryHeap
     */
    public static void testSmartBinaryHeap(boolean isMin) {
        SmartBinaryHeap<Integer> minInts = new SmartBinaryHeap<Integer>(Integer.class, 7, isMin);
        
        minInts.add(13);
        minInts.add(20);
        minInts.add(11);
        minInts.add(44);
        minInts.add(3);
        minInts.add(7);
        minInts.add(9);
  
        System.out.println(minInts);
        System.out.println(minInts.peek());
        
        while (!minInts.isEmpty()) {
            System.out.println(minInts.remove());               
            System.out.println(minInts);
        }
        
        System.out.println();
        System.out.println();

        SmartBinaryHeap<String> minStrs = new SmartBinaryHeap<String>(String.class, 4, isMin);
        minStrs.add("Kona");
        minStrs.add("Daisy");        
        minStrs.add("Meghan");
        minStrs.add("Martin");
        
        while (!minStrs.isEmpty()) {
            System.out.println(minStrs.remove());
        }
        
        System.out.println();
    }
  
    
    /**
     * Builds a binary heap of a given size with random integers.  
     * This method could be used to time your binary heap inserts.
     */
    private static BinaryHeap<Integer> buildBinaryHeap(int size) {
    	Random rand = new Random();
    	BinaryHeap<Integer> bh = new BinaryHeap<Integer>(Integer.class, size);
    	
    	for (int i = 0; i < size; i++) {
    		bh.add(rand.nextInt(2 * size) - size / 2);
    	}
    	
    	return bh;
    }    
    
    /**
     * Builds a four heap of a given size with random integers.  
     * This method could be used to time your four heap inserts.
     */    
    private static FourHeap<Integer> buildFourHeap(int size) {
    	Random rand = new Random();
    	FourHeap<Integer> fh = new FourHeap<Integer>(Integer.class, size);
    	
    	for (int i = 0; i < size; i++) {
    		fh.add(rand.nextInt(2 * size) - size / 2);
    	}
    	
    	return fh;
    }
}