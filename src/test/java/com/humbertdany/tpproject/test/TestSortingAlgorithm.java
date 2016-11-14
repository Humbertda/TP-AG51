package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.factory.ArrayFactory;
import com.humbertdany.tpproject.util.generator.ArrayListGenerator;
import com.humbertdany.tpproject.util.sort.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author dhumbert
 */
public class TestSortingAlgorithm<T extends Comparable> extends ATest {
    
    private final ArrayList<ASortingAlgorithm<T>> sortingAlgo;
	private final ArrayListGenerator<T> gen;

    /**
     *
     */
    public TestSortingAlgorithm(final ArrayListGenerator<T> gen){
        this.sortingAlgo = new ArrayList<>();
	    this.gen = gen;
        sortingAlgo.addAll(Arrays.asList( 
            new SortingInsert<>(this.gen),
            new SortingPermutation<>(this.gen),
            new SortingShell<>(this.gen),
            new SortingFusion<>(this.gen),
            new SortingFast<>(this.gen)
        ));
    }
	
	private ArrayList<ResultEntry> startTest(final ArrayList<T> toSort) throws Exception {
		return this.startTest(toSort.toArray(this.gen.buildArray(toSort.size())));
	}
    
    private ArrayList<ResultEntry> startTest(final T[] toSort) throws Exception {
	    final ArrayList<ResultEntry> results = new ArrayList<>();
        for(ASortingAlgorithm<T> i : this.sortingAlgo){
            final Chrono chr = new Chrono();
            chr.start();
            final Collection<T> sort = i.sort(toSort);
            chr.stop();
	        if(i.isSorted(sort)){
		        results.add(new ResultEntry(i, chr.getMilliSec()));
	        } else {
		        throw new Exception("The array was not sorted corretly by the " + i.getAlgorithmName() + " algorithm");
	        }
        }
	    return results;
    }

    final public void launch(){

	    ArrayList<Integer> dimensionToTest = new ArrayList<>();
	    dimensionToTest.addAll(Arrays.asList(1000,2000,3000,5000,10000,20000,50000,100000));

	    for(int dimension : dimensionToTest){
		    try {
			    final ArrayList<T> generated = this.gen.generate(dimension);
			    System.out.println("   > Results for an array of " + dimension + " elements");
			    final ArrayList<ResultEntry> res = this.startTest(generated);
			    for(ResultEntry r : res){
				    //TODO multiple tests and return as excel array
				    r.displayResults();
			    }
			    System.out.println();
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
	    }

    }

	private class ResultEntry {

		private final ASortingAlgorithm algo;
		private final long result;

		ResultEntry(final ASortingAlgorithm a, final long result){
			this.algo = a;
			this.result = result;
		}

		void displayResults(){
			System.out.println(this.algo.getAlgorithmName() + " sorted the array in " + this.result + "ms");
		}


	}
    
}
