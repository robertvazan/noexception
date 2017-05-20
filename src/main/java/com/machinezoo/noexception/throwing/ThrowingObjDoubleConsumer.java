// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingObjDoubleConsumer<T> {
	void accept(T t, double value) throws Exception;
}
