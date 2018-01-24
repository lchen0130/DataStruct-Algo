import java.util.*;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, "
                    + "please pass in valid start");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, "
                    + "please pass in valid graph");
        }
        Map<Vertex<T>, List<Edge<T>>> adjMap = graph.getAdjList();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        ArrayList<Vertex<T>> myQueue = new ArrayList<>();
        myQueue.add(start);
        return breadthHelper(visited, myQueue, adjMap);
    }

    private static <T> List<Vertex<T>> breadthHelper(
                                         ArrayList<Vertex<T>> visited,
                                         ArrayList<Vertex<T>> myQueue,
                                         Map<Vertex<T>, List<Edge<T>>> adjMap) {
        if (myQueue.size() == 0) {
            return visited;
        }
        Vertex<T> deqElem = myQueue.remove(0);
        if (visited.contains(deqElem)) {
            return visited;
        }
        for(Edge<T> e: adjMap.get(deqElem)) {
            myQueue.add(e.getV());
        }
        visited.add(deqElem);
        return breadthHelper(visited, myQueue, adjMap);
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, "
                    + "please pass in valid start");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, "
                    + "please pass in valid graph");
        }
        Map<Vertex<T>, List<Edge<T>>> adjMap = graph.getAdjList();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        return helperDepth(start, visited, adjMap);


    }

    private static <T> List<Vertex<T>> helperDepth(Vertex<T> start,
                                      List<Vertex<T>> visited,
                                      Map<Vertex<T>, List<Edge<T>>> adjMap) {
        if (visited.size() == adjMap.size()) {
            return visited;
        }
        if (visited.contains(start)) {
            return visited;
        }
        visited.add(start);
        for(Edge<T> v: adjMap.get(start)) {
            helperDepth(v.getV(), visited, adjMap);
        }
        return visited;

    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, "
                    + "please pass in valid start");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, "
                    + "please pass in valid graph");
        }
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        for(Edge<T> e: graph.getEdges()) {
            distanceMap.put(e.getV(), Integer.MAX_VALUE);
        }
        distanceMap.replace(start, 0);
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        pq.add(new Edge<>(start, start, 0));
        while(visited.size() != graph.getAdjList().size()) {
            Edge<T> edgy = pq.remove();
            visited.add(edgy.getV());
            for(Edge<T> e: graph.getAdjList().get(edgy.getV())) {
                if (!visited.contains(e.getV())) {
                    pq.add(e);
                }
                if (distanceMap.get(e.getV()) > (distanceMap.get(e.getU()))
                        + e.getWeight()) {
                    distanceMap.replace(e.getV(), (distanceMap.get(e.getU()))
                            + e.getWeight());
                }
            }

        }


        return distanceMap;


    }


    /**
     * Runs Kruskal's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, please enter "
                + "valid graph");
        }

        if (graph.getEdges().size() == 0) {
            return null;
        }

        DisjointSet<Vertex<T>> disSet =
                new DisjointSet<>(graph.getAdjList().keySet());
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(graph.getEdges());
        HashSet<Edge<T>> set = new HashSet<>();
        int vSize = graph.getAdjList().size();

        while (!pq.isEmpty() && set.size() != 2 * vSize - 2) {
            Edge<T> dqElement = pq.peek();
            pq.remove(dqElement);
            if (!disSet.find(dqElement.getU()).equals(disSet.find(dqElement.getV()))) {
                set.add(dqElement);
                set.add(new Edge<>(dqElement.getV(), dqElement.getU(), dqElement.getWeight()));
            }
            disSet.union(dqElement.getU(), dqElement.getV());

        }
        return set;
    }
}