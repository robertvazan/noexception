// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingLongPredicateTest {
    void takeThrowing(ThrowingLongPredicate functional) {
    }
    void takeNonThrowing(LongPredicate functional) {
    }
    @Test
    public void lambdas() {
        takeNonThrowing(v -> true);
        takeThrowing(v -> true);
        takeThrowing(v -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw new IOException();
            else
                return true;
        });
        Throwable throwable = new IOException();
        takeThrowing(v -> {
            if (ThreadLocalRandom.current().nextBoolean())
                throw throwable;
            else
                return true;
        });
    }
}
