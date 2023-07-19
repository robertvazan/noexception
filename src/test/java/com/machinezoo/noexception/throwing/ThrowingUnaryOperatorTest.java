// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingUnaryOperatorTest {
    void takeThrowing(ThrowingUnaryOperator<String> functional) {
    }
    void takeNonThrowing(UnaryOperator<String> functional) {
    }
    @Test
    public void lambdas() {
        takeNonThrowing(o -> "value");
        takeThrowing(o -> "value");
        takeThrowing(o -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw new IOException();
            else
                return "value";
        });
        Throwable throwable = new IOException();
        takeThrowing(o -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw throwable;
            else
                return "value";
        });
    }
}
