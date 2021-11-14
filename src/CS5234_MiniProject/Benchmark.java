package CS5234_MiniProject;

import org.jgrapht.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    // Each iteration is a different set of tests
    // The plans are as follow:
    // (Graph parameter N, Graph Parameter M)
    public void runBenchmarks(List<List<Integer>> benchmark_plans, Long seed) {
        Integer i = 0;
        List<BenchmarkResult> benchmarkResults = new ArrayList<>();
        for (List<Integer> benchmark : benchmark_plans) {
            GraphUtils graph_utils = new GraphUtils();
            // Next step can take a long time
            System.out.println("Generating graph " + i);
            Graph benchmark_graph = graph_utils.generateRandomGraph(benchmark.get(0), benchmark.get(1), seed);
            System.out.println("Finished generating graph");
            AP_BFS ap_bfs_algorithms = new AP_BFS();
            System.out.println("Starting benchmarks");
            System.out.println("Starting Floyd-warshall");
            benchmarkResults.add(new BenchmarkResult("Floyd-warshall", i, ap_bfs_algorithms.runFloyd_Warshall(benchmark_graph)));
            System.out.println(benchmarkResults.get(benchmarkResults.size() -1));
            System.out.println("Starting MR-BFS only");
            benchmarkResults.add(new BenchmarkResult("MR-BFS only", i, ap_bfs_algorithms.runMR_BFS_Only(benchmark_graph)));
            System.out.println(benchmarkResults.get(benchmarkResults.size() -1));
            System.out.println("Starting AP-BFS");
            benchmarkResults.add(new BenchmarkResult("AP-BFS", i, ap_bfs_algorithms.runAP_BFS(benchmark_graph)));
            System.out.println(benchmarkResults.get(benchmarkResults.size() -1));
            System.out.println("Starting AP-BFS extension");
            benchmarkResults.add(new BenchmarkResult("AP-BFS extension", i, ap_bfs_algorithms.runAP_BFS_extension(benchmark_graph)));
            System.out.println(benchmarkResults.get(benchmarkResults.size() -1));
            System.out.println("Finished running benchmarks for graph " + i);
            i++;
        }
        try {
            writeToCSV(System.getProperty("user.dir")+"\\results.csv", benchmarkResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeToCSV(String file_name_and_path, List<BenchmarkResult> data_to_write) throws IOException {
        File csvFile = new File(file_name_and_path);
        FileWriter fileWriter = new FileWriter(csvFile);
        for (BenchmarkResult line: data_to_write) {
            fileWriter.write(line.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public class BenchmarkResult {
        public String algorithm;
        public Integer graph_number;
        public Long result; // Time taken, in seconds

        public BenchmarkResult(String algorithm, Integer graph_number, Long result) {
            this.algorithm = algorithm;
            this.graph_number = graph_number;
            this.result = result;
        }

        public String toString() {
            return algorithm + "," + result + "," + graph_number;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public Integer getGraph_number() {
            return graph_number;
        }

        public void setGraph_number(Integer graph_number) {
            this.graph_number = graph_number;
        }

        public Long getResult() {
            return result;
        }

        public void setResult(Long result) {
            this.result = result;
        }
    }


}
