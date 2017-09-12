package com.machinezoo.noexception;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.machinezoo.noexception.Exceptions.sneak;
import static com.machinezoo.noexception.InterruptedAsserts.assertThatThreadInterruptedIsNotSet;
import static com.machinezoo.noexception.InterruptedAsserts.assertThatThreadInterruptedIsSet;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ExceptionHandlerDoHandleTest {
    @Rule public ExpectedException expectedException = ExpectedException.none();

    private ExceptionHandler exceptionHandler;

    @Before public void setUp() {
        exceptionHandler = spy(ExceptionHandler.class);
    }

    @Test public void handle_accept_exception() {
        when(exceptionHandler.handle(any(Throwable.class)))
                .thenReturn(true);

        assertTrue(exceptionHandler.doHandle(new NumberFormatException()));
    }

    @Test public void handle_throw_exception() {
        doThrow(new IllegalArgumentException())
                .when(exceptionHandler)
                .handle(any(Throwable.class));

        expectedException.expect(
                both(instanceOf(IllegalArgumentException.class))
                        .and(hasProperty("suppressed", hasItemInArray(instanceOf(NumberFormatException.class)))));

        exceptionHandler.doHandle(new NumberFormatException());
    }

    @Test public void handle_reject_exception() {
        when(exceptionHandler.handle(any(Throwable.class)))
                .thenReturn(false);

        assertFalse(exceptionHandler.doHandle(new NumberFormatException()));
    }

    @Test public void handle_accept_InterruptedException() {
        when(exceptionHandler.handle(any(InterruptedException.class)))
                .thenReturn(true);
        Thread.interrupted();

        exceptionHandler.doHandle(new InterruptedException());

        assertThatThreadInterruptedIsSet();
    }

    @Test public void handle_throw_not_InterrruptedException() {
        doThrow(new NumberFormatException())
                .when(exceptionHandler)
                .handle(any(InterruptedException.class));
        Thread.interrupted();

        try {
            exceptionHandler.doHandle(new InterruptedException());
            fail();
        }
        catch (Throwable ignored) {
        }

        assertThatThreadInterruptedIsSet();
    }

    @Test public void handle_throw_InterrruptedException() {
        when(exceptionHandler.handle(any(InterruptedException.class)))
                .thenAnswer(a -> {
                    sneak().run(() -> {
                        throw new InterruptedException();
                    });
                    return true;
                });
        Thread.interrupted();

        try {
            exceptionHandler.doHandle(new InterruptedException());
            fail();
        } catch (Throwable ignored) {
        }

        assertThatThreadInterruptedIsNotSet();
    }

    @Test public void handle_reject_InterruptedException() {
        when(exceptionHandler.handle(any(InterruptedException.class)))
                .thenReturn(false);
        Thread.interrupted();

        exceptionHandler.doHandle(new InterruptedException());

        assertThatThreadInterruptedIsNotSet();
    }
}
