package com.asoiu.simbigraph.algorithms.shortestpath;

import edu.uci.ics.jung.graph.Hypergraph;
import javafx.util.Pair;

import java.util.PriorityQueue;

/**
 * @author Andrey Kurchanov
 */
public class UnweightedShortestPath<V, E> {

    private Hypergraph<V, E> graph;

    /**
     * Constructs and initializes the class.
     *
     * @author Andrey Kurchanov
     * @param graph the graph
     */
    public UnweightedShortestPath(Hypergraph<V, E> graph) {
        this.graph = graph;
    }

	/**
	 * Calculates the shortest path distances from a given <code>source</code>
	 * vertex to all other vertices, ignoring edge weights.
	 *
	 * @author Andrey Kurchanov
	 * @param source the source vertex
	 * @return eccentricity of the <code>source</code> vertex
	 */
    public int getEccentricity(V source) {
    	int ecc = 0;
    	int[] distances = new int[graph.getVertexCount()];
        for (int i = 0; i < distances.length; i++) {
        	distances[i] = Integer.MAX_VALUE;
        }	
        distances[(Integer) source] = 0;
        PriorityQueue<Pair<V, Integer>> q = new PriorityQueue<>(
            100, (pair1, pair2) -> (pair1.getValue() > pair2.getValue() ? 1 : -1)
        );
        q.add(new Pair<>(source, 0));
        Pair<V, Integer> tempPair;
        V v;
        int cur_d;
        while (!q.isEmpty()) {
        	tempPair = q.poll();
        	v = tempPair.getKey();
        	cur_d = tempPair.getValue();
            if (cur_d > distances[(Integer) v]) {
                continue;
            }
            for (V neighbor : graph.getSuccessors(v)) {
                if (distances[(Integer) v] + 1 < distances[(Integer) neighbor]) {
                    distances[(Integer) neighbor] = distances[(Integer) v] + 1;
                    q.add(new Pair<>(neighbor, distances[(Integer) neighbor]));
                }
            }
            if (distances[(Integer) v] > ecc) {
                ecc = distances[(Integer)v];
            }
        }
        return ecc;
    }
    
}