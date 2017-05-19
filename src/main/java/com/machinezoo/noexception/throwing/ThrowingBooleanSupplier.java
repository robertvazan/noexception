package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingBooleanSupplier {
	boolean getAsBoolean() throws Exception;
}
