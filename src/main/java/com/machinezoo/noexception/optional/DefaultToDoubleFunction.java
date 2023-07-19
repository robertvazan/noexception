// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultToDoubleFunction<T> implements ToDoubleFunction<T> {
    private final OptionalToDoubleFunction<T> inner;
    private final double result;
    public DefaultToDoubleFunction(OptionalToDoubleFunction<T> inner, double result) {
        this.inner = inner;
        this.result = result;
    }
    @Override
    public double applyAsDouble(T value) {
        return inner.apply(value).orElse(result);
    }
}
