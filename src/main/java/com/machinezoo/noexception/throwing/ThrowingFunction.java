package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingFunction<T, R> {
    R apply(T value) throws Exception;
}
