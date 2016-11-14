/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public abstract class ASortingAlgorithm<T extends Comparable> implements ISortingAlgorithm<T>{
    
    private ArrayFactory<T> arrayFactory;
    
    public ASortingAlgorithm(ArrayFactory<T> arrayFactory){
        this.arrayFactory = arrayFactory;
    }
    
    public ArrayFactory<T> getArrayFactory(){
        return this.arrayFactory; 
    }

    final void swap(final T[] arr, final int i, final int j) {
        final T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	final public boolean isSorted(final T[] a) {
		for(int i=1; i < a.length; i++)
			if(a[i-1].compareTo(a[i]) > 0)
				return false;
		return true;
	}

	final public boolean isSorted(final Collection<T> a) {
		final T[] tmp = this.arrayFactory.buildArray(a.size());
		for(int i=0; i < a.size(); i++) {
			tmp[i] = a.iterator().next();
		}
		return this.isSorted(tmp);
	}

}
