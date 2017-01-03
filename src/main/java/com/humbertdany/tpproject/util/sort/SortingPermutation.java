/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dhumbert
 */
public class SortingPermutation<T extends Comparable> extends ASortingAlgorithm<T>{

	public SortingPermutation(final ArrayFactory<T> arrayFactory){
		super(arrayFactory);
	}
	
    @Override
    public Collection<T> sort(T[] toSort) {
	    bogoSort(toSort);
        return Arrays.asList(toSort);
    }


	/**
	 * Sorts array a[0..n-1] using Bogo sort
	 * @param a
	 */
	private void bogoSort(T[] a)
	{
		// if array is not sorted then shuffle the
		// array again
		while (!isSorted(a)) {
			shuffle(a);
		}
	}
    
    @Override 
    public String getAlgorithmName(){
        return "Permutation"; 
    }
    
}
