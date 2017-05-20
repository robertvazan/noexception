// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingLongFunction<R> {
	R apply(long value) throws Exception;
}
