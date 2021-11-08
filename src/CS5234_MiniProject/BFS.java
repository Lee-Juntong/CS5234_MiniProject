package CS5234_MiniProject;

import org._3pq.jgrapht.Graph;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class BFS {
    public Hashtable<Integer, List<Node>> RunMR_BFS(Graph graph, Node starting_node) {
        Hashtable<Integer, List<Node>> distances = new Hashtable<>();
        Integer layer_count = 0;
        distances.put(0, Arrays.asList(starting_node));
        Integer node_count = graph.vertexSet().size() - 1; // Count the number of nodes that haven't been added to a layer
        while (node_count != 0) {
            layer_count ++;
            List<Node> layer_i = new ArrayList<>();
            // First build L[i]

            // Remove duplicates

            // Remove previous layer's node

            // Decrement node count
            node_count = node_count - layer_i.size();
            distances.put(layer_count, layer_i);
        }
        return distances;

    }

    public Hashtable<Integer, List<Node>> RunIncremental_BFS(Graph graph, Node starting_node, Hashtable<Integer, List<Node>> previous_bfs, Node previous_bfs_node) {
        Hashtable<Integer, List<Node>> distances = new Hashtable<>();
        Integer node_count = graph.vertexSet().size(); // Count the number of nodes that haven't been added to a layer
        Integer layer_count = 0;
        distances.put(0, Arrays.asList(starting_node));

        while (node_count != 0) {
            layer_count ++;
            List<Node> layer_i = new ArrayList<>();
            // First build L[i]

            // Remove duplicates

            // Remove previous layer's node

            distances.put(layer_count, layer_i);
        }
        return distances;
    }
}
