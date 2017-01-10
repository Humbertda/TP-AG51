package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;
import com.humbertdany.tpproject.util.prioritystack.PriorityStack;
import com.humbertdany.tpproject.util.prioritystack.StackNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class TestPriorityStack extends ATest<Integer> {

	private final int numberOfTest;

	public TestPriorityStack(int numberOfTest){
		this.numberOfTest = numberOfTest;
	}

	/**
	 * TODO:: implements this
	 * @implNote : we will extract the min
	 * since our BinaryTree is extracting the min element
	 */
	@Override
	public void launch() {
		final ArrayListIntegerGenerator gen = new ArrayListIntegerGenerator();
		final ArrayList<Integer> dimensionToTest = new ArrayList<>();
		dimensionToTest.add(10000);
		for(int i = 15000; i <= 200000; i+=10000){
			dimensionToTest.add(i);
		}

		// Result entry
		final ResultEntry linearResultsInsert = new ResultEntry("Linear", "l'insertion a été faite en ms");
		final ResultEntry stackResultInsert = new ResultEntry("Tas Binaire", "l'insertion a été faite en ms");
		final ResultEntry linearResultExtractMin = new ResultEntry("Linear", "let ExtractMin de l'élément max a été faite en ms");
		final ResultEntry stackResultExtractMin = new ResultEntry("Tas Binaire", "let ExtractMin de l'élément max a été faite en ms");
		try {
			for (Integer d : dimensionToTest) {
				log("Running dimension : " + d);
				for (int nTest = 0; nTest < numberOfTest; nTest++) {
					// Generate a random array
					final List<Integer> generatedArray = gen.generate(d);
					// To test
					final PriorityStack pStack = new PriorityStack();
					final Integer[] linearArray = new Integer[d];
					final Chrono chr = new Chrono();

					// Insertion Test

					chr.start();
					for (Integer i : generatedArray) {
						pStack.insert(new StackNode(i));
					}
					chr.stop();
					stackResultInsert.add(d, chr.getMilliSec());

					chr.start();
					int counter = 0;
					for (Integer i : generatedArray) {
						linearArray[counter] = i;
						counter++;
					}
					chr.stop();
					linearResultsInsert.add(d, chr.getMilliSec());

					// Get max Test

					chr.start();
					final StackNode stackNode = pStack.extractMin();
					chr.stop();
					stackResultExtractMin.add(d, chr.getMilliSec());

					chr.start();
					final Integer integer = extractMin(linearArray);
					chr.stop();
					linearResultExtractMin.add(d, chr.getMilliSec());

					if (!Objects.equals(stackNode.getKey(), integer)) {
						throw new Exception("Object not equals " + stackNode.getKey() + " and " + integer);
					}

				}
			}

			linearResultsInsert.displayResults();
			stackResultInsert.displayResults();
			stackResultExtractMin.displayResults();
			linearResultExtractMin.displayResults();
		} catch (Exception e){
			log(e.getMessage());
		}
	}
	
	private Integer extractMin(Integer[] linearArray) {
		int id = 0;
		Integer min = linearArray[id];
		for(int i = 1; i < linearArray.length; i++){
			if(linearArray[i] < min) {
				min = linearArray[i];
				id = i;
			}
		}
		Integer[] newArray = new Integer[linearArray.length-1];
		int counter = 0;
		for(Integer i : linearArray){
			if(counter != id){
				newArray[counter] = i;
				counter++;
			}
		}
		linearArray = newArray;
		return min;
	}
	
	private final class ResultEntry extends AResultEntry<String>{

		private final String info;

		ResultEntry(String a, String info) {
			super(a);
			this.info = info;
		}

		@Override
		void displayResults() {
			final StringBuilder sb = new StringBuilder();
			sb.append("Here is the results for ").append(getElem()).append(" algorithm : \n");
			for(Map.Entry<Integer, List<Long>> entry : getResult().entrySet()){
				sb.append("   ").append("For a dimension of ").append(entry.getKey()).append(", ").append(info).append(" ms :   ")
						.append(this.getAverageExecutionTime(entry.getValue())).append("ms\n")
				;
			}
			log(sb);
		}

	}

}
