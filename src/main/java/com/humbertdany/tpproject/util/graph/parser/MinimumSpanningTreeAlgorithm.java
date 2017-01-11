package com.humbertdany.tpproject.util.graph.parser;

import com.humbertdany.tpproject.util.graph.Graph;
import com.humbertdany.tpproject.util.graph.VertexData;

public interface MinimumSpanningTreeAlgorithm<T extends VertexData> {
	double[][] getMinimumSpanningTreeAlgorithm(Graph<T> graph);

	/**
	 * Get a String description of the spanning tree
	 * @param spanning_tree the MinimumSpanningTree to parse
	 * @return the description
	 */
	static String getMSPDescription(double[][] spanning_tree){
		StringBuilder sb = new StringBuilder();
		final int numberOfVertices = spanning_tree.length-1;
		sb.append("The spanning tree is ");
		for (int i = 1; i <= numberOfVertices; i++)
			sb.append("\t").append(i);
		sb.append("\n");
		for (int source = 1; source <= numberOfVertices; source++)
		{
			sb.append(source).append("\t");
			for (int destination = 1; destination <= numberOfVertices; destination++) {
				sb.append(spanning_tree[source][destination]).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
