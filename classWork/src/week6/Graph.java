package week6;

/**
 *
 * @author Michael Nwabuobi
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ****************************************************************************
 * A graph, implemented using an array of sets. Parallel edges and self-loops
 * allowed.
 *
 * % java Graph tinyG.txt 13 vertices, 13 edges 0: 6 2 1 5 1: 0 2: 0 3: 5 4 4: 5
 * 6 3 5: 3 4 0 6: 0 4 7: 8 8: 7 9: 11 10 12 10: 9 11: 9 12 12: 11 9
 *
 * % java Graph mediumG.txt 250 vertices, 1273 edges 0: 225 222 211 209 204 202
 * 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 1: 220 203 200 194 189 164
 * 150 130 107 72 2: 141 110 108 86 79 51 42 18 14 ...
 *
 *****************************************************************************
 */
/**
 * The class represents an undirected graph of vertices named 0 through V â€“ 1.
 * It supports the following two primary operations: add an edge to the graph,
 * iterate over all of the vertices adjacent to a vertex. It also provides
 * methods for returning the degree of a vertex, the number of vertices V in the
 * graph, and the number of edges E in the graph. Parallel edges and self-loops
 * are permitted. This implementation uses an adjacency-lists representation
 */
public class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;
    private int E;
    private Bag<Integer>[] adj;

    /**
     * Initializes an empty graph with V vertices and 0 edges. param V the
     * number of vertices
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a graph from the specified input stream. The format is the
     * number of vertices V, followed by the number of edges E, followed by E
     * pairs of vertices, with each entry separated by whitespace.
     */
    public Graph(String inFile) {
        try {
            File file = new File(inFile);
            Scanner sc = new Scanner(file);
            this.V = sc.nextInt();
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = sc.nextInt();
            for (int i = 0; i < E; i++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                if (validateVertex(v) && validateVertex(w)) {
                    addEdge(v, w);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }      
    }

    //TODO: Create a Copy Constructor for Graph

    //Returns the number of vertices in this graph.
    public int V() {
        return V;
    }

    //Returns the number of edges in this graph.     
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private boolean validateVertex(int v) {
        if (v < 0 || v >= V) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds the undirected edge v-w to this graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     */
    public void addEdge(int v, int w) {
        if (validateVertex(v) && validateVertex(w)) {
            E++;
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    //Returns the vertices adjacent to vertex v.
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    //Returns the degree of vertex v.
    public int degree(int v) {
        return adj[v].size();
    }

    //Returns a string representation of this graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {

            s.append(v + ": ");
            // Iterator
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //Testing the Graph
    public static void main(String[] args) {
        Graph G = new Graph("mediumG.txt");
        System.out.println(G);
    }
}