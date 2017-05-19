package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntToDoubleFunction {
	double applyAsDouble(int value) throws Exception;
}
