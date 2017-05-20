// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingPredicate<T> {
	boolean test(T t) throws Exception;
}
