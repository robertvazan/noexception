package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingToLongFunction<T> {
	long applyAsLong(T value) throws Exception;
}
