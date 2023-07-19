// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultFunction<T, R> implements Function<T, R> {
    private final OptionalFunction<T, R> inner;
    private final R result;
    public DefaultFunction(OptionalFunction<T, R> inner, R result) {
        this.inner = inner;
        this.result = result;
    }
    @Override
    public R apply(T t) {
        return inner.apply(t).orElse(result);
    }
}
