package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntFunction<R> {
    R apply(int value) throws Exception;
}
