package CS5234_MiniProject;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import java.util.*;

public class AP_BFS {
    public long runAP_BFS(Graph graph) {
        // Needs to be changed in order to return the benchmark time too
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        List<List<String>> iteration_plan = new ArrayList<>();
        // Plan for iteration, (v_i, v_i-1)
        iteration_plan = get_euler_tour_order(graph);
        BFS bfs = new BFS();
        // Run the first iteration
        results.put(iteration_plan.get(0).get(0), bfs.RunMR_BFS(graph, iteration_plan.get(0).get(0)));
        // Run the next iterations
        long start_time = System.currentTimeMillis();
        for (List<String> nodes_tuple: iteration_plan) {
            if (nodes_tuple.equals(iteration_plan.get(0))) {
                continue;
            }
            else {
                String v_i = nodes_tuple.get(0);
                String v_i_minus_1 = nodes_tuple.get(1);
                Hashtable<String, Integer> previous_bfs = results.get(v_i_minus_1);
                results.put(v_i, bfs.RunIncremental_BFS(graph, v_i, previous_bfs, v_i_minus_1));
            }
        }
        return (System.currentTimeMillis() - start_time);
    }

    public long runAP_BFS_extension(Graph graph) {
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        List<List<String>> iteration_plan = new ArrayList<>();
        // Plan for iteration, (v_i, v_i-1)
        iteration_plan = get_extension_order(graph);
        BFS bfs = new BFS();
        // Run the first iteration
        results.put(iteration_plan.get(0).get(0), bfs.RunMR_BFS(graph, iteration_plan.get(0).get(0)));
        // Run the next iterations
        long start_time = System.currentTimeMillis();
        for (List<String> nodes_tuple: iteration_plan) {
            if (nodes_tuple.equals(iteration_plan.get(0))) {
                continue;
            }
            else {
                String v_i = nodes_tuple.get(0);
                String v_i_minus_1 = nodes_tuple.get(1);
                Hashtable<String, Integer> previous_bfs = results.get(v_i_minus_1);
                results.put(v_i, bfs.RunIncremental_BFS(graph, v_i, previous_bfs, v_i_minus_1));
            }
        }
        return (System.currentTimeMillis() - start_time);
    }

    public long runMR_BFS_Only(Graph graph) {
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        Iterator<String> vertices_iterator = graph.vertexSet().iterator();
        BFS mr_bfs = new BFS();
        long start_time = System.currentTimeMillis();
        while (vertices_iterator.hasNext()) {
            String node = vertices_iterator.next();
            results.put(node, mr_bfs.RunMR_BFS(graph, node));
        }
        return (System.currentTimeMillis() - start_time);
    }

    public long runFloyd_Warshall(Graph graph) {
        // Doc https://jgrapht.org/javadoc-1.3.1/org/jgrapht/alg/shortestpath/FloydWarshallShortestPaths.html
        FloydWarshallShortestPaths floydWarshallShortestPaths = new FloydWarshallShortestPaths(graph);
        // Start timer
        long start_time = System.currentTimeMillis();
        // the paths are computed the first time we call a method of this class
        floydWarshallShortestPaths.getShortestPathsCount();
        return (System.currentTimeMillis() - start_time);
    }

    public List<List<String>> get_extension_order(Graph graph) {
        GraphIterator<String, DefaultEdge> breadthFirstIterator = new BreadthFirstIterator(graph);
        Hashtable<String, String> order_dictionary = new Hashtable<>();
        List<List<String>> order = new ArrayList<>();
        while (breadthFirstIterator.hasNext()) {
            String node = breadthFirstIterator.next();
            List<String> children = Graphs.neighborListOf(graph, node);
            for (String child: children) {
                if (!order_dictionary.containsKey(child)) {
                    order_dictionary.put(child, node);
                }
            }
        }
        for (String key: order_dictionary.keySet()) {
            String v_i_minus_1 = order_dictionary.get(key);
            String v_i = key;
            order.add(Arrays.asList(v_i, v_i_minus_1));
        }
        return order;
    }

    public List<List<String>> get_euler_tour_order(Graph graph) {
        List<List<String>> order = new ArrayList<>();
        HierholzerEulerianCycle hierholzerEulerianCycle = new HierholzerEulerianCycle();
        List<String> eulerian_cycle_vertex_list = hierholzerEulerianCycle.getEulerianCycle(graph).getVertexList();
        // Plan for iteration, (v_i, v_i-1)
        order.add(Arrays.asList(eulerian_cycle_vertex_list.get(0), eulerian_cycle_vertex_list.get(0)));
        for (Integer i=1; i < eulerian_cycle_vertex_list.size(); i++) {
            order.add(Arrays.asList(eulerian_cycle_vertex_list.get(i), eulerian_cycle_vertex_list.get(i-1)));
        }
        return order;
    }
}
