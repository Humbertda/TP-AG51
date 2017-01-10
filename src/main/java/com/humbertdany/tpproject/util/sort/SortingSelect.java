package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * @implNote Tri par Selection
 * @author Dany
 * @param <T>
 */
public class SortingSelect<T extends Comparable<T>> extends ASortingAlgorithm<T> {

	public SortingSelect(ArrayFactory<T> arrayFactory) {
		super(arrayFactory);
	}

	@Override
	public String getAlgorithmName() {
		return "Selection";
	}

	@Override
	public Collection<T> sort(final T[] a) {
		selectionSort(a);
		return Arrays.asList(a);
	}

	private void selectionSort(final T[] arr) {
		// Début
		int indMin;
		int i, j;
		int N = arr.length;
		for ( i = 0; i <= N-2; i += 1 ) {
			indMin = i;
			for ( j = i+1; j <= N-1; j += 1 ) {
				if (arr[j].compareTo(arr[indMin]) < 0 ) {
					indMin = j;
				} // Fin if
			} // Fin for
			if ( indMin != i ) {
				swap(arr, i, indMin) ; // vu précédemment
			} // Fin if
		} // Fin for
		// Fin
	}
}
