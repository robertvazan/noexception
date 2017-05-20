// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleSupplier {
	double getAsDouble() throws Exception;
}
