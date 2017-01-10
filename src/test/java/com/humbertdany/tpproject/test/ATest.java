package com.humbertdany.tpproject.test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The ATest class
 * Every TP launcher extends this class
 * it give important methods to
 */
abstract public class ATest<T extends Comparable<T>>{

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

}
