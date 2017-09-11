// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.Optional;
import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends ExceptionWrapper {
	@NonNull private final Function<Exception, RuntimeException> wrapper;
	@Override protected RuntimeException wrap(Exception exception) {
		return Optional.ofNullable(wrapper.apply(exception))
				.orElse(super.wrap(exception));
	}
}
