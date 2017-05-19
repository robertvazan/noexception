package com.machinezoo.noexception.throwing;

@FunctionalInterface public interface ThrowingDoublePredicate {
    boolean test(double value) throws Exception;
}
