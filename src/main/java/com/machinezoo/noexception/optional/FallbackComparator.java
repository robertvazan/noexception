// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackComparator<T> implements Comparator<T> {
	private final OptionalComparator<T> inner;
	private final IntSupplier source;
	@Override public int compare(T left, T right) {
		return inner.compare(left, right).orElseGet(source);
	}
}
