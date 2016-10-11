/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.generator;

import java.util.ArrayList;

/**
 *
 * @author dhumbert
 */
abstract public class ArrayListGenerator<T extends Comparable> {
    abstract T buildObject();
    
    final public ArrayList<T> generate(final int count){
        final ArrayList<T> res = new ArrayList<>();
        for(int i = 0; i < count; i++){
            res.add(this.buildObject());
        }
        return res;
    }; 
}
