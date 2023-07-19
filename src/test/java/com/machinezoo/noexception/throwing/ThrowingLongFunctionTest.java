// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingLongFunctionTest {
    void takeThrowing(ThrowingLongFunction<String> functional) {
    }
    void takeNonThrowing(LongFunction<String> functional) {
    }
    @Test
    public void lambdas() {
        takeNonThrowing(v -> "value");
        takeThrowing(v -> "value");
        takeThrowing(v -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw new IOException();
            else
                return "value";
        });
        Throwable throwable = new IOException();
        takeThrowing(v -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw throwable;
            else
                return "value";
        });
    }
}
