package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingToIntFunction<T> {
	int applyAsInt(T value) throws Exception;
}
