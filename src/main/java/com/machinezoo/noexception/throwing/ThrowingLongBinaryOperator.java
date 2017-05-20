// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingLongBinaryOperator {
	long applyAsLong(long left, long right) throws Exception;
}
