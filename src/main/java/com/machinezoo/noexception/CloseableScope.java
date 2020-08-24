// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

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
}
