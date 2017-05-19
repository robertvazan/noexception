package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleConsumer {
	void accept(double value) throws Exception;
}
