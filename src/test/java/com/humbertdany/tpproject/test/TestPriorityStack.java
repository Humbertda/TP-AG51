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
		String insertionStr = "l'insertion a été faite en ms";
		String extractStr = "le ExtractMin de l'élément max a été faite en ms";
		String getStr = "le getMin de l'élément max a été faite en ms";
		final ResultEntry linearResultsInsert = new ResultEntry("Linear", insertionStr);
		final ResultEntry stackResultInsert = new ResultEntry("Tas Binaire", insertionStr);
		final ResultEntry linearResultExtractMin = new ResultEntry("Linear", extractStr);
		final ResultEntry stackResultExtractMin = new ResultEntry("Tas Binaire", extractStr);
		final ResultEntry linearResultGetMin = new ResultEntry("Linear", getStr);
		final ResultEntry stackResultGetMin = new ResultEntry("Tas Binaire", getStr);


		try {
			for (Integer d : dimensionToTest) {
				log("Running dimension : " + d);
				for (int nTest = 0; nTest < numberOfTest; nTest++) {

					// Generate a random array of dimension 'd'
					final List<Integer> generatedArray = gen.generate(d);

					// Init the chrono to record the execution time
					final Chrono chr = new Chrono();

					// Init the structure to test
					final PriorityStack pStack = new PriorityStack();
					final Integer[] linearArray = new Integer[d];

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

					// get min test

					chr.start();
					final StackNode stackNode = pStack.getMin();
					chr.stop();
					stackResultGetMin.add(d, chr.getMilliSec());

					chr.start();
					final Integer integer = getMin(linearArray);
					chr.stop();
					linearResultGetMin.add(d, chr.getMilliSec());

					// Make sure both elements returned are the same
					if (!Objects.equals(stackNode.getKey(), integer)) {
						throw new Exception("Object not equals " + stackNode.getKey() + " and " + integer);
					}

					// extract min test

					chr.start();
					final StackNode stackNodeExtracted = pStack.extractMin();
					chr.stop();
					stackResultExtractMin.add(d, chr.getMilliSec());

					chr.start();
					final Integer extractedMin = extractMin(linearArray);
					chr.stop();
					linearResultExtractMin.add(d, chr.getMilliSec());

					// Make sure both elements returned are the same
					if (!Objects.equals(stackNodeExtracted.getKey(), extractedMin)) {
						throw new Exception("Object not equals " + stackNodeExtracted.getKey() + " and " + extractedMin);
					}


				}
			}

			linearResultsInsert.displayResults();
			stackResultInsert.displayResults();
			stackResultExtractMin.displayResults();
			linearResultExtractMin.displayResults();
			stackResultGetMin.displayResults();
			linearResultGetMin.displayResults()
			;
		} catch (Exception e){
			log(e.getMessage());
		}
	}

	private Integer getMin(Integer[] linearArray) {
		Integer min = linearArray[0];
		for(int i = 1; i < linearArray.length; i++){
			if(linearArray[i] < min) {
				min = linearArray[i];
			}
		}
		return min;
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
