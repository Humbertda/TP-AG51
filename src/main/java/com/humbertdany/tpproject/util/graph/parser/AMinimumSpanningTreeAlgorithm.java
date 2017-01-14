package com.humbertdany.tpproject.util.graph.parser;

import com.humbertdany.tpproject.util.graph.Graph;
import com.humbertdany.tpproject.util.graph.Vertex;
import com.humbertdany.tpproject.util.graph.VertexData;

import java.util.Map;

public abstract class AMinimumSpanningTreeAlgorithm<T extends VertexData> implements MinimumSpanningTreeAlgorithm<T> {

	private static final int MAX_VALUE = 999;

	protected final double[][] getAgencyMatrixForGraph(Graph<T> g){
		final Map<Integer, Vertex<T>> vertices = g.getVerticies();
		double[][] adjacency_matrix = new double[vertices.size()][vertices.size()];
		for (int i = 0; i < vertices.size() - 1; i++) {
			for (int j = 0; j < vertices.size() - 1; j++) {
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
		return adjacency_matrix;
	}

}
