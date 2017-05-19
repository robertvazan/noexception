package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntConsumer {
    void accept(int value) throws Exception;
}
