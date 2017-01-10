package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * The Bubble Sort
 * @implNote Tri par Permutation
 * @author Dany
 * @param <T>
 */
public class SortingPermutation<T extends Comparable<T>> extends ASortingAlgorithm<T> {

	public SortingPermutation(ArrayFactory<T> arrayFactory) {
		super(arrayFactory);
	}

	@Override
	public String getAlgorithmName() {
		return "Permutation";
	}

	@Override
	public Collection<T> sort(T[] toSort) {
		final T[] sorted = toSort;
		permutationSort(sorted);
		return Arrays.asList(sorted);
	}

	private void permutationSort(T[] toSort) {
		// Début
		for (int i = 1; i <= toSort.length-1; i += 1 ) {
			int j = i;
			T val = toSort[i];
			while ( j > 0 && toSort[j-1].compareTo(val) > 0 ) {
				toSort[j] = toSort[j-1];
				j = j-1;
			} // Fin while
			toSort[j] = val ; // pas de test, ici plus lourd que l’affectation
		} // Fin for
	}

}
