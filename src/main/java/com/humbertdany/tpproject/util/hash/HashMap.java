package com.humbertdany.tpproject.util.hash;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * The HashMap class
 * @param <T>
 */
public class HashMap<T, K> {

	final private HashEntry<T, K>[] table;
	final private int dimension;

	/**
	 * Construct the HashMap with a given dimension
	 * @param dim the dimensions
	 */
	public HashMap(final int dim) {
		this.dimension = dim;
		table = (HashEntry<T, K>[]) Array.newInstance(HashEntry.class, dim);
		for (int i = 0; i < dimension; i++)
			table[i] = null;
	}

	/**
	 * Get an element in the map
	 * @param key the key to get
	 * @return the value stored at 'key'
	 * @throws HashEntryEmptyException
	 */
	public synchronized K get(T key) throws HashEntryEmptyException {
		int hash = getHash(key);
		while (table[hash] != null && !Objects.equals(table[hash].getKey(), key))
			hash = (hash + 1) % dimension;
		if (table[hash] == null)
			throw new HashEntryEmptyException(key.toString());
		else
			return table[hash].getValue();
	}

	/**
	 * Put an element in the map
	 * @param key the key where to put it
	 * @param value the value tu store
	 */
	public synchronized void put(T key, K value) {
		int hash = getHash(key);
		while (table[hash] != null && !Objects.equals(table[hash].getKey(), key))
			hash = (hash + 1) % dimension;
		table[hash] = new HashEntry<>(key, value);
	}

	private int getHash(T key){
		return Objects.hash(key) % dimension;
	}

	/**
	 * Search a value in the HashMap
	 * @param keyable the keyable to get the real key
	 * @param value the value so search
	 * @return the key
	 * @throws HashEntryEmptyException
	 */
	public final T searchValue(Keyable<T, K> keyable, K value) throws HashEntryEmptyException {
		final T key = keyable.getKey(value);
		int iteration = 0;
		do {
			this.get(key);
			if(this.get(key).equals(value)){
				return key;
			}
			iteration ++;
		} while(iteration <= dimension);
		throw new HashEntryEmptyException("Unable to find entry for " + value.toString());
	}

}