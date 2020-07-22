// Part of NoException: https://noexception.machinezoo.com
/*
 * It is tempting to add CompletableFuture wrapper. For example, logging wrapper would be quite useful to prevent swallowing of exceptions.
 * There is however a number of arguments against this:
 * - It would only work in ExceptionHandler, not CheckedExceptionHandler, because exceptions from CompletableFuture are already wrapped.
 * - The wrapper would produce CompletableFuture<Optional<T>>. There's no convenient way to add orElse() method to it.
 * - Futures are not functional interfaces like the rest. They can be wrapped, but it costs a lot of complexity and it doesn't fit the API.
 * - There is a duality between CompletableFuture and CompletionStage, which further increases complexity on both API and implementation level.
 * - Futures are not used frequently, at least not yet. They are a low priority problem.
 * 
 * Wrappers for ExecutorService would have similar problems.
 */
/**
 * Classes implementing all NoException features.
 * Class {@link com.machinezoo.noexception.Exceptions} is the entry point to NoException functionality.
 * After picking one of its predefined exception handlers and possibly calling {@link com.machinezoo.noexception.ExceptionHandler#passing()},
 * you pass your code as a lambda to the appropriate method of {@link com.machinezoo.noexception.ExceptionHandler}, {@link com.machinezoo.noexception.ExceptionFilter},
 * or {@link com.machinezoo.noexception.CheckedExceptionHandler}.
 * <p>
 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda).orElse(fallback)}
 *
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 */
package com.machinezoo.noexception;
