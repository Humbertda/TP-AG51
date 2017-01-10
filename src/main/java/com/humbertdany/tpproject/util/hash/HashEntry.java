package com.humbertdany.tpproject.util.hash;

/**
 * The hash entry
 * @param <T>
 */
public class HashEntry<T> {

	/**
	 * Store the key
	 */
	private int key;

	/**
	 * Store the value
	 */
	private T value;

	public HashEntry(final int key, final T value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Return the object key
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Return the current value of the entry
	 * @return the current value
	 */
	public synchronized T getValue() {
		return value;
	}

	/**
	 * Generate an empty HashEntry
	 * @return
	 */
	public static final HashEntry empty(){
		return new HashEntry(-1, null);
	}

}