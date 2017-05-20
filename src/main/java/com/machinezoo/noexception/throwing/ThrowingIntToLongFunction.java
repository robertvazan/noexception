// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntToLongFunction {
	long applyAsLong(int value) throws Exception;
}
