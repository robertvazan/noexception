package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleFunction<R> {
	R apply(double value) throws Exception;
}
