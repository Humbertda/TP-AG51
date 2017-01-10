package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.RandomStringGenerator;
import com.humbertdany.tpproject.util.hash.HashEntryEmptyException;
import com.humbertdany.tpproject.util.hash.HashMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

final public class TestHash extends ATest<String> {

	private final int nbTest;

	public TestHash(int nbTest){
		this.nbTest = nbTest;
	}

	@Override
	public void launch() {

		/**
		 * Step 1: check if the HashMap is working
		 */
		log("\nCheck if the HashMap is working: \n");

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
		log("\n Compare the speed on large dimensions with simple java ArrayList\n");

		ResultEntry reHashMap = new ResultEntry("HashMap", "The HashMap got the value in ms : ");
		ResultEntry reFlatStructure = new ResultEntry("Flat Structure", "The classic Flat Structure got the value in ms : ");

		try {
			for (Integer dimension : Arrays.asList(100, 10000, 1000000)) {
				for (int currentTestID = 0; currentTestID < nbTest; currentTestID++) {
					final int indexLook = dimension - 10; // defined by hand.

					log("Running test for both structures full, containing " + dimension + " elements");

					// Init structures
					final HashMap<String> largeMap = new HashMap<>(dimension);
					final String[] largeList = new String[dimension];

					// Fill both list
					final Chrono chrGen = new Chrono();
					chrGen.start();
					for (int i = 0; i < dimension; i++) {
						final String randomized = RandomStringGenerator.gen(5);
						largeMap.put(i, randomized);
						largeList[i] = randomized;
					}
					chrGen.stop();

					log("The map and the list have been randomly generated in " + chrGen.getMilliSec() + " milli-seconds");

					// Put the index we are looking
					String valueArrayList = "Test ArrayList";
					String valueHashMap = "Test HashMap";
					largeList[indexLook] = valueArrayList;
					largeMap.put(indexLook, valueHashMap);

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

					if (!sMap.equals(valueHashMap)) {
						throw new Exception("HashMap did not get the correct value");
					}

					final Chrono chrList = new Chrono();
					chrList.start();
					String sList = largeList[indexLook];
					chrList.stop();

					if (!sList.equals(valueArrayList)) {
						throw new Exception("ArrayList did not get the correct value");
					}

					// Display tests
					reHashMap.add(dimension, chrMap.getMilliSec());
					reFlatStructure.add(dimension, chrList.getMilliSec());

					// NB: impossible de tester sur des structure Java sur des grandes dimensions
				}
			}
		} catch (Exception e){
			log(e.getMessage());
		}

		reHashMap.displayResults();
		reFlatStructure.displayResults();

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
