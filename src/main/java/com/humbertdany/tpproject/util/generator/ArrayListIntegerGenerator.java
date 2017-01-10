package com.humbertdany.tpproject.util.generator;

import java.util.Random;

/**
 * Class who generate random integers
 * between min and max
 * @author dhumbert
 */
public class ArrayListIntegerGenerator extends ArrayListGenerator<Integer>{
    
    private Random random = new Random();
    final private int min, max;
    
     public ArrayListIntegerGenerator(){
         this(0, Integer.MAX_VALUE-1); 
     }
    
    public ArrayListIntegerGenerator(int min, int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public final Integer buildObject() {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public Integer[] buildArray(int dimension) {
        return new Integer[dimension];
    }
    
}
