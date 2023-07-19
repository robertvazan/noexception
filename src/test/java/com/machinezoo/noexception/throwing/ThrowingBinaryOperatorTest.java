// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingBinaryOperatorTest {
    void takeThrowing(ThrowingBinaryOperator<String> functional) {
    }
    void takeNonThrowing(BinaryOperator<String> functional) {
    }
    @Test
    public void lambdas() {
        takeNonThrowing((l, r) -> "value");
        takeThrowing((l, r) -> "value");
        takeThrowing((l, r) -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw new IOException();
            else
                return "value";
        });
        Throwable throwable = new IOException();
        takeThrowing((l, r) -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw throwable;
            else
                return "value";
        });
    }
}
