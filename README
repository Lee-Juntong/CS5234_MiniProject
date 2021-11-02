# CS5234 Project

Code written for the CS5234 Project (NUS, Year 2021, Semester 1).

Java implementation of the algorithm AP-BFS (and a variant designed by us), a cache-oblivious algorithm to solve the APSP problem (All-Pairs Shortest Path) in undirected unweighted graphs, used for example to compute the diameter of the graph.
Benchmark of the AP-BFS algorithm and our variant to compare their performances at the step 3. of the algorithm.

## Structure of the algorithms

The algorithm does the following:

1. Establish an order in the nodes, and return a list of tuples (u,v)
2. Run MR-BFS on the first node of the first tuple
3. For every tuple (u,v) in the list returned by step 1: Compute IncrementBFS(G,u,v, d(u,.)) which will return d(v,.)

## Our variant of the algorithm:

Our variant changed the step 1, by returning a different order, while the AP-BFS algorithm relies on the order provided by the Euler Tour of a spanning tree of the graph.

## Algorithms implemented:

To implement the algorithm AP-BFS, the following algorithms have been implemented
- An algorithm returning the order obtained by the Euler Tour of a spanning tree of an undirected, unweighted graph
- Our variant's contribution, an algorithm returning a different list of tuples (u,v), which result in a more efficient step 3. of the algorithm
- The algorithm MR-BFS
- The algorithm IncrementBFS

## Benchmarks

TODO: Decide on the parameters of the benchmarks, and implement a method to generate graphs, write scripts to run these benchmarks.

## References

TODO



