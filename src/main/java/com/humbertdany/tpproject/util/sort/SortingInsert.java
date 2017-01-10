package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * @implNote Tri par Insertion
 * @author dhumbert
 */
public class SortingInsert<T extends Comparable<T>> extends ASortingAlgorithm<T> {

	public SortingInsert(final ArrayFactory<T> arrayFactory){
		super(arrayFactory);
	}

	@Override
	public Collection<T> sort(final T[] a) {
		insertionSort(a);
		return Arrays.asList(a);
	}

	private void insertionSort(final T[] arr) {
		int i, j;
		T newValue;
		for (i = 1; i < arr.length; i++) {
			newValue = arr[i];
			j = i;
			while (j > 0 && arr[j - 1].compareTo(newValue) > 0) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = newValue;
		}
	}


	@Override
	public String getAlgorithmName(){
		return "Insert";
	}

}
