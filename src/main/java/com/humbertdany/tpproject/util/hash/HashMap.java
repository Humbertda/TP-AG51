package com.humbertdany.tpproject.util.hash;

import java.lang.reflect.Array;

/**
 * The HashMap class
 * @param <T>
 */
public class HashMap<T> {

	final private HashEntry<T>[] table;
	final private int dimension;

	/**
	 * Construct the HashMap with a given dimension
	 * @param dim the dimensions
	 */
	public HashMap(final int dim) {
		this.dimension = dim;
		table = (HashEntry<T>[]) Array.newInstance(HashEntry.class, dim);
		for (int i = 0; i < dimension; i++)
			table[i] = null;
	}

	/**
	 * Get an element in the map
	 * @param key the key to get
	 * @return the value stored at 'key'
	 * @throws HashEntryEmptyException
	 */
	public synchronized T get(int key) throws HashEntryEmptyException {
		int hash = (key % dimension);
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % dimension;
		if (table[hash] == null)
			throw new HashEntryEmptyException(key);
		else
			return table[hash].getValue();
	}

	/**
	 * Put an element in the map
	 * @param key the key where to put it
	 * @param value the value tu store
	 */
	public synchronized void put(int key, T value) {
		int hash = (key % dimension);
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % dimension;
		table[hash] = new HashEntry<>(key, value);
	}

	/**
	 * Read the entire map
	 * Works as the toString()
	 */
	public synchronized final void readAll(){
		for(int i = 0; i < this.dimension; i++){
			try {
				System.out.println("[key:"+ i +"] " + this.get(i));
			} catch (HashEntryEmptyException e) {
				// We dont care if its empty,
				// we just want to parse it all
			}
		}
	}

}