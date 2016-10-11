/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.factory.ArrayFactory;
import com.humbertdany.tpproject.util.sort.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public class TestSortingAlgorithm<T extends Comparable> {
    
    private final ArrayList<ISortingAlgorithm<T>> sortingAlgo;
    private final ArrayFactory<T> factory;

    /**
     *
     */
    public TestSortingAlgorithm(final ArrayFactory<T> factory){
        this.sortingAlgo = new ArrayList<>();
        this.factory = factory;
        sortingAlgo.addAll(Arrays.asList( 
            new SortingInsert<>(this.factory),
            new SortingPermutation<>(this.factory),
            new SortingShell<>(this.factory),
            new SortingFusion<>(this.factory),
            new SortingFast<>(this.factory),
            new SortingStack<>(this.factory)
        ));
    }
	
	public void launch(final ArrayList<T> toSort){
		this.launch(toSort.toArray(this.factory.buildArray(toSort.size())));
	}
    
    public void launch(final T[] toSort){
        for(ISortingAlgorithm<T> i : this.sortingAlgo){
            final Chrono chr = new Chrono();
            chr.start();
            final Collection<T> sort = i.sort(toSort);
            chr.stop();
            System.out.println(i.getAlgorihmName() + " sorted the array in " + chr.getMilliSec() + "ms");
        }
    }
    
}
