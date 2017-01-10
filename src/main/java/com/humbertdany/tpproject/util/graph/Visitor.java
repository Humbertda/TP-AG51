package com.humbertdany.tpproject.util.graph;

public interface Visitor<T> {
	/**
	 * Called by the graph traversal methods when a vertex is first visited.
	 *
	 * @param g -
	 *          the graph
	 * @param v -
	 *          the vertex being visited.
	 */
	public void visit(Graph<T> g, Vertex<T> v);
}