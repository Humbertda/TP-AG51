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

	final private ArrayFactory<Ni> nArrayFactory = new ArrayFactory<Ni>() {
		@Override
		public Ni[] buildArray(int dimension) {
			return new Ni[dimension];
		}
	};
	
	final public void launch(){

		// Test v1
		final Ni root = new Ni(1);
		final Ni n1 = new Ni(50);
		final Ni n2 = new Ni(4);
		final Ni n3 = new Ni(2);
		final Ni n4 = new Ni(5);

		final BinaryStack<Ni> l1 = new BinaryStack<>(nArrayFactory, root);
		l1.add(root, n1, BinaryStack.ORIENTATION_LEFT);
		l1.add(root, n2, BinaryStack.ORIENTATION_RIGHT);
		l1.add(n2, n3, BinaryStack.ORIENTATION_LEFT);
		l1.add(n2, n4, BinaryStack.ORIENTATION_RIGHT);
		
		log(l1.contains(new Ni(50)));
		log(l1.contains(new Ni(4)));
		log(l1.contains(new Ni(55)));
		
		l1.printInOrder();
		l1.printPostOrder();

		// Test v2
		final TasBinaire<N> h = new TasBinaire<>(new ArrayFactory<N>() {
			@Override
			public N[] buildArray(int dimension) {
				return new N[dimension];
			}
		});

		h.insertAll(new N("p"), new N("r"), new N("i"), new N("a"), new N("o"));
		h.insert(new N("r"));

		log("Binary Stack stocked : \n " + h);
		h.deleteMin();
		log("Binary Stack state after deletion of min : \n " + h);

		final Ni[] a = {new Ni(4), new Ni(7), new Ni(7), new Ni(7), new Ni(5), new Ni(0), new Ni(2), new Ni(3), new Ni(5), new Ni(1)};
		log("Array given to sort \n " + descArray(a));
		TasBinaire<Ni> tmp = new TasBinaire<>(new ArrayFactory<Ni>() {
			@Override
			public Ni[] buildArray(int dimension) {
				return new Ni[dimension];
			}
		});
		tmp.heapSort(a);
		log("The last array sorted : \n " + descArray(a));
	}
	
	private class N extends Node<String> {
		N(final String n){
			super(n);
		}
	}

	private class Ni extends Node<Integer> {
		Ni(final Integer n){
			super(n);
		}
	}

	private String descArray(final Ni[] array){
		return Arrays.toString(array);
	}
	
}

