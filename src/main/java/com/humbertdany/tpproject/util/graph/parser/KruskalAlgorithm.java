package com.humbertdany.tpproject.util.graph.parser;

import com.humbertdany.tpproject.util.graph.Edge;
import com.humbertdany.tpproject.util.graph.Graph;
import com.humbertdany.tpproject.util.graph.VertexData;

import java.util.*;

public class KruskalAlgorithm<T extends VertexData> extends AMinimumSpanningTreeAlgorithm<T> {

	@Override
	public List<Edge<T>> getMinimumSpanningTreeAlgorithm(Graph<T> graph) {
		final KruskalSolver s = new KruskalSolver(graph);
		return s.KruskalMST();
	}


	// A class to represent a subset for union-find
	static private class subset {
		int parent, rank;
	}

	/**
	 * Inspired from http://www.sanfoundry.com/java-program-find-mst-using-kruskals-algorithm/
	 */
	final class KruskalSolver {

		int V, E;    // V-> no. of vertices & E->no.of edges
		Edge edge[]; // collection of all edges

		KruskalSolver(final Graph<T> graph) {
			V = graph.getVerticies().size();
			E = graph.getEdges().size();
			int i = 0;
			edge = new Edge[E];
			for(Edge<T> e :  graph.getEdges()){
				edge[i] = e;
				i++;
			}
		}

		// A utility function to find set of an element i
		// (uses path compression technique)
		int find(subset subsets[], int i) {
			// find root and make root as parent of i (path compression)
			if (subsets[i].parent != i)
				subsets[i].parent = find(subsets, subsets[i].parent);

			return subsets[i].parent;
		}

		// A function that does union of two sets of x and y
		// (uses union by rank)
		void Union(subset subsets[], int x, int y) {
			int xroot = find(subsets, x);
			int yroot = find(subsets, y);

			// Attach smaller rank tree under root of high rank tree
			// (Union by Rank)
			if (subsets[xroot].rank < subsets[yroot].rank)
				subsets[xroot].parent = yroot;
			else if (subsets[xroot].rank > subsets[yroot].rank)
				subsets[yroot].parent = xroot;

				// If ranks are same, then make one as root and increment
				// its rank by one
			else
			{
				subsets[yroot].parent = xroot;
				subsets[xroot].rank++;
			}
		}

		// The main function to construct MST using Kruskal's algorithm
		List<Edge<T>> KruskalMST() {
			Edge result[] = new Edge[V];  // Tnis will store the resultant MST
			int e = 0;  // An index variable, used for result[]
			int i = 0;  // An index variable, used for sorted edges

			// Step 1:  Sort all the edges in non-decreasing order of their
			// weight.  If we are not allowed to change the given graph, we
			// can create a copy of array of edges
			Arrays.sort(edge, (p1, p2) -> {
				if (p1.getCost() < p2.getCost()) return -1;
				if (p1.getCost() > p2.getCost()) return 1;
				return 0;
			});

			// Allocate memory for creating V ssubsets
			subset subsets[] = new subset[V];
			for(i=0; i<V; ++i)
				subsets[i]=new subset();

			// Create V subsets with single elements
			for (int v = 0; v < V; ++v)
			{
				subsets[v].parent = v;
				subsets[v].rank = 0;
			}

			i = 0;  // Index used to pick next edge

			// Number of edges to be taken is equal to V-1
			while (e < V - 1) {
				// Step 2: Pick the smallest edge. And increment the index
				// for next iteration
				Edge next_edge = edge[i++];

				int x = find(subsets, next_edge.getFrom().getId());
				int y = find(subsets, next_edge.getTo().getId());

				// If including this edge does't cause cycle, include it
				// in result and increment the index of result for next edge
				if (x != y)
				{
					result[e++] = next_edge;
					Union(subsets, x, y);
				}
				// Else discard the next_edge
			}

			// print the contents of result[] to display the built MST
			ArrayList<Edge<T>> edgesResults = new ArrayList<>();
 			for (i = 0; i < e; ++i) {
			    edgesResults.add(result[i]);
		    }

			return edgesResults;
		}

	}

}
