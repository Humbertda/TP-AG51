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
		
		System.out.println(l1.contains(new N(50)));
		System.out.println(l1.contains(new N(4)));
		System.out.println(l1.contains(new N(55)));
		
		l1.printInOrder();
		l1.printPostOrder();

		//TODO Very Important : fix Mitsu class

		// Test v2
		final TasBinaire<String> h = new TasBinaire<>(new ArrayFactory<String>() {
			@Override
			public String[] buildArray(int dimension) {
				return new String[dimension];
			}
		});

		h.insert("p");
		h.insert("r");
		h.insert("i");
		h.insert("o");
		System.out.println(h);
		h.deleteMin();
		System.out.println(h);

		TasBinaire<Integer> tmp = new TasBinaire<>(new ArrayFactory<Integer>() {
			@Override
			public Integer[] buildArray(int dimension) {
				return new Integer[dimension];
			}
		});
		Integer[] a = {4, 7, 7, 7, 5, 0, 2, 3, 5, 1};
		tmp.heapSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	private class N extends Node<Integer> {
		N(final Integer n){
			super(n);
		}
	}
	
}

