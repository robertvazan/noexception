// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleBinaryOperator {
	double applyAsDouble(double left, double right) throws Exception;
}
