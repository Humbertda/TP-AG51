/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public class SortingShell<T extends Comparable> extends ASortingAlgorithm<T> {

	public SortingShell(final ArrayFactory<T> arrayFactory) {
		super(arrayFactory);
	}

	@Override
	public Collection<T> sort(T[] toSort) {
		final T[] sorted = toSort;
		shellSort(sorted);
		return Arrays.asList(sorted);
	}

	private void shellSort(T[] a) {
		int j;
		for (int gap = a.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < a.length; i++) {
				T tmp = a[ i];
				for (j = i; j >= gap && tmp.compareTo(a[ j - gap]) < 0; j -= gap) {
					a[ j] = a[ j - gap];
				}
				a[ j] = tmp;
			}
		}
	}

	@Override
	public String getAlgorithmName() {
		return "Shell";
	}
}
