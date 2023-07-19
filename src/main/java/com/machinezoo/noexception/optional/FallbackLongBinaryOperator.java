// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongBinaryOperator implements LongBinaryOperator {
    private final OptionalLongBinaryOperator inner;
    private final LongSupplier source;
    public FallbackLongBinaryOperator(OptionalLongBinaryOperator inner, LongSupplier source) {
        this.inner = inner;
        this.source = source;
    }
    @Override
    public long applyAsLong(long left, long right) {
        return inner.apply(left, right).orElseGet(source);
    }
}
