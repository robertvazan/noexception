# NoException #

[![Maven Central](https://img.shields.io/maven-central/v/com.machinezoo.noexception/noexception)](https://search.maven.org/artifact/com.machinezoo.noexception/noexception)
[![Build Status](https://travis-ci.com/robertvazan/noexception.svg?branch=master)](https://travis-ci.com/robertvazan/noexception)

NoException is a Java library for handling exceptions in concise, unified, and architecturally clean way.

```java
System.out.println(Exceptions.log().get(() -> "test".substring(5)).orElse("fallback"));
```

* Documentation: [Tutorial](https://noexception.machinezoo.com/), [Javadoc](https://noexception.machinezoo.com/javadoc/overview-summary.html)
* Download: see [Tutorial](https://noexception.machinezoo.com/)
* Sources: [GitHub](https://github.com/robertvazan/noexception), [Bitbucket](https://bitbucket.org/robertvazan/noexception)
* Issues: [GitHub](https://github.com/robertvazan/noexception/issues), [Bitbucket](https://bitbucket.org/robertvazan/noexception/issues)
* License: [Apache License 2.0](LICENSE)

