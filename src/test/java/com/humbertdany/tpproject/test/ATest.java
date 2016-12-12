package com.humbertdany.tpproject.test;

/**
 * Created by Dany on 14/11/2016.
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
