package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * @implNote Tri par Tas
 * @author dhumbert
 * @param <T>
 */
public class SortingStack<T extends Comparable<T>> extends ASortingAlgorithm<T> {

	public SortingStack(ArrayFactory<T> arrayFactory) {
		super(arrayFactory);
	}

	@Override
	public String getAlgorithmName() {
		return "Stack";
	}

	@Override
	public Collection<T> sort(T[] toSort) {
		final Stack<T> stackToSort = new Stack<>();
		Collections.addAll(stackToSort, toSort);
		final Stack<T> sorted = sortinStack(stackToSort);
		return new ArrayList<>(sorted);
	}

	private Stack<T> sortinStack(final Stack<T> s) {
		if (s.isEmpty()) {
			return s;
		}
		T pivot = s.pop();

		// partition
		Stack<T> left  = new Stack<>();
		Stack<T> right = new Stack<>();
		while(!s.isEmpty()) {
			T y = s.pop();
			if (y.compareTo(pivot) < 0) {
				left.push(y);
			} else {
				right.push(y);
			}
		}
		sortinStack(left);
		sortinStack(right);

		// merge
		Stack<T> tmp = new Stack<>();
		while(!right.isEmpty()) {
			tmp.push(right.pop());
		}
		tmp.push(pivot);
		while(!left.isEmpty()) {
			tmp.push(left.pop());
		}
		while(!tmp.isEmpty()) {
			s.push(tmp.pop());
		}
		return s;
	}
}
