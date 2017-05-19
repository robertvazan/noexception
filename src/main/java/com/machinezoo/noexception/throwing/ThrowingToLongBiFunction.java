package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingToLongBiFunction<T, U> {
    long applyAsLong(T t, U u) throws Exception;
}
