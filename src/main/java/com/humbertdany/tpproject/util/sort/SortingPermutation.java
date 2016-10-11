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
public class SortingPermutation<T extends Comparable> extends ASortingAlgorithm<T>{

	public SortingPermutation(final ArrayFactory<T> arrayFactory){
		super(arrayFactory);
	}
	
    @Override
    public Collection<T> sort(T[] toSort) {
        //TODO :: implement this method
		// What I could check : http://codereview.stackexchange.com/questions/59966/sorting-algorithms
        return Arrays.asList(toSort);
    }
    
    @Override 
    public String getAlgorihmName(){
        return "Permutation"; 
    }
    
}
