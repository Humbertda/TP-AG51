package com.humbertdany.tpproject.util.sort;

import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public interface ISortingAlgorithm<T>{
    abstract public String getAlgorithmName();
    abstract public Collection<T> sort(final T[] toSort);
    
}
