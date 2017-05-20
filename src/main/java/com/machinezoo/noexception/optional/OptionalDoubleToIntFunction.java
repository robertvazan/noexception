// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleToIntFunction extends DoubleFunction<OptionalInt> {
	@Override OptionalInt apply(double value);
	default DoubleToIntFunction orElse(int result) {
		return new DefaultDoubleToIntFunction(this, result);
	}
	default DoubleToIntFunction orElseGet(IntSupplier source) {
		return new FallbackDoubleToIntFunction(this, source);
	}
}
