// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackFunction<T, R> implements Function<T, R> {
    private final OptionalFunction<T, R> inner;
    private final Supplier<R> source;
    public FallbackFunction(OptionalFunction<T, R> inner, Supplier<R> source) {
        this.inner = inner;
        this.source = source;
    }
    @Override
    public R apply(T t) {
        return inner.apply(t).orElseGet(source);
    }
}
