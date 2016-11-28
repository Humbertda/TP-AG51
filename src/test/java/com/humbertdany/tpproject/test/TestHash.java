package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.RandomStringGenerator;
import com.humbertdany.tpproject.util.hash.HashEntryEmptyException;
import com.humbertdany.tpproject.util.hash.HashMap;

import java.util.Arrays;

final public class TestHash extends ATest {

	@Override
	public void launch() {

		/**
		 * Step 1: check if the HashMap is working
		 */

		final HashMap<String> map = new HashMap<>(50);
		map.put(4, "Salut");
		map.put(7, "Comment ça va?");
		map.put(40, RandomStringGenerator.gen(30));
		map.put(22, RandomStringGenerator.gen(100));
		map.readAll();

		/**
		 * Step 2: Compare the speed on large dimensions
		 * with simple java ArrayList
		 */

		for(Integer i : Arrays.asList(100, 10000, 1000000)) {
			this.separe();
			this.launchPerformanceTest(i);
		}

	}

	private void launchPerformanceTest(final int dimension){

		final int indexLook = dimension - 10; // defined by hand.

		// Init structures
		final HashMap<String> largeMap = new HashMap<>(dimension);
		final String[] largeList = new String[dimension];

		// Fill both list
		for(int i = 0; i < dimension; i++){
			final String randomized = RandomStringGenerator.gen(5);
			largeMap.put(i, randomized);
			largeList[i] = randomized;
		}

		// Put the index we are looking
		largeList[indexLook] = "Test ArrayList";
		largeMap.put(indexLook, "Test HashMap");

		// Start the test
		final Chrono chrMap = new Chrono();
		chrMap.start();
		String sMap;
		try {
			sMap = largeMap.get(indexLook);
		} catch (HashEntryEmptyException e) {
			// this should not happen since we just settled it
			e.printStackTrace();
			sMap = "";
		}
		chrMap.stop();

		final Chrono chrList = new Chrono();
		chrList.start();
		String sList = largeList[indexLook];
		chrList.stop();

		// Display tests
		log("Running test for both structures full, containing " + dimension + " elements");
		log("The ArrayList get the value " + sList + " in " + chrList.getMilliSec() + " milli-seconds");
		log("The HashMap get the value " + sMap + " in " + chrMap.getMilliSec() + " milli-seconds");

		// NB: impossible de tester sur des structure Java sur des grandes dimensions
	}

	private void log(final String s){
		System.out.println(s);
	}

}
