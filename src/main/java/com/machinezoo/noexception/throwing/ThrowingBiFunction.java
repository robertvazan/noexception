package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingBiFunction<T, U, R> {
	R apply(T t, U u) throws Exception;
}
