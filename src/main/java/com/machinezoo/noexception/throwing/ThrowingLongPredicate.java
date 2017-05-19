package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingLongPredicate {
	boolean test(long value) throws Exception;
}
