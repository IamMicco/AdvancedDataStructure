package week4;

/**
 *
 * @author Michael Nwabuobi
 */
public class Main {
    public static void main (String[] arg) {
        
        PriorityQueue q = new PriorityQueue(Integer.class, 5);
        q.add(20);
        q.add(50);
        q.add(10);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
