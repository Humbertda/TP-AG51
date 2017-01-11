package com.humbertdany.tpproject.util.graph.parser;

import com.humbertdany.tpproject.util.graph.Edge;
import com.humbertdany.tpproject.util.graph.Graph;
import com.humbertdany.tpproject.util.graph.Vertex;
import com.humbertdany.tpproject.util.graph.VertexData;

import java.util.*;

public class KruskalAlgorithm<T extends VertexData> implements MinimumSpanningTreeAlgorithm<T> {

	@Override
	public double[][] getMinimumSpanningTreeAlgorithm(Graph<T> graph) {
		final KruskalSolver s = new KruskalSolver(graph);
		return s.kruskalAlgorithm();
	}

	/**
	 * Inspired from http://www.sanfoundry.com/java-program-find-mst-using-kruskals-algorithm/
	 */
	private final class KruskalSolver {
		private static final int MAX_VALUE = 999;

		private final ArrayList<Edge<T>> edges;
		private final List<Vertex<T>> vertices;
		private final int numberOfVertices;
		private int visited[];
		private final Map<String, Integer> mapNameToId;

		private final double spanning_tree[][];
		private final double adjacency_matrix[][];

		KruskalSolver(final Graph<T> graph) {
			vertices = new ArrayList<>(graph.getVerticies());
			edges = new ArrayList<>(graph.getEdges());
			numberOfVertices = vertices.size() - 1;
			adjacency_matrix = new double[vertices.size() + 1][vertices.size() + 1];
			for (int i = 1; i <= vertices.size() - 1; i++) {
				for (int j = 1; j <= vertices.size() - 1; j++) {
					adjacency_matrix[i][j] = vertices.get(i).getCostTo(vertices.get(j));
					if (i == j) {
						adjacency_matrix[i][j] = 0;
						continue;
					}
					if (adjacency_matrix[i][j] == 0) {
						adjacency_matrix[i][j] = MAX_VALUE;
					}
				}
			}
			mapNameToId = new HashMap<>();
			int counter = 0;
			for(Vertex v : vertices){
				mapNameToId.put(v.getName(), counter);
				counter++;
			}
			visited = new int[this.numberOfVertices + 1];
			spanning_tree = new double[numberOfVertices + 1][numberOfVertices + 1];
		}

		double[][] kruskalAlgorithm() {
			boolean finished = false;
			Collections.sort(edges, new EdgeComparator());
			CheckCycle checkCycle = new CheckCycle();
			for (Edge edge : edges)
			{
				double cost = edge.getCost();
				spanning_tree[mapNameToId.get(edge.getFrom().getName())][mapNameToId.get(edge.getTo().getName())] = cost;
				spanning_tree[mapNameToId.get(edge.getTo().getName())][mapNameToId.get(edge.getFrom().getName())] = cost;
				if (checkCycle.checkCycle(spanning_tree, mapNameToId.get(edge.getFrom().getName()))) {
					spanning_tree[mapNameToId.get(edge.getFrom().getName())][mapNameToId.get(edge.getTo().getName())] = 0;
					spanning_tree[mapNameToId.get(edge.getTo().getName())][mapNameToId.get(edge.getFrom().getName())] = 0;
					continue;
				}
				visited[mapNameToId.get(edge.getFrom().getName())] = 1;
				visited[mapNameToId.get(edge.getTo().getName())] = 1;
				for (int aVisited : visited) {
					if (aVisited == 0) {
						finished = false;
						break;
					} else {
						finished = true;
					}
				}
				if (finished)
					break;
			}
			return spanning_tree;
		}

	}

	/**
	 * CheckCycle class
	 */
	private final class CheckCycle {
		private Stack<Integer> stack;
		private double adjacencyMatrix[][];

		CheckCycle() {
			stack = new Stack<>();
		}

		boolean checkCycle(double adjacency_matrix[][], int source) {
			int number_of_nodes = adjacency_matrix[source].length - 1;

			adjacencyMatrix = new double[number_of_nodes + 1][number_of_nodes + 1];
			for (int sourceVertex = 1; sourceVertex <= number_of_nodes; sourceVertex++) {
				System.arraycopy(adjacency_matrix[sourceVertex], 1, adjacencyMatrix[sourceVertex], 1, number_of_nodes);
			}

			int visited[] = new int[number_of_nodes + 1];
			int element = source;
			int i = source;
			visited[source] = 1;
			stack.push(source);

			while (!stack.isEmpty()) {
				element = stack.peek();
				i = element;
				while (i <= number_of_nodes) {
					if (adjacencyMatrix[element][i] >= 1 && visited[i] == 1) {
						if (stack.contains(i)) {
							return true;
						}
					}
					if (adjacencyMatrix[element][i] >= 1 && visited[i] == 0)
					{
						stack.push(i);
						visited[i] = 1;
						adjacencyMatrix[element][i] = 0;// mark as labelled;
						adjacencyMatrix[i][element] = 0;
						element = i;
						i = 1;
						continue;
					}
					i++;
				}
				stack.pop();
			}
			return false;
		}
	}

	/**
	 * Edge Comparator
	 */
	private final class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			if (edge1.getCost() < edge2.getCost())
				return -1;
			if (edge1.getCost() > edge2.getCost())
				return 1;
			return 0;
		}
	}

}
