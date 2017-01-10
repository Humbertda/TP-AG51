package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.ArrayListGenerator;
import com.humbertdany.tpproject.util.sort.*;

import java.util.*;

/**
 *
 * @author dhumbert
 */
public class TestSortingAlgorithm<T extends Comparable<T>> extends ATest<T> {
    
    private final ArrayList<ASortingAlgorithm<T>> sortingAlgo;
	private final ArrayListGenerator<T> gen;

	private final int numberOfTotalTest;

    /**
     *
     */
    public TestSortingAlgorithm(final ArrayListGenerator<T> gen, final int numberOfTotalTest){
	    this.numberOfTotalTest = numberOfTotalTest;
        this.sortingAlgo = new ArrayList<>();
	    this.gen = gen;
        sortingAlgo.addAll(Arrays.asList( 
	            new SortingInsert<>(this.gen),
		        new SortingSelect<>(this.gen),
	            new SortingPermutation<>(this.gen),
		        new SortingFast<>(this.gen),
	            new SortingShell<>(this.gen),
	            new SortingFusion<>(this.gen),
		        new SortingStack<>(this.gen)
        ));
    }

    final public void launch(){

	    ArrayList<Integer> dimensionToTest = new ArrayList<>();

	    dimensionToTest.add(1000);

	    for(int i = 5000; i < 100000; i+=5000){
		    dimensionToTest.add(i);
	    }

	    final Map<Integer, ResultEntry> results = new HashMap<>();
	    for(int i = 0; i < this.sortingAlgo.size(); i++){
		    results.put(i, new ResultEntry(this.sortingAlgo.get(i)));
	    }

	    for(int i = 0 ; i < numberOfTotalTest; i++){
		    log("Running iteration " + (i+1) + " of " + numberOfTotalTest);
		    for(int dimension : dimensionToTest){
			    log("  Testing for an array of dimension " + dimension);
			    try {
				    for(int j = 0; j < this.sortingAlgo.size(); j++){
					    final List<T> generated = this.gen.generate(dimension);
					    final T[] ts = generated.toArray(this.gen.buildArray(generated.size()));
					    final ResultEntry currentAlgo = results.get(j);
					    final ASortingAlgorithm<T> algo = currentAlgo.getElem();
					    log("      Running test for algorithm " + algo.getAlgorithmName());
					    final Chrono chr = new Chrono();
					    chr.start();
					    final Collection<T> sort = algo.sort(ts);
					    chr.stop();
					    if(algo.isSorted(sort)){
						    currentAlgo.add(dimension, chr.getMilliSec());
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

	    log("\nDisplaying results:\n");

	    for(final ResultEntry r : results.values()){
		    r.displayResults();
	    }

    }

	private class ResultEntry extends AResultEntry<ASortingAlgorithm<T>> {

		ResultEntry(ASortingAlgorithm<T> a) {
			super(a);
		}

		void displayResults(){
			final StringBuilder sb = new StringBuilder();
			sb.append("Here is the results for the ").append(getElem().getAlgorithmName()).append(" algorithm : \n");
			for(Map.Entry<Integer, List<Long>> entry : getResult().entrySet()){
				sb      .append("   ").append("For a dimension of ").append(entry.getKey())
						.append(", the algorithm sorted the array in average in ms :   ")
						.append(this.getAverageExecutionTime(entry.getValue())).append("ms\n")
				;
			}
			log(sb);
		}
	}
    
}
