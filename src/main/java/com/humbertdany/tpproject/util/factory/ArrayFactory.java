/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.factory;

/**
 *
 * @author dhumbert
 */
abstract public class ArrayFactory<T> {
    abstract public T[] buildArray(int dimension);
}
