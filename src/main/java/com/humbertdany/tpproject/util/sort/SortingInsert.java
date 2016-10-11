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
    public Collection<T> sort(final T[] A) {
        // a negative integer, zero, or a positive integer as this object 
        // is less than, equal to, or greater than the specified object.
        for(int j = 1; j < A.length ; j++) {
            final T key = A[j];
            int i = j-1;
            while(i > 0 && A[i].compareTo(key) > 0){
                A[i+1] = A[i]; 
                i--;
            }
            A[i+1] = key;
        }
        return Arrays.asList(A);
      }
    
    @Override 
    public String getAlgorihmName(){
        return "Insert"; 
    }
    
}
