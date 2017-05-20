// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntBinaryOperator {
	int applyAsInt(int left, int right) throws Exception;
}
