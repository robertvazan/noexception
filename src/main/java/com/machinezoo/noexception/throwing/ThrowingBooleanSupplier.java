// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingBooleanSupplier {
	boolean getAsBoolean() throws Exception;
}
