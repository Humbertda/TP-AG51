package com.humbertdany.tpproject.util.hash;

public interface Keyable<T, K> {
	T getKey(K object);
}
