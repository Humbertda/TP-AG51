package com.humbertdany.tpproject.util.binarystack;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

public class ABinaryStack<T extends Comparable> implements IBinaryStack<T> {

	/**
	 * the array factory to gen anything needed
	 */
	final private ArrayFactory<T> arrayFactory;

	public ABinaryStack(final ArrayFactory<T> factory) {
		this.arrayFactory = factory;
	}

	final protected ArrayFactory<T> getArrayFactory() {
		return arrayFactory;
	}

}
