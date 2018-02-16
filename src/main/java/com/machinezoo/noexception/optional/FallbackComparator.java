// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

final class FallbackComparator<T> implements Comparator<T> {
	private final OptionalComparator<T> inner;
	private final IntSupplier source;
	public FallbackComparator(OptionalComparator<T> inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int compare(T left, T right) {
		return inner.compare(left, right).orElseGet(source);
	}
}
