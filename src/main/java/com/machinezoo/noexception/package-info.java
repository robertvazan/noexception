// Part of NoException: https://noexception.machinezoo.com
/**
 * NoException is a library that lets you deal with exceptions in concise, testable, and standardized manner.
 * Class {@link com.machinezoo.noexception.Exceptions} is the entry point to NoException functionality.
 * After picking one of its predefined exception handlers, you pass your code as a lambda to
 * the appropriate method of {@link com.machinezoo.noexception.ExceptionHandler} or {@link com.machinezoo.noexception.CheckedExceptionHandler}.
 * <p>
 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda).orElse(fallback)}
 *
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 */
package com.machinezoo.noexception;