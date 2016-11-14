/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.prioritystack;

import com.humbertdany.tpproject.util.binarystack.BinaryStack;

/**
 *
 * @author dhumbert
 */
public class PriorityStack {
	
	final private BinaryStack<StackNode> tree;
	
	public PriorityStack(){
		tree = new BinaryStack<>(new StackNode("root"));
		// TODO:: implements this
		// What I could check:  http://codereview.stackexchange.com/questions/98105/priority-stack-in-java
	}
	
	/**
	 * Insert a Node in the Stack with a given Priority
	 * @param toInsert : the Node 
	 * @param x  : the Priority
	 */
	public void insert(final StackNode toInsert, int x){
		tree.add(tree.getRoot(), toInsert, BinaryStack.ORIENTATION_LEFT); //TODO
	}
	
	/**
	 * Get the Node with the maximum priority without removing it from the Stack
	 * @return T : the maximum priority Node
	 */
	public StackNode maximum(){
		return tree.getRoot(); //TODO
	}
	
	/**
	 * Get the Node with the maximm priority and remove it from the Stack
	 * (e.g: the action is taken from the stack and executed so we don't need it after)
	 * @return T : the maximum priority Node
	 */
	public StackNode extractMax(){
		return tree.getRoot(); //TODO
	}
	
}
