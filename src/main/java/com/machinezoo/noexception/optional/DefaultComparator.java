// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultComparator<T> implements Comparator<T> {
	private final OptionalComparator<T> inner;
	private final int result;
	@Override public int compare(T left, T right) {
		return inner.compare(left, right).orElse(result);
	}
}
