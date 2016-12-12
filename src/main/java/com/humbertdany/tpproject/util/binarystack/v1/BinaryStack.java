package com.humbertdany.tpproject.util.binarystack.v1;

import com.humbertdany.tpproject.util.binarystack.ABinaryStack;
import com.humbertdany.tpproject.util.factory.ArrayFactory;

/**
 *
 * @author dhumbert
 */
public class BinaryStack<T extends Node> extends ABinaryStack<T> {

	public final static int ORIENTATION_RIGHT = 1;
	public final static int ORIENTATION_LEFT = -1;
	private T root;

	public BinaryStack(final ArrayFactory<T> factory, final T root) {
		super(factory);
		this.root = root;
	}

	public void add(final T parent, final T child, final int orientation) {
		switch (orientation) {
			case BinaryStack.ORIENTATION_LEFT:
				parent.setLeft(child);
				break;
			case BinaryStack.ORIENTATION_RIGHT:
				parent.setRight(child);
				break;
		}
	}

	/**
	 * Return true if item is one of the items in the binary sort binarystack to which
	 * root points. Return false if not.
	 */
	public boolean contains(final T item) {
		return this.treeContains(this.root, item);
	}

	private boolean treeContains(final Node root, final Node item) {
		if (root == null) {
			// Tree is empty, so it certainly doesn't contain item.
			return false;
		} else if (item.equals(root)) {
			// Yes, the item has been found in the root node.
			return true;
		} else if (item.greaterThen(root)) { // TODO make sure this is the right order
			// If the item occurs, it must be in the left subtree.
			return treeContains(root.getLeft(), item);
		} else {
			// If the item occurs, it must be in the right subtree.
			return treeContains(root.getRight(), item);
		}
	}

	/**
	 * Print all the items in the binarystack to which root points. The item in the
	 * left subtree is printed first, followed by the items in the right subtree
	 * and then the item in the root node.
	 */
	public void printPostOrder() {
		System.out.println("BinaryStack print:");
		this.postOrderPrint(root);
		System.out.println("--end");
	}

	private void postOrderPrint(final Node root) {
		if (root != null) {  // (Otherwise, there's nothing to print.)
			postOrderPrint(root.getLeft());   // Print items in left subtree.
			postOrderPrint(root.getRight());  // Print items in right subtree.
			System.out.print(root.getKey() + " ");  // Print the root item.
		}
	}

	/**
	 * Print all the items in the binarystack to which root points. The item in the
	 * left subtree is printed first, followed by the item in the root node and
	 * then the items in the right subtree.
	 */
	public void printInOrder() {
		System.out.println("BinaryStack print:");
		this.inOrderPrint(root);
		System.out.println("--end");
	}

	private void inOrderPrint(final Node root) {
		if (root != null) {  // (Otherwise, there's nothing to print.)
			inOrderPrint(root.getLeft());   // Print items in left subtree.
			System.out.print(root.getKey() + " ");  // Print the root item.
			inOrderPrint(root.getRight());  // Print items in right subtree.
		}
	}
	
	/**
	 * Return root element
	 */
	public T getRoot(){
		return this.root;
	}
}
