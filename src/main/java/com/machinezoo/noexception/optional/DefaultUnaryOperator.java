// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultUnaryOperator<T> implements UnaryOperator<T> {
    private final OptionalUnaryOperator<T> inner;
    private final T result;
    public DefaultUnaryOperator(OptionalUnaryOperator<T> inner, T result) {
        this.inner = inner;
        this.result = result;
    }
    @Override
    public T apply(T operand) {
        return inner.apply(operand).orElse(result);
    }
}
