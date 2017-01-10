package com.humbertdany.tpproject.util.prioritystack;

import com.humbertdany.tpproject.util.binarystack.v2.TasBinaire;
import com.humbertdany.tpproject.util.factory.ArrayFactory;

/**
 *
 * @author dhumbert
 */
public class PriorityStack {
	
	final private TasBinaire<StackNode> tree;
	
	public PriorityStack(){
		tree = new TasBinaire<>(new ArrayFactory<StackNode>() {
			@Override
			public StackNode[] buildArray(int dimension) {
				return new StackNode[dimension];
			}
		});
	}
	
	/**
	 * Insert a Node in the Stack with a given Priority
	 * @param toInsert : the Node
	 */
	public void insert(final StackNode toInsert){
		tree.insert(toInsert);
	}
	
	/**
	 * Get the Node with the minimum priority and remove it from the Stack
	 * (e.g: the action is taken from the stack and executed so we don't need it after)
	 * @return the minimum priority Node
	 */
	public StackNode extractMin(){
		return tree.deleteMin();
	}

	/**
	 * Get the minimum element value
	 * @return the minimum value
	 */
	public StackNode getMin(){
		return tree.getMin();
	}
	
}
