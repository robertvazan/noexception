// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingLongConsumer {
	void accept(long value) throws Exception;
}
