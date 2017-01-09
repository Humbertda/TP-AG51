package com.humbertdany.tpproject.test;

/**
 * The ATest class
 * Every TP launcher extends this class
 * it give important methods to
 */
abstract public class ATest {

	abstract public void launch();

	public static void separeLine(){
		System.out.println();
		System.out.println("#----------------------------------#");
		System.out.println();
	}

	protected final void separe(){
		ATest.separeLine();
	}

	protected final void log(final Object o){
		System.out.println(o.toString());
	}

}
