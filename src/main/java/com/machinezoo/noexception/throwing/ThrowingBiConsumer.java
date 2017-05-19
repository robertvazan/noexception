package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingBiConsumer<T, U> {
	void accept(T t, U u) throws Exception;
}
