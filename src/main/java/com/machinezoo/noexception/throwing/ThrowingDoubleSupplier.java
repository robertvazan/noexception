package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoubleSupplier {
    double getAsDouble() throws Exception;
}
