package CS5234_MiniProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> seeds = Arrays.asList(100L, 200L, 300L);
        for (Long seed : seeds) {
            System.out.println("Benchmark for seed: "+seed);
            Benchmark benchmark = new Benchmark();
            List<List<Integer>> benchmark_plan = new ArrayList<>();
            List<Integer> plan_1 = Arrays.asList(1000, 400000);
            benchmark_plan.add(plan_1);
            List<Integer> plan_2 = Arrays.asList(1000, 200000);
            benchmark_plan.add(plan_2);
            List<Integer> plan_3 = Arrays.asList(1000, 50000);
            benchmark_plan.add(plan_3);
            benchmark.runBenchmarks(benchmark_plan,seed);
        }

    }
}
