// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingConsumerTest {
    void takeThrowing(ThrowingConsumer<String> functional) {
    }
    void takeNonThrowing(Consumer<String> functional) {
    }
    @Test
    public void lambdas() {
        takeNonThrowing(t -> {
        });
        takeThrowing(t -> {
        });
        takeThrowing(t -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw new IOException();
            else
                return;
        });
        Throwable throwable = new IOException();
        takeThrowing(t -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw throwable;
            else
                return;
        });
    }
}
