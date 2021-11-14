package CS5234_MiniProject;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class GraphUtils {
    public Graph generateRandomGraph(Integer number_of_nodes, Integer number_of_edges, Long seed) {
        // The graph generated is strongly-connected
        Supplier<String> vSupplier = new Supplier<String>()
        {
            private int id = 0;

            @Override
            public String get()
            {
                return "v" + id++;
            }
        };
        Graph<String, DefaultEdge> graph
                = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false );
         new GnmRandomGraphGenerator<String, DefaultEdge>(number_of_nodes, number_of_edges, seed).generateGraph(graph, null);
        // We now add edges to make the graph strongly connected
        ConnectivityInspector connectivity_inspector = new ConnectivityInspector(graph);
        while (!connectivity_inspector.isConnected()) {
            List<Set<String>> connected_sets = connectivity_inspector.connectedSets();
            // We add one edge between the first node of the Sets Si and Si-1, with i from 1 to connected_sets.size() - 1
            for (Integer i=1; i < connected_sets.size(); i++) {
                graph.addEdge(connected_sets.get(i-1).stream().findFirst().get(),connected_sets.get(i).stream().findFirst().get());
            }
        }
        return graph;
    }

    public void visualizeGraph(Graph graph, String file_path) throws IOException {
        JGraphXAdapter<String, DefaultEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultEdge>(graph);
        mxIGraphLayout layout = new mxFastOrganicLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File(file_path);
        ImageIO.write(image, "PNG", imgFile);
    }
}
