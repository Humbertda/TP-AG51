package com.humbertdany.tpproject.util.graph;

public interface GraphObserver<T extends Graph> {

	void graphUpdated(final T g);

}
