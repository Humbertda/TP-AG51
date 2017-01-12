package com.humbertdany.tpproject.util.hash;

/**
 * The hash entry
 * @param <T>
 */
class HashEntry<K, T> {

	/**
	 * Store the key
	 */
	private K key;

	/**
	 * Store the value
	 */
	private T value;

	HashEntry(final K key, final T value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Return the object key
	 * @return the key
	 */
	K getKey() {
		return key;
	}

	/**
	 * Return the current value of the entry
	 * @return the current value
	 */
	synchronized T getValue() {
		return value;
	}


}