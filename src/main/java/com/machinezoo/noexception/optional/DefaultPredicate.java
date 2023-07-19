// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultPredicate<T> implements Predicate<T> {
    private final OptionalPredicate<T> inner;
    private final boolean result;
    public DefaultPredicate(OptionalPredicate<T> inner, boolean result) {
        this.inner = inner;
        this.result = result;
    }
    @Override
    public boolean test(T t) {
        return inner.test(t).orElse(result);
    }
}
