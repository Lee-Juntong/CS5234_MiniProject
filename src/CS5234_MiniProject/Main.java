package CS5234_MiniProject;

import org.jgrapht.Graph;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Test graph by printing it
        GraphUtils graph_utils = new GraphUtils();
        Graph graph = graph_utils.generateRandomGraph(100, 4000, 100L);
        try {
            graph_utils.visualizeGraph(graph, System.getProperty("user.dir")+"\\graph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
