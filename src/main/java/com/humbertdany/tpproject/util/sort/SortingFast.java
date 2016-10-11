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
public class SortingFast<T extends Comparable> extends ASortingAlgorithm<T>{

	private T array[];
    private int length;
	
	public SortingFast(final ArrayFactory<T> arrayFactory){
		super(arrayFactory);
	}
	
    @Override
    public Collection<T> sort(T[] toSort) {
		this.array = toSort;
        length = toSort.length;
        quickSort(0, length - 1);
        return Arrays.asList(this.array);
    }
	
	private void quickSort(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        T pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (array[i].compareTo(pivot) < 0) {
                i++;
            }
            while (array[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(final int i, final int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @Override 
    public String getAlgorihmName(){
        return "FAST"; 
    }
}
