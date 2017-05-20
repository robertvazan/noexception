// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleToLongFunction {
	long applyAsLong(double value) throws Exception;
}
