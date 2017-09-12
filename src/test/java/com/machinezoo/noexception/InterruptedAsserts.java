package com.machinezoo.noexception;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class InterruptedAsserts {
    public static void assertThatThreadInterruptedIsSet() {
        assertThat(
                "Interrupted status is not set",
                Thread.interrupted(), // We must clear interrupt status
                is(true));
    }

    public static void assertThatThreadInterruptedIsNotSet() {
        assertThat(
                "Interrupted status is set",
                Thread.interrupted(), // We must clear interrupt status
                is(false));
    }
}
