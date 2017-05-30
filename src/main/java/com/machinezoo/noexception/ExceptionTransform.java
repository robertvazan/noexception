// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends CheckedExceptionHandler {
	@NonNull private final Function<Exception, RuntimeException> wrapper;
	@Override public RuntimeException handle(@NonNull Exception exception) {
		return wrapper.apply(exception);
	}
}
