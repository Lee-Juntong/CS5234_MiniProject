package CS5234_MiniProject;

import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class AP_BFS {
    public Hashtable<String, Hashtable<String, Integer>> runAP_BFS(Graph graph) {
        // Needs to be changed in order to return the benchmark time too
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        List<List<String>> iteration_plan = new ArrayList<>();// Plan for iteration, (key: v_i, value: v_i-1)
        // TODO Create order by populating iteration plan

        BFS bfs = new BFS();
        // Run the first iteration
        results.put(iteration_plan.get(0).get(0), bfs.RunMR_BFS(graph, iteration_plan.get(0).get(0)));
        // Run the next iterations
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
        return results;
    }

    public Hashtable<String, Hashtable<String, Integer>> runAP_BFS_extension(Graph graph) {
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        List<List<String>> iteration_plan = new ArrayList<>();// Plan for iteration, (key: v_i, value: v_i-1)
        // TODO Create order by populating iteration plan

        BFS bfs = new BFS();
        // Run the first iteration
        results.put(iteration_plan.get(0).get(0), bfs.RunMR_BFS(graph, iteration_plan.get(0).get(0)));
        // Run the next iterations
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
        return results;
    }

    public Hashtable<String, Hashtable<String, Integer>> runMR_BFS_Only(Graph graph) {
        Hashtable<String, Hashtable<String, Integer>> results = new Hashtable<>();
        Iterator<String> vertices_iterator = graph.vertexSet().iterator();
        BFS mr_bfs = new BFS();
        while (vertices_iterator.hasNext()) {
            String node = vertices_iterator.next();
            results.put(node, mr_bfs.RunMR_BFS(graph, node));
        }
        return results;
    }
}
