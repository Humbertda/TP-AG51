package com.humbertdany.tpproject.util.graph.parser;

import com.humbertdany.tpproject.util.graph.Edge;
import com.humbertdany.tpproject.util.graph.Graph;
import com.humbertdany.tpproject.util.graph.Vertex;
import com.humbertdany.tpproject.util.graph.VertexData;

import java.util.*;
import java.util.stream.Collectors;

public class PrimAlgorithm<T extends VertexData> extends AMinimumSpanningTreeAlgorithm<T> {
	
	// TODO MAKE SURE THE ALGORITHM IS WORKING
	@Override
	public List<Edge<T>> getMinimumSpanningTreeAlgorithm(Graph<T> graph) {
		Prims p = new Prims(graph);
		return p.primMST();
	}
	
	
	private final class Prims {
		
		private final Graph<T> graph;
		
		Prims(Graph<T> g){
			graph = g;
			if (graph == null)
				throw (new NullPointerException("Graph must be non-NULL."));
		}
		
		/**
		 * @implNote Prim's algorithm only works on undirected graphs
		 * @return the MST
		 */
		List<Edge<T>> primMST () {
			
			final Set<Vertex<T>> unvisited = new HashSet<>();
			unvisited.addAll(graph.getVerticies().values());
			unvisited.remove(graph.getRootVertex()); // O(1)
			
			final List<Edge<T>> path = new ArrayList<>();
			final Queue<Edge<T>> edgesAvailable = new PriorityQueue<>((o1, o2) -> o1.getCost().compareTo(o2.getCost()));
			
			Vertex<T> vertex = graph.getRootVertex();
			while (!unvisited.isEmpty()) {
				// Add all edges to unvisited vertices
				edgesAvailable.addAll(vertex.getOutgoingEdges().stream().filter(e -> unvisited.contains(e.getTo())).collect(Collectors.toList()));
				
				// Remove the lowest cost edge
				final Edge<T> e = edgesAvailable.remove();
				path.add(e); // O(1)
				vertex = e.getTo();
				unvisited.remove(vertex); // O(1)
			}
			
			return path;
		}
		
	}
}
