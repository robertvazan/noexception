# NoException #

NoException is a Java library for handling exceptions in concise, unified, and architecturally clean way.

~~~~
#!Java
Exceptions.log().run(() -> {
    // ...
    throw new RuntimeException();
    // ...
});
methodTakingRunnable(Exceptions.sneak().runnable(() -> {
    // ...
    throw new IOException(); // checked exception
    // ...
}));
~~~~

Read the [tutorial](https://noexception.machinezoo.com/) to get started or browse [reference documentation](https://noexception.machinezoo.com/javadoc/overview-summary.html).