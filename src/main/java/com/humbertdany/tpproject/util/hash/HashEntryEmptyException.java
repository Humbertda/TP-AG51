package com.humbertdany.tpproject.util.hash;

/**
 * Exception returned when
 * there is an error in the MyHashMap
 */
public class HashEntryEmptyException extends Exception {

	public HashEntryEmptyException(final String key){
		super("The requested key (" + key + ") is empty");
	}
}
