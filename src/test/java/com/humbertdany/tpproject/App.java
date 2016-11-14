package com.humbertdany.tpproject;

import com.humbertdany.tpproject.test.TestBinaryStack;
import com.humbertdany.tpproject.test.TestSortingAlgorithm;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main( String[] args ){
		tp1();
    }
	
	private static void tp1(){
        final TestSortingAlgorithm<Integer> algoTest = new TestSortingAlgorithm<>(new ArrayListIntegerGenerator());
		algoTest.launch();
	}
	
	private static void tp2(){
		final TestBinaryStack test = new TestBinaryStack();
		test.launch();
	}
	
}
