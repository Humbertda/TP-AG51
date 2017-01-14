package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;
import com.humbertdany.tpproject.util.generator.RandomStringGenerator;
import com.humbertdany.tpproject.util.hash.MyHashMap;

import java.util.HashMap;
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

		ClassicResultEntry reHashMap = new ClassicResultEntry("MyHashMap", "The MyHashMap got the value in");
		ClassicResultEntry inHashMap = new ClassicResultEntry("MyHashMap", "The MyHashMap inserted the values in");

		ClassicResultEntry reFlatStructure = new ClassicResultEntry("Flat Structure", "The classic Flat Structure got the value in");
		ClassicResultEntry inFlatStructure = new ClassicResultEntry("Flat Structure", "The classic array inserted the values in");

		ClassicResultEntry reJavaHashMap = new ClassicResultEntry("Java HashMap", "The Java HashMap got the value in");
		ClassicResultEntry inJavaHashMap = new ClassicResultEntry("Java HashMap", "The Java HashMap inserted the values in");

		try {
			for (int dimension = 100; dimension <= 1500000; dimension = new Double(dimension * 3).intValue()) {
				for (int currentTestID = 0; currentTestID < nbTest; currentTestID++) {

					log("Running test nb " + currentTestID + " for both structures full, containing " + dimension + " elements");
					final Chrono chr = new Chrono();
					final ArrayListIntegerGenerator integerGenerator = new ArrayListIntegerGenerator(0, dimension-1);

					// Init structures
					final MyHashMap<Integer, KeyStorable> largeMap = new MyHashMap<>(dimension);
					final KeyStorable[] largeList = new KeyStorable[dimension];
					final HashMap<Integer, KeyStorable> javaMap = new HashMap<>(dimension);

					// Fill both list
					final String[] genStrs = new String[dimension];
					for (int i = 0; i < dimension; i++) {
						genStrs[i] = RandomStringGenerator.gen(2);
					}

					//   #
					// INSERT
					//   #

					chr.start();
					for (int i = 0; i < dimension; i++) {
						largeMap.put(i, new KeyStorable(i, genStrs[i]));
					}
					chr.stop();
					inHashMap.add(dimension, chr.getMilliSec());

					chr.start();
					for (int i = 0; i < dimension; i++) {
						largeList[i] = new KeyStorable(i, genStrs[i]);
					}
					chr.stop();
					inFlatStructure.add(dimension, chr.getMilliSec());

					chr.start();
					for (int i = 0; i < dimension; i++) {
						javaMap.put(i, new KeyStorable(i, genStrs[i]));
					}
					chr.stop();
					inJavaHashMap.add(dimension, chr.getMilliSec());

					//   #
					// SEARCH
					//   #


					// Put the index we are looking
					// (We can be sure there are unique here, since it is longer then 5 letters)
					final Integer idWeAreLooking = integerGenerator.buildObject();
					KeyStorable valueArrayList = new KeyStorable(idWeAreLooking, "Test ArrayList");
					KeyStorable valueHashMap = new KeyStorable(idWeAreLooking, "Test MyHashMap");
					KeyStorable valueJavaMap = new KeyStorable(idWeAreLooking, "Test Java HashMap");
					largeList[idWeAreLooking] = valueArrayList;
					largeMap.put(idWeAreLooking, valueHashMap);
					javaMap.put(idWeAreLooking, valueJavaMap);

					// Start the test
					chr.start();
					Integer sMap = largeMap.searchValue(object -> object.key, valueHashMap);
					if(!Objects.equals(sMap, idWeAreLooking) || !Objects.equals(largeMap.get(sMap).value, valueHashMap.value)){
						throw new Exception("MyHashMap did not get the correct value");
					}
					chr.stop();
					reHashMap.add(dimension, chr.getMilliSec());

					chr.start();
					Integer keySearch = -1;
					for(KeyStorable s : largeList){
						if(s.equals(valueArrayList)){
							keySearch = s.key;
							break;
						}
					}
					chr.stop();
					reFlatStructure.add(dimension, chr.getMilliSec());
					if (!keySearch.equals(idWeAreLooking) || !Objects.equals(largeList[keySearch].value, valueArrayList.value)) {
						throw new Exception("ArrayList did not get the correct value");
					}

					chr.start();
					Integer keySearchJ = -1;
					for(KeyStorable k : javaMap.values()){
						if(k.value.equals(valueJavaMap.value)){
							keySearchJ = k.key;
							break;
						}
					}
					if(!Objects.equals(keySearchJ, idWeAreLooking) || !Objects.equals(javaMap.get(keySearchJ).value, valueJavaMap.value)){
						throw new Exception("MyHashMap did not get the correct value");
					}
					chr.stop();
					reJavaHashMap.add(dimension, chr.getMilliSec());

				}
			}
		} catch (Exception e){
			log(e.getMessage());
		}

		reHashMap.displayResults();
		inHashMap.displayResults();

		reFlatStructure.displayResults();
		inFlatStructure.displayResults();

		reJavaHashMap.displayResults();
		inJavaHashMap.displayResults();

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
