/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public class SortingInsert<T extends Comparable> extends ASortingAlgorithm<T> {

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
