package CS5234_MiniProject;

import org.jgrapht.Graph;

import java.util.List;

public class Benchmark {
    // Each iteration is a different set of tests
    // The plans are as follow:
    // (Graph parameter N, Graph Parameter M)
    public void runBenchmarks(List<List<Integer>> benchmark_plans, Long seed) {
        for (List<Integer> benchmark : benchmark_plans) {
            GraphUtils graph_utils = new GraphUtils();
            // Next step can take a long time
            Graph benchmark_graph = graph_utils.generateRandomGraph(benchmark.get(0), benchmark.get(1), seed);
            AP_BFS ap_bfs_algorithms = new AP_BFS();
            // TODO

        }

    }


}
