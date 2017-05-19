package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingFunction<T, R> {
	R apply(T t) throws Exception;
}
