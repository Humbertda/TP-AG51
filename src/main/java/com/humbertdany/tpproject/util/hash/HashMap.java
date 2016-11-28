package com.humbertdany.tpproject.util.hash;

import java.lang.reflect.Array;

public class HashMap<T> {

	final private HashEntry<T>[] table;
	final private int dimension;

	public HashMap(final int dim) {
		this.dimension = dim;
		table = (HashEntry<T>[]) Array.newInstance(HashEntry.class, dim);
		for (int i = 0; i < dimension; i++)
			table[i] = null;
	}

	public synchronized T get(int key) throws HashEntryEmptyException {
		int hash = (key % dimension);
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % dimension;
		if (table[hash] == null)
			throw new HashEntryEmptyException(key);
		else
			return table[hash].getValue();
	}

	public synchronized void put(int key, T value) {
		int hash = (key % dimension);
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % dimension;
		table[hash] = new HashEntry<>(key, value);
	}

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