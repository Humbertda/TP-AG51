package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;
import com.humbertdany.tpproject.util.generator.RandomStringGenerator;
import com.humbertdany.tpproject.util.hash.HashMap;
import com.humbertdany.tpproject.util.hash.Keyable;

import java.util.Objects;

final public class TestHash extends ATest<String> {

	private final int nbTest;

	public TestHash(int nbTest){
		this.nbTest = nbTest;
	}

	@Override
	public void launch() {

		/**
		 * Compare the speed on large dimensions
		 * with simple java ArrayList
		 */
		log("\n Compare the speed on large dimensions with simple java ArrayList\n");

		ClassicResultEntry reHashMap = new ClassicResultEntry("HashMap", "The HashMap got the value in");
		ClassicResultEntry reFlatStructure = new ClassicResultEntry("Flat Structure", "The classic Flat Structure got the value in");

		try {
			for (int dimension = 100; dimension <= 1500000; dimension = new Double(dimension * 3).intValue()) {
				for (int currentTestID = 0; currentTestID < nbTest; currentTestID++) {

					log("Running test nb " + currentTestID + " for both structures full, containing " + dimension + " elements");

					// Init structures
					final HashMap<Integer, KeyStorable> largeMap = new HashMap<>(dimension);
					final KeyStorable[] largeList = new KeyStorable[dimension];

					// Fill both list
					final Chrono chrGen = new Chrono();
					chrGen.start();
					for (int i = 0; i < dimension; i++) {
						final String randomized = RandomStringGenerator.gen(2);
						largeMap.put(i, new KeyStorable(i, randomized));
						largeList[i] = new KeyStorable(i, randomized);
					}
					chrGen.stop();

					final ArrayListIntegerGenerator integerGenerator = new ArrayListIntegerGenerator(0, dimension-1);

					// Put the index we are looking
					// (We can be sure there are unique here, since it is longer then 5 letters)
					final Integer idWeAreLooking = integerGenerator.buildObject();
					KeyStorable valueArrayList = new KeyStorable(idWeAreLooking, "Test ArrayList");
					KeyStorable valueHashMap = new KeyStorable(idWeAreLooking, "Test HashMap");
					largeList[idWeAreLooking] = valueArrayList;
					largeMap.put(idWeAreLooking, valueHashMap);

					// Start the test
					final Chrono chrMap = new Chrono();
					chrMap.start();
					Integer sMap = largeMap.searchValue(object -> object.key, valueHashMap);
					if(!Objects.equals(sMap, idWeAreLooking) || !Objects.equals(largeMap.get(sMap).value, valueHashMap.value)){
						throw new Exception("HashMap did not get the correct value");
					}
					chrMap.stop();

					final Chrono chrList = new Chrono();
					chrList.start();
					Integer keySearch = -1;
					for(KeyStorable s : largeList){
						if(s.equals(valueArrayList)){
							keySearch = s.key;
							break;
						}
					}
					chrList.stop();

					if (!keySearch.equals(idWeAreLooking) || !Objects.equals(largeList[keySearch].value, valueArrayList.value)) {
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

	private final static class KeyStorable {

		private final Integer key;
		private final String value;

		KeyStorable(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

	}

}
