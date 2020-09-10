// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;

/*
 * This interface could be named UncheckedCloseable, which would be more explicit,
 * but we want to support returning this interface directly from methods that open something
 * without having to define public implementing class every time.
 * After all, when there is an implementing class, then exception specification can be removed on that class
 * and there would then be no need for this interface.
 * So we want some nice interface name that can be part of APIs and UncheckedCloseable doesn't look nice.
 * It's too focused on a technicality. Since this interface would be returned most often
 * when try-with-resources is (ab)used as a scoping mechanism, we will name it CloseableScope.
 */
/**
 * Specialization of {@link AutoCloseable} that throws only unchecked exceptions.
 */
public interface CloseableScope extends AutoCloseable {
	/**
	 * Closes this scope or other closeable resource.
	 * This is a specialization of {@link AutoCloseable#close()} that throws only unchecked exceptions.
	 */
	void close();
	/*
	 * We could also take CloseableScope as a parameter, which would be more consistent with andThen methods in Consumer and such,
	 * but Runnable is more common and CloseableScopes are unlikely to need to be combined in practice.
	 * We don't want to overload on Runnable and CloseableScope, because that would create ambiguity for lambdas.
	 */
	/**
	 * Creates extended {@code CloseableScope} that additionally runs specified {@code action} unless exception is thrown.
	 * This is useful for quickly creating outer scopes that add extra operations to some inner scope.
	 * If {@link #close()} throws, {@code action} does not run.
	 * 
	 * @param action
	 *            the operation to perform after {@link #close()} is called
	 * @return new {@code CloseableScope} that, when closed, first closes this {@code CloseableScope} and then executes {@code action}
	 * @throws NullPointerException
	 *             if {@code action} is {@code null}
	 * 
	 * @see #andFinally(Runnable)
	 */
	default CloseableScope andThen(Runnable action) {
		Objects.requireNonNull(action);
		return () -> {
			close();
			action.run();
		};
	}
	/**
	 * Creates extended {@code CloseableScope} that additionally runs specified {@code action} regardless of exceptions.
	 * This is useful for quickly creating outer scopes that add extra operations to some inner scope.
	 * If {@link #close()} throws, {@code action} runs anyway as if in try-with-resources block.
	 * If both {@link #close()}} and {@code action} throw, the exception from {@code action} is added
	 * to suppressed exception list by calling {@link Throwable#addSuppressed(Throwable)}.
	 * 
	 * @param action
	 *            the operation to perform after {@link #close()} is called
	 * @return new {@code CloseableScope} that, when closed, first closes this {@code CloseableScope} and then executes {@code action}
	 * @throws NullPointerException
	 *             if {@code action} is {@code null}
	 * 
	 * @see #andThen(Runnable)
	 */
	default CloseableScope andFinally(Runnable action) {
		Objects.requireNonNull(action);
		return () -> {
			Throwable exception = null;
			try {
				close();
			} catch (Throwable ex) {
				exception = ex;
				throw ex;
			} finally {
				if (exception != null) {
					try {
						action.run();
					} catch (Throwable suppressed) {
						exception.addSuppressed(suppressed);
					}
				} else
					action.run();
			}
		};
	}
}
