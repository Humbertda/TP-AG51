/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.binarystack.*;

/**
 *
 * @author dhumbert
 */
public class TestBinaryStack extends ATest {
	
	public TestBinaryStack(){
		
	}
	
	final public void launch(){
		final N root = new N(1);
		final N n1 = new N(50);
		final N n2 = new N(4);
		final N n3 = new N(2);
		final N n4 = new N(5);

		final BinaryStack<N> l1 = new BinaryStack<>(root);
		l1.add(root, n1, BinaryStack.ORIENTATION_LEFT);
		l1.add(root, n2, BinaryStack.ORIENTATION_RIGHT);
		l1.add(n2, n3, BinaryStack.ORIENTATION_LEFT);
		l1.add(n2, n4, BinaryStack.ORIENTATION_RIGHT);
		
		System.out.println(l1.contains(new N(50)));
		System.out.println(l1.contains(new N(4)));
		System.out.println(l1.contains(new N(55)));
		
		l1.printInOrder();
		l1.printPostOrder();
	}
	
	private class N extends Node<Integer> {
		N(final Integer n){
			super(n);
		}
	}
	
}

