// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingObjIntConsumer<T> {
	void accept(T t, int value) throws Exception;
}
