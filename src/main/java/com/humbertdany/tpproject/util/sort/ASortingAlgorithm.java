/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import com.humbertdany.tpproject.util.factory.ArrayFactory;

/**
 *
 * @author dhumbert
 */
public abstract class ASortingAlgorithm<T> implements ISortingAlgorithm<T>{
    
    private ArrayFactory<T> arrayFactory;
    
    public ASortingAlgorithm(ArrayFactory<T> arrayFactory){
        this.arrayFactory = arrayFactory;
    }
    
    public ArrayFactory<T> getArrayFactory(){
        return this.arrayFactory; 
    }
    
}
