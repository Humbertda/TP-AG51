/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.prioritystack;

import com.humbertdany.tpproject.util.tree.BinaryTree;
import com.humbertdany.tpproject.util.tree.Node;

/**
 *
 * @author dhumbert
 */
public class PriorityStack<T extends Comparable> {
	
	final private BinaryTree<T> tree;
	
	public PriorityStack(){
		tree = new BinaryTree<>(new Node(0));
		// TODO:: implements this
		// What I could check:  http://codereview.stackexchange.com/questions/98105/priority-stack-in-java
	}
	
	/**
	 * Insert a Node in the Stack with a given Priority
	 * @param toInsert : the Node 
	 * @param x  : the Priority
	 */
	public void insert(final T toInsert, int x){
		tree.add(tree.getRoot(), new Node(toInsert), BinaryTree.ORIENTATION_LEFT); //TODO
	}
	
	/**
	 * Get the Node with the maximum priority without removing it from the Stack
	 * @return T : the maximum priority Node
	 */
	public T maximum(){
		return tree.getRoot().getKey(); //TODO
	}
	
	/**
	 * Get the Node with the maximm priority and remove it from the Stack
	 * (e.g: the action is taken from the stack and executed so we don't need it after)
	 * @return T : the maximum priority Node
	 */
	public T extractMax(){
		return tree.getRoot().getKey(); //TODO
	}
	
}
