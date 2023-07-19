// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntPredicate implements IntPredicate {
    private final OptionalIntPredicate inner;
    private final BooleanSupplier source;
    public FallbackIntPredicate(OptionalIntPredicate inner, BooleanSupplier source) {
        this.inner = inner;
        this.source = source;
    }
    @Override
    public boolean test(int value) {
        return inner.test(value).orElseGet(source);
    }
}
