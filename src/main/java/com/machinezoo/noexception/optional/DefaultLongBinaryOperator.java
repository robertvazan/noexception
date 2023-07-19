// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongBinaryOperator implements LongBinaryOperator {
    private final OptionalLongBinaryOperator inner;
    private final long result;
    public DefaultLongBinaryOperator(OptionalLongBinaryOperator inner, long result) {
        this.inner = inner;
        this.result = result;
    }
    @Override
    public long applyAsLong(long left, long right) {
        return inner.apply(left, right).orElse(result);
    }
}
