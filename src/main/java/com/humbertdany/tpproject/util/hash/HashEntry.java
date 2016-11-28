package com.humbertdany.tpproject.util.hash;

public class HashEntry<T> {

	private int key;
	private T value;

	public HashEntry(final int key, final T value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public synchronized T getValue() {
		return value;
	}

	public static final HashEntry empty(){
		return new HashEntry(-1, null);
	}

}