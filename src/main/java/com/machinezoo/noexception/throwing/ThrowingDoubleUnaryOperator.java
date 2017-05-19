package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleUnaryOperator {
	double applyAsDouble(double operand) throws Exception;
}
