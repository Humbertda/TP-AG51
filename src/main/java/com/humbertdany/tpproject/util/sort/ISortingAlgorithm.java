package com.humbertdany.tpproject.util.sort;

import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public interface ISortingAlgorithm<T>{
    String getAlgorithmName();
    Collection<T> sort(final T[] toSort);
}
