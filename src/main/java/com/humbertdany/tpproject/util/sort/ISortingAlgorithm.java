package com.humbertdany.tpproject.util.sort;

import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public interface ISortingAlgorithm<T>{
    String getAlgorithmName();
    Collection<T> sort(final T[] toSort);

	/**
	 * TODO @Dany
     * Implement the following:
	 * from https://moodle.utbm.fr/pluginfile.php/111607/mod_resource/content/1/AG51_TP1_2014.pdf
	 *
	 *  Insertion
	 *  Permutation
	 *  Stack
     */

}
