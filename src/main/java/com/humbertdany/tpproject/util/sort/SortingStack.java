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
public class SortingStack<T extends Comparable> extends ASortingAlgorithm<T>{

	public SortingStack(final ArrayFactory<T> arrayFactory){
		super(arrayFactory);
	}
	
    @Override
    public Collection<T> sort(T[] toSort) {
        //TODO :: implement this method
        return Arrays.asList(toSort);
    }
    
    @Override 
    public String getAlgorihmName(){
        return "Stack"; 
    }
    
}
