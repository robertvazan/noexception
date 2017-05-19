package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingIntSupplier {
    int getAsInt() throws Exception;
}
