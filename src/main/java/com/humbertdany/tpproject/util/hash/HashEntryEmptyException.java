package com.humbertdany.tpproject.util.hash;

/**
 * Exception returned when
 * there is an error in the HashMap
 */
public class HashEntryEmptyException extends Exception {

	public HashEntryEmptyException(final int key){
		super("The requested key (" + key + ") is empty");
	}
}
