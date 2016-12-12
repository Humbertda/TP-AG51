/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.binarystack.v1.BinaryStack;
import com.humbertdany.tpproject.util.binarystack.v1.Node;
import com.humbertdany.tpproject.util.binarystack.v2.TasBinaire;
import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.util.Arrays;

/**
 *
 * @author dhumbert
 */
public class TestBinaryStack extends ATest {
	
	public TestBinaryStack(){
		
	}

	final private ArrayFactory<N> nArrayFactory = new ArrayFactory<N>() {
		@Override
		public N[] buildArray(int dimension) {
			return new N[dimension];
		}
	};


	
	final public void launch(){

		// Test v1
		final N root = new N(1);
		final N n1 = new N(50);
		final N n2 = new N(4);
		final N n3 = new N(2);
		final N n4 = new N(5);

		final BinaryStack<N> l1 = new BinaryStack<>(nArrayFactory, root);
		l1.add(root, n1, BinaryStack.ORIENTATION_LEFT);
		l1.add(root, n2, BinaryStack.ORIENTATION_RIGHT);
		l1.add(n2, n3, BinaryStack.ORIENTATION_LEFT);
		l1.add(n2, n4, BinaryStack.ORIENTATION_RIGHT);
		
		log(l1.contains(new N(50)));
		log(l1.contains(new N(4)));
		log(l1.contains(new N(55)));
		
		l1.printInOrder();
		l1.printPostOrder();

		// Test v2
		final TasBinaire<String> h = new TasBinaire<>(ArrayFactory.buildStringFactory());

		h.insertAll("p", "r", "i", "a", "o");
		h.insert("r");

		log("Binary Stack stocked : \n " + h);
		h.deleteMin();
		log("Binary Stack state after deletion of min : \n " + h);

		final Integer[] a = {4, 7, 7, 7, 5, 0, 2, 3, 5, 1};
		log("Array given to sort \n " + descArray(a));
		TasBinaire<Integer> tmp = new TasBinaire<>(ArrayFactory.buildIntegerFactory());
		tmp.heapSort(a);
		log("The last array sorted : \n " + descArray(a));
	}
	
	private class N extends Node<Integer> {
		N(final Integer n){
			super(n);
		}
	}

	private String descArray(final Integer[] array){
		return Arrays.toString(array);
	}
	
}

