/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.tree;

/**
 *
 * @author dhumbert
 */
public class Node<T extends Comparable> {

	private T key;
	private Node left;
	private Node right;

	public Node(T key) {
		this.key = key;
		right = null;
		left = null;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getLeft() {
		return left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getRight() {
		return right;
	}
}