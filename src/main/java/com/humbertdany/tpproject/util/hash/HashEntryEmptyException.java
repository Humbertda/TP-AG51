package com.humbertdany.tpproject.util.hash;

public class HashEntryEmptyException extends Exception {

	public HashEntryEmptyException(final int key){
		super("The requested key (" + key + ") is empty");
	}
}
