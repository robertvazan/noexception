package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingSupplier<T> {
	T get() throws Exception;
}
