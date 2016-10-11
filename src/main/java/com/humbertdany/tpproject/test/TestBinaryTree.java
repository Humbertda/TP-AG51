/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.tree.*;

/**
 *
 * @author dhumbert
 */
public class TestBinaryTree {
	
	public TestBinaryTree(){
		
	}
	
	final public void launch(){
		final N root = new N(1);
		final N n1 = new N(50);
		final N n2 = new N(4);
		final N n3 = new N(2);
		final N n4 = new N(5);

		final BinaryTree<Integer> l1 = new BinaryTree(root);
		l1.add(root, n1, BinaryTree.ORIENTATION_LEFT);
		l1.add(root, n2, BinaryTree.ORIENTATION_RIGHT);
		l1.add(n2, n3, BinaryTree.ORIENTATION_LEFT);
		l1.add(n2, n4, BinaryTree.ORIENTATION_RIGHT);
		
		System.out.println(l1.contains(new Integer(50)));
		System.out.println(l1.contains(new Integer(4)));
		System.out.println(l1.contains(new Integer(55)));
		
		l1.printInOrder();
		l1.printPostOrder();
	}
	
	class N extends com.humbertdany.tpproject.util.tree.Node<Integer> {
		public N(Integer n){
			super(n);
		}
	}
	
}

