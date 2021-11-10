package CS5234_MiniProject;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import java.util.*;

public class BFS {
    public Hashtable<String, Integer> RunMR_BFS(Graph graph, String starting_node) {
        // needs to handle case where they are not in the same cc
        List<List<String>> layers = new ArrayList<>();
        Hashtable<String, Integer> bfs_results = new Hashtable<>();
        Integer layer_count = 0;
        layers.add(Arrays.asList(starting_node));
        Integer node_count = graph.vertexSet().size() - 1; // Count the number of nodes that haven't been added to a layer
        while (node_count != 0) {
            layer_count ++;
            List<String> layer_i = new ArrayList<>(); // Use array list and not linked list
            // First build L[i]
            if (layer_count >= 2) {
                for (String node: layers.get(layer_count - 2)) {
                    layer_i.addAll(Graphs.neighborListOf(graph, node));
                }
            }
            for (String node: layers.get(layer_count - 1)) {
                layer_i.addAll(Graphs.neighborListOf(graph, node));
            }
            // Sort Layer i
            Collections.sort(layer_i);
            // Remove duplicates, as explained in the paper, works because array is sorted
            for (Integer j=0; j< layer_i.size() - 1; j++) {
                if (layer_i.get(j) == layer_i.get(j+1)) {
                    layer_i.remove(j+1);
                    j--; // Need to keep the index so that it can see if j+2 (now j+1) is equal
                }
            }
            // Remove previous layer's node
            if (layer_count == 1) {
                layer_i.removeAll(layers.get(0));
            }
            else { // We need to ensure layer count is at least 2
                layer_i.removeAll(layers.get(layer_count - 1));
                layer_i.removeAll(layers.get(layer_count - 2));
            }
            // Decrement node count
            node_count = node_count - layer_i.size();
            layers.add(layer_i);
            for (String node : layer_i) { // Add the nodes to the structure we will return
                bfs_results.put(node, layer_count);
            }
        }
        return bfs_results;
    }

    public Hashtable<String, Integer> RunIncremental_BFS(Graph graph, String starting_node, Hashtable<String, Integer> previous_bfs_results, String previous_bfs_node) {
        // needs to handle case where they are not in the same cc
        List<List<String>> layers = new ArrayList<>();
        Integer node_count = graph.vertexSet().size(); // Count the number of nodes that haven't been added to a layer
        Integer layer_count = 0;
        // Build A[i], the list of lists
        Hashtable<String, Integer> bfs_results = new Hashtable<>();
        List<List<List<String>>> previous_adjacency_lists = new ArrayList<>();
        Set<String> nodes = previous_bfs_results.keySet();
        for (String node : nodes) {
            List<String> neighbours = Graphs.neighborListOf(graph, node);
            List<List<String>> new_a_i = previous_adjacency_lists.get(previous_bfs_results.get(node));
            new_a_i.add(neighbours);
            previous_adjacency_lists.set(previous_bfs_results.get(node), new_a_i);
        }
        Integer distance_u_v = previous_bfs_results.get(starting_node);
        layers.set(0, Arrays.asList(starting_node));

        while (node_count != 0) {
            layer_count ++;
            List<String> layer_i = new ArrayList<>(); // Use array list and not linked list
            // First build L[i]
            Integer j_lower_bound = Math.max(0, layer_count - 1 - distance_u_v);
            Integer j_upper_bound = Math.min(graph.vertexSet().size() - 1 , layer_count -1 + distance_u_v);
            for (Integer j = j_lower_bound; j <= j_upper_bound; j++) {
                for (String node : layers.get(layer_count - 1)) {
                    if (previous_adjacency_lists.contains(Graphs.neighborListOf(graph, node))) {
                        layer_i.addAll(Graphs.neighborListOf(graph, node));
                    }
                }
            }
            // Sort Layer i
            Collections.sort(layer_i);
            // Remove duplicates, as explained in the paper, works because array is sorted
            for (Integer j=0; j< layer_i.size() - 1; j++) {
                if (layer_i.get(j) == layer_i.get(j+1)) {
                    layer_i.remove(j+1);
                    j--; // Need to keep the index so that it can see if j+2 (now j+1) is equal
                }
            }
            // Remove previous layer's node
            if (layer_count == 1) {
                layer_i.removeAll(layers.get(0));
            }
            else { // We need to ensure layer count is at least 2
                layer_i.removeAll(layers.get(layer_count - 1));
                layer_i.removeAll(layers.get(layer_count - 2));
            }
            // Decrement node count
            node_count = node_count - layer_i.size();
            layers.set(layer_count, layer_i);
            for (String node : layer_i) { // Add the nodes to the structure we will return
                bfs_results.put(node, layer_count);
            }
        }
        return bfs_results;
    }
}
