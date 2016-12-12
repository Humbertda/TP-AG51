package com.humbertdany.tpproject.util.binarystack.v2;

import com.humbertdany.tpproject.util.binarystack.ABinaryStack;
import com.humbertdany.tpproject.util.factory.ArrayFactory;

import java.io.Serializable;

/**
 *
 * @author Mitsu
 */
public class TasBinaire<T extends Comparable<T>> extends ABinaryStack<T> implements Serializable {

	private static final int CAPACITY = 2;

	/**
	 * Nombre d'éléments dans le tas
	 */
	private int size;

	/**
	 * Array
	 */
	private T[] heap;

	public TasBinaire(final ArrayFactory<T> factory) {
		this(factory, factory.buildArray(0));
	}

	/**
	 * Construit le tas binaire à partir d'une array
	 */
	public TasBinaire(final ArrayFactory<T> factory, T[] array) {
		super(factory);
		size = array.length;
		heap = factory.buildArray(array.length + 1);

		System.arraycopy(array, 0, heap, 1, array.length);

		buildHeap();
	}

	private void buildHeap() {
		for (int k = size / 2; k > 0; k--) {
			percolatingDown(k);
		}
	}

	private void percolatingDown(int k) {
		T tmp = heap[k];
		int child;

		for (; 2 * k <= size; k = child) {
			child = 2 * k;

			if (child != size
					&& heap[child].compareTo(heap[child + 1]) > 0) {
				child++;
			}

			if (tmp.compareTo(heap[child]) > 0) {
				heap[k] = heap[child];
			} else {
				break;
			}
		}
		heap[k] = tmp;
	}

	/**
	 * Trie une array.
	 */
	public void heapSort(T[] array) {
		size = array.length;
		heap = (T[]) new Comparable[size + 1];
		System.arraycopy(array, 0, heap, 1, size);
		buildHeap();

		for (int i = size; i > 0; i--) {
			T tmp = heap[i]; //move top item to the end of the heap array
			heap[i] = heap[1];
			heap[1] = tmp;
			size--;
			percolatingDown(1);
		}
		for (int k = 0; k < heap.length - 1; k++) {
			array[k] = heap[heap.length - 1 - k];
		}
	}

	/**
	 * Supprime l'élément du haut.
	 */
	public T deleteMin() throws RuntimeException {
		if (size == 0) {
			throw new RuntimeException();
		}
		T min = heap[1];
		heap[1] = heap[size--];
		percolatingDown(1);
		return min;
	}

	/**
	 * Insère un nouvel élément.
	 */
	public void insert(T x) {
		if (size == heap.length - 1) {
			doubleSize();
		}

		//Insère un nouvel élément à la fin de l'array
		int pos = ++size;

		//Percolate up
		for (; pos > 1 && x.compareTo(heap[pos / 2]) < 0; pos = pos / 2) {
			heap[pos] = heap[pos / 2];
		}

		heap[pos] = x;
	}

	/**
	 * Add all the elements in parameters to the Binary stack
	 * @param elements all the elements to add
	 */
	@SafeVarargs
	public final void insertAll(final T... elements){
		for(T elem : elements){
			this.insert(elem);
		}
	}

	private void doubleSize() {
		T[] old = heap;
		heap = (T[]) new Comparable[heap.length * 2];
		System.arraycopy(old, 1, heap, 1, size);
	}

	public String toString() {
		String out = "";
		for (int k = 1; k <= size; k++) {
			out += heap[k] + " ";
		}
		return out;
	}
}
