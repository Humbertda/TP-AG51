package com.humbertdany.tpproject;

import com.humbertdany.tpproject.test.TestBinaryTree;
import com.humbertdany.tpproject.test.TestSortingAlgorithm;
import com.humbertdany.tpproject.util.factory.ArrayFactory;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;
import com.humbertdany.tpproject.util.prioritystack.PriorityStack;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main( String[] args ){
		tp2();
    }
	
	private static void tp1(){
		final ArrayListIntegerGenerator gen = new ArrayListIntegerGenerator();
        final TestSortingAlgorithm<Integer> algoTest = new TestSortingAlgorithm<>(new ArrayFactory<Integer>() {
			@Override
			public Integer[] buildArray(int dimension) {
				return new Integer[dimension];
			}
		});
		
		algoTest.launch(gen.generate(35050));
	}
	
	private static void tp2(){
		//final TestBinaryTree test = new TestBinaryTree();
		//test.launch();
		
		final PriorityStack q = new PriorityStack();
		q.insert(50, 5);
		System.out.println(q.extractMax()); 
	}
	
}
