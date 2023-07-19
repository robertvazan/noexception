// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackBooleanSupplier implements BooleanSupplier {
    private final OptionalBooleanSupplier inner;
    private final BooleanSupplier source;
    public FallbackBooleanSupplier(OptionalBooleanSupplier inner, BooleanSupplier source) {
        this.inner = inner;
        this.source = source;
    }
    @Override
    public boolean getAsBoolean() {
        return inner.get().orElseGet(source);
    }
}
