package assignment4;

import java.util.Comparator;

/**
 *
 * @author marianky
 */

public class DirectedEdge implements Comparator<DirectedEdge> {
    private final int v;
    private final int w;
    private final double weight;

    /**
     * Initializes a directed edge from vertex v to vertex w
     * with the given weight.
     */
    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public DirectedEdge (DirectedEdge edge) {

        this.v = edge.from();
        this.w = edge.to();
        this.weight = edge.weight;
    }

    //Returns the tail vertex of the directed edge.
    public int from() {
        return v;
    }

    //Returns the head vertex of the directed edge.     
    public int to() {
        return w;
    }

    //Returns the weight of the directed edge.
    public double weight() {
        return weight;
    }

    //Returns a string representation of the directed edge.
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
    
    @Override
    public int compare (DirectedEdge edge1, DirectedEdge edge2) {
        
        if (edge1.weight() < edge2.weight()) return -1;
        if (edge1.weight() > edge2.weight()) return 1;
        return 0;
    }

    //Test the edge 
    // public static void main(String[] args) {
    //     DirectedEdge e = new DirectedEdge(12, 34, 5.67);
    //     System.out.println(e);
    // }
}