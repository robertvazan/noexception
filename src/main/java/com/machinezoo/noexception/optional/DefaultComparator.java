// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;

final class DefaultComparator<T> implements Comparator<T> {
	private final OptionalComparator<T> inner;
	private final int result;
	public DefaultComparator(OptionalComparator<T> inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int compare(T left, T right) {
		return inner.compare(left, right).orElse(result);
	}
}
