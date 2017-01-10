package com.humbertdany.tpproject;

import com.humbertdany.tpproject.test.*;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;

/**
 * The TP launcher class, used to launch all the cases
 * with string arguments
 * (Check run configuration for more+)
 */
public class App {
    
    public static void main( String[] args ){
	    for (final String s : args) {
		    try {
			    launch(s);
			    ATest.separeLine();
		    } catch (CaseApplicationNotFoundException e) {
			    e.printStackTrace();
		    }
	    }
    }

	/**
	 * Launch an AG51 TP test based on the number given as string
	 * @param s : the TP number (e.g: 1)
	 * @throws CaseApplicationNotFoundException
	 */
	private static void launch(final String s) throws CaseApplicationNotFoundException {
		switch (s) {
			case "1":
				tp1();
				break;
			case "2":
				tp2();
				break;
			case "3":
				tp3();
				break;
			case "4":
				tp4();
				break;
			default:
				throw new CaseApplicationNotFoundException("The TP n°" + s + " could not be found.");
		}
	}
	
	private static void tp1(){
        final TestSortingAlgorithm<Integer> algoTest = new TestSortingAlgorithm<>(new ArrayListIntegerGenerator(), 25);
		algoTest.launch();
	}
	
	private static void tp2(){
		final TestPriorityStack test = new TestPriorityStack(50);
		test.launch();
	}

	private static void tp3(){
		final TestHash test = new TestHash(25);
		test.launch();
	}

	private static void tp4(){
		final GraphTest test = new GraphTest(50, 500);
		test.launch();
	}

	private static final class CaseApplicationNotFoundException extends Exception {
		CaseApplicationNotFoundException(final String s){
			super(s);
		}
	}
	
}
