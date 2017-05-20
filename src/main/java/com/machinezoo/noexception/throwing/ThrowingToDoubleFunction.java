// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingToDoubleFunction<T> {
	double applyAsDouble(T value) throws Exception;
}
