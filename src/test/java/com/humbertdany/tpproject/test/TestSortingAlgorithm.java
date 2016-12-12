package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.factory.ArrayFactory;
import com.humbertdany.tpproject.util.generator.ArrayListGenerator;
import com.humbertdany.tpproject.util.sort.*;

import java.util.*;

/**
 *
 * @author dhumbert
 */
public class TestSortingAlgorithm<T extends Comparable> extends ATest {
    
    private final ArrayList<ASortingAlgorithm<T>> sortingAlgo;
	private final ArrayListGenerator<T> gen;

	public final int NUMBER_OF_TEST = 20;

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

    final public void launch(){

	    ArrayList<Integer> dimensionToTest = new ArrayList<>();
	    dimensionToTest.addAll(Arrays.asList(1000,2000,3000,5000,10000,20000,50000,100000));

	    final Map<Integer, ResultEntry> results = new HashMap<>();
	    for(int i = 0; i < this.sortingAlgo.size(); i++){
		    results.put(i, new ResultEntry(this.sortingAlgo.get(i)));
	    }

	    for(int i = 0 ; i < NUMBER_OF_TEST; i++){
		    log("Running iteration " + (i+1) + " of " + NUMBER_OF_TEST);
		    for(int dimension : dimensionToTest){
			    log("  Testing for an array of dimension " + dimension);
			    try {
				    final ArrayList<T> generated = this.gen.generate(dimension);
				    final T[] ts = generated.toArray(this.gen.buildArray(generated.size()));
				    for(int j = 0; j < this.sortingAlgo.size(); j++){
					    final ResultEntry currentAlgo = results.get(j);
					    final ASortingAlgorithm<T> algo = currentAlgo.getAlgo();
					    log("      Running test for algorithm " + algo.getAlgorithmName());
					    final Chrono chr = new Chrono();
					    chr.start();
					    final Collection<T> sort = algo.sort(ts);
					    chr.stop();
					    if(algo.isSorted(sort)){
						    currentAlgo.add(chr.getMilliSec());
					    } else {
						    throw new Exception("The array was not sorted corretly by the " + algo.getAlgorithmName() + " algorithm");
					    }
				    }
				    log("");
			    } catch (Exception e) {
				    e.printStackTrace();
			    }
		    }
	    }

	    for(final ResultEntry r : results.values()){
		    r.displayResults();
	    }

    }

	private class ResultEntry {

		private final ASortingAlgorithm<T> algo;

		//TODO Separate dimensions results
		private final List<Long> result = new ArrayList<>();

		ResultEntry(final ASortingAlgorithm<T> a){
			this.algo = a;
		}

		void add(long r){
			this.result.add(r);
		}

		void displayResults(){
			log(this.algo.getAlgorithmName() + " sorted the array in " + this.getAverageExecutionTime() + "ms");
		}

		long getAverageExecutionTime(){
			int numberOfRes = result.size();
			long totalDuration = 0;
			for(long l : result){
				totalDuration += l;
			}
			return totalDuration/numberOfRes;
		}


		ASortingAlgorithm<T> getAlgo() {
			return algo;
		}
	}
    
}
