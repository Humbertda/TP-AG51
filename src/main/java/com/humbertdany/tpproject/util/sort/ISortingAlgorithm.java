/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.sort;

import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public interface ISortingAlgorithm<T>{
    abstract public String getAlgorihmName();
    abstract public Collection<T> sort(final T[] toSort);
    
}
