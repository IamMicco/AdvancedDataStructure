package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author marianky
 */
// An edge-weighted digraph, implemented using adjacency lists.
public class WeightedDigraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;                      // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private Bag<DirectedEdge>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v
    private double[] dist;
    private Set<Integer> settled;
    private PriorityQueue<DirectedEdge> pQueue;

    /**
     * Initializes an empty edge-weighted digraph with V vertices and 0 edges.
     */
    public WeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    //Initializes an edge-weighted digraph from the specified input stream.     
    public WeightedDigraph(String inFile) {        
        try {
            File file = new File(inFile);
            Scanner sc = new Scanner(file);
            this.V = sc.nextInt();
            indegree = new int[V];
            adj = (Bag<DirectedEdge>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<DirectedEdge>();
            }

            int E = sc.nextInt();
            for (int i = 0; i < E; i++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                validateVertex(v);
                validateVertex(w);
                double weight = sc.nextDouble();
                addEdge(new DirectedEdge(v, w, weight));
            }
        } catch (FileNotFoundException e) {            
            System.out.println(e.getMessage());
        }        
    }

    public WeightedDigraph (WeightedDigraph diGraph) {

        this.V = diGraph.V();
        this.E = diGraph.E();
        this.adj = (Bag<DirectedEdge>[]) diGraph.adj();
        this.indegree = diGraph.indegree();
    }

    //Returns the number of vertices in this edge-weighted digraph.
    public int V() {
        return V;
    }

    //Returns the number of edges in this edge-weighted digraph.
    public int E() {
        return E;
    }

    public Bag[] adj () {

        Bag<DirectedEdge>[] temp = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {

            temp[i] = new Bag(adj[i]);
        }
        return temp;
    }

    public int[] indegree () {

        int[] temp = new int[V];
        for (int i = 0; i < V; i++) {

            temp[i] = indegree[i];
        }
        return temp;
    }

    public ArrayList<Integer> depthFirstSearch (int start) {

        boolean[] visited = new boolean[V];
        ArrayList<Integer> orderOfIteration = new ArrayList<>();
        dfsHelper (start, visited, orderOfIteration);
        return orderOfIteration;
    }

    private void dfsHelper (int start, boolean[] visited, ArrayList<Integer> orderOfIteration) {

        visited[start] = true;
        orderOfIteration.add(start);
        Iterator<DirectedEdge> i = adj[start].iterator();
        while (i.hasNext()) {

            int next = i.next().to();
            if (!visited[next]) dfsHelper(next, visited, orderOfIteration);
        }
    }
    
    public ArrayList<Integer> breadthFirstSearch (int start) {
        
        boolean[] visited = new boolean[V];
        ArrayList<Integer> orderOfIteration = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[start] = true;
        orderOfIteration.add(start);
        queue.add(start);
        
        while (queue.size() != 0) {
            
            start = queue.poll();
            Iterator<DirectedEdge> i = adj[start].iterator();
            while (i.hasNext()) {
                
                int next = i.next().to();
                if (!visited[next]) {
                    
                    queue.add(next);
                    visited[next] = true;
                    orderOfIteration.add(next);
                }
            }
        }
        
        return orderOfIteration;
    }
    
    public boolean DAG () {
        
        boolean[] stack = new boolean[V];
        boolean[] visited = new boolean[V];
        
        for (int i = 0; i < V; i++) {
            
            if (helperDAG(i, visited, stack)) return true;
        }
        return false;
    }
    
    private boolean helperDAG (int i, boolean[] visited, boolean[] stack) {
        
        if (stack[i]) return true;
        if (visited[i]) return false;
        
        visited[i] = true;
        stack[i] = true;
        Iterator<DirectedEdge> it = adj[i].iterator();
        while (it.hasNext()) {
            
            if (helperDAG(it.next().from(), visited, stack)) return true;            
        }
        stack[i] = false;
        return false;
    }
    
    public void dijkstra (int src) {
        
        dist = new double[V];
        pQueue = new PriorityQueue<DirectedEdge>();
        settled = new HashSet<Integer>();
        for (int i = 0; i < V; i++) {
            
            dist[i] = Integer.MAX_VALUE;
        }
        
        pQueue.add(new DirectedEdge(src, src, 0));
        dist[src] = 0;
        while (settled.size() != V) {
            
            int u = pQueue.remove().from();
            settled.add(u);
            neighbours(u);
        }
    }
    
    private void neighbours (int u) {
        
        double edgeDistance = -1;
        double newDistance = -1;
        
        for (DirectedEdge item : adj[u]) {
            
            if (!settled.contains(item.to())) {
                
                edgeDistance = item.weight();
                newDistance = dist[u] + edgeDistance;
                
                if (newDistance < dist[item.to()]) {
                    
                    dist[item.to()] = newDistance;
                }
                pQueue.add(new DirectedEdge(u, item.to(), dist[item.to()]));
            }
        }
    }

    private boolean validateVertex(int v) {
        if (v < 0 || v >= V) {
            return false;
        }
        return true;
    }

    //Adds the directed edge e     
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    //Returns the directed edges incident from vertex v     
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    //Returns the number of directed edges incident from vertex v (i.e. outdegree)
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    //Returns the number of directed edges incident to vertex v (i.e. indegree)     
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    // Returns all directed edges in this edge-weighted digraph as an iterable
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    //Returns a string representation of this edge-weighted digraph.     
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //Testing everything
    public static void main(String[] args) {
        WeightedDigraph G = new WeightedDigraph("tinyEWD.txt");
        WeightedDigraph F = new WeightedDigraph(G);
        System.out.println(F);
        // int source = 0;
        // G.dijkstra(source);
        System.out.println("Does a loop occur in the tree");
        System.out.println(G.DAG());
        System.out.println("\n");
        System.out.println("Breadth First Search");
        System.out.println(G.breadthFirstSearch(0));
        System.out.println("\n");
        System.out.println("Depth First Search");
        System.out.println(G.depthFirstSearch(0));
        System.out.println("\n");
        // System.out.println(String.format("The shortest part from %d", source));
        // for (int i = 0; i < G.dist.length; i++) {

        //     System.out.println(source + " to " + i + " is " + G.dist[i]);
        // }
    }
}