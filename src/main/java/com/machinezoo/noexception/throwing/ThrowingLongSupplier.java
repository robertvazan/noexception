package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingLongSupplier {
    long getAsLong() throws Exception;
}
