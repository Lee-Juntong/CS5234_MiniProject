package CS5234_MiniProject;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
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
import java.util.function.Supplier;

public class GraphUtils {
    public Graph generateRandomGraph(Integer number_of_nodes, Integer number_of_edges, Long seed) {
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
