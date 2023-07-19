// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntFunction<R> implements IntFunction<R> {
    private final OptionalIntFunction<R> inner;
    private final Supplier<R> source;
    public FallbackIntFunction(OptionalIntFunction<R> inner, Supplier<R> source) {
        this.inner = inner;
        this.source = source;
    }
    @Override
    public R apply(int value) {
        return inner.apply(value).orElseGet(source);
    }
}
