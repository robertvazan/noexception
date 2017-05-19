package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleToIntFunction {
	int applyAsInt(double value) throws Exception;
}
