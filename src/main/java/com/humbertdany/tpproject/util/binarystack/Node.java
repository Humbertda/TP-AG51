/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.binarystack;

/**
 *
 * @author dhumbert
 */
public class Node<T extends Comparable> {

	private T key;
	private Node<T> left;
	private Node<T> right;

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

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Node<T> getRight() {
		return right;
	}


	final public boolean greaterThen(final Node<T> t){
		return this.getKey().compareTo(t.getKey()) > 0;
	}
}