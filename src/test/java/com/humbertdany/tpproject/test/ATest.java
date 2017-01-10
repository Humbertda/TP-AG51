package com.humbertdany.tpproject.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The ATest class
 * Every TP launcher extends this class
 * it give important methods to
 */
abstract public class ATest<T>{

	abstract public void launch();

	public static void separeLine(){
		System.out.println();
		System.out.println("#----------------------------------#");
		System.out.println();
	}

	protected final void separe(){
		ATest.separeLine();
	}

	protected final void log(final Object o){
		System.out.println(o.toString());
	}

	abstract protected class AResultEntry<Type> {

		private final Type elem;

		private final TreeMap<Integer, List<Long>> result = new TreeMap<>();

		AResultEntry(final Type a){
			this.elem = a;
		}

		void add(int arrayDimension, long results){
			if(!result.containsKey(arrayDimension)){
				result.put(arrayDimension, new ArrayList<>());
			}
			this.result.get(arrayDimension).add(results);
		}

		abstract void displayResults();

		long getAverageExecutionTime(final List<Long> results){
			int numberOfRes = result.size();
			long totalDuration = 0;
			for(long l : results){
				totalDuration += l;
			}
			return totalDuration/numberOfRes;
		}

		final public TreeMap<Integer, List<Long>> getResult() {
			return result;
		}

		final Type getElem() {
			return elem;
		}
	}

	protected final class ClassicResultEntry extends AResultEntry<String>{

		private final String info;

		ClassicResultEntry(String a, String info) {
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
