package com.humbertdany.tpproject.util.binarystack;

/**
 * Interface used to give the minumum function of Binary Stack
 * Since we have multiple version in the TP
 * @param <T>
 */
public interface IBinaryStack<T extends Comparable> {
	T deleteMin();
	T getMin();
	void insert(T t);
}
